package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.ContractPayment;
import ae.isoft.moi.installment.repository.ContractPaymentRepository;
import ae.isoft.moi.installment.service.dto.ContractPaymentDTO;
import ae.isoft.moi.installment.service.mapper.ContractPaymentMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ContractPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractPaymentResourceIT {

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_INSTALLMENT_NO = 1;
    private static final Integer UPDATED_INSTALLMENT_NO = 2;

    private static final BigDecimal DEFAULT_INSTALLMENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSTALLMENT_AMOUNT = new BigDecimal(2);

    private static final Instant DEFAULT_INSTALLMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTALLMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_INSTALLMENT_LATE_FEES = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSTALLMENT_LATE_FEES = new BigDecimal(2);

    private static final Instant DEFAULT_PAYMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAYMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_PAYMENT_TYPE = 1;
    private static final Integer UPDATED_PAYMENT_TYPE = 2;

    private static final String DEFAULT_RECEIPT_NO = "AAAAAAAAAA";
    private static final String UPDATED_RECEIPT_NO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CREATION_FEES = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREATION_FEES = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/contract-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractPaymentRepository contractPaymentRepository;

    @Autowired
    private ContractPaymentMapper contractPaymentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractPaymentMockMvc;

    private ContractPayment contractPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractPayment createEntity(EntityManager em) {
        ContractPayment contractPayment = new ContractPayment()
            .status(DEFAULT_STATUS)
            .installmentNo(DEFAULT_INSTALLMENT_NO)
            .installmentAmount(DEFAULT_INSTALLMENT_AMOUNT)
            .installmentDate(DEFAULT_INSTALLMENT_DATE)
            .installmentLateFees(DEFAULT_INSTALLMENT_LATE_FEES)
            .paymentDate(DEFAULT_PAYMENT_DATE)
            .paymentType(DEFAULT_PAYMENT_TYPE)
            .receiptNo(DEFAULT_RECEIPT_NO)
            .creationFees(DEFAULT_CREATION_FEES);
        return contractPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractPayment createUpdatedEntity(EntityManager em) {
        ContractPayment contractPayment = new ContractPayment()
            .status(UPDATED_STATUS)
            .installmentNo(UPDATED_INSTALLMENT_NO)
            .installmentAmount(UPDATED_INSTALLMENT_AMOUNT)
            .installmentDate(UPDATED_INSTALLMENT_DATE)
            .installmentLateFees(UPDATED_INSTALLMENT_LATE_FEES)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .receiptNo(UPDATED_RECEIPT_NO)
            .creationFees(UPDATED_CREATION_FEES);
        return contractPayment;
    }

    @BeforeEach
    public void initTest() {
        contractPayment = createEntity(em);
    }

    @Test
    @Transactional
    void createContractPayment() throws Exception {
        int databaseSizeBeforeCreate = contractPaymentRepository.findAll().size();
        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);
        restContractPaymentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        ContractPayment testContractPayment = contractPaymentList.get(contractPaymentList.size() - 1);
        assertThat(testContractPayment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContractPayment.getInstallmentNo()).isEqualTo(DEFAULT_INSTALLMENT_NO);
        assertThat(testContractPayment.getInstallmentAmount()).isEqualByComparingTo(DEFAULT_INSTALLMENT_AMOUNT);
        assertThat(testContractPayment.getInstallmentDate()).isEqualTo(DEFAULT_INSTALLMENT_DATE);
        assertThat(testContractPayment.getInstallmentLateFees()).isEqualByComparingTo(DEFAULT_INSTALLMENT_LATE_FEES);
        assertThat(testContractPayment.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testContractPayment.getPaymentType()).isEqualTo(DEFAULT_PAYMENT_TYPE);
        assertThat(testContractPayment.getReceiptNo()).isEqualTo(DEFAULT_RECEIPT_NO);
        assertThat(testContractPayment.getCreationFees()).isEqualByComparingTo(DEFAULT_CREATION_FEES);
    }

    @Test
    @Transactional
    void createContractPaymentWithExistingId() throws Exception {
        // Create the ContractPayment with an existing ID
        contractPayment.setId(1L);
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        int databaseSizeBeforeCreate = contractPaymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractPaymentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractPayments() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        // Get all the contractPaymentList
        restContractPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].installmentNo").value(hasItem(DEFAULT_INSTALLMENT_NO)))
            .andExpect(jsonPath("$.[*].installmentAmount").value(hasItem(sameNumber(DEFAULT_INSTALLMENT_AMOUNT))))
            .andExpect(jsonPath("$.[*].installmentDate").value(hasItem(DEFAULT_INSTALLMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].installmentLateFees").value(hasItem(sameNumber(DEFAULT_INSTALLMENT_LATE_FEES))))
            .andExpect(jsonPath("$.[*].paymentDate").value(hasItem(DEFAULT_PAYMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].paymentType").value(hasItem(DEFAULT_PAYMENT_TYPE)))
            .andExpect(jsonPath("$.[*].receiptNo").value(hasItem(DEFAULT_RECEIPT_NO)))
            .andExpect(jsonPath("$.[*].creationFees").value(hasItem(sameNumber(DEFAULT_CREATION_FEES))));
    }

    @Test
    @Transactional
    void getContractPayment() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        // Get the contractPayment
        restContractPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, contractPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractPayment.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.installmentNo").value(DEFAULT_INSTALLMENT_NO))
            .andExpect(jsonPath("$.installmentAmount").value(sameNumber(DEFAULT_INSTALLMENT_AMOUNT)))
            .andExpect(jsonPath("$.installmentDate").value(DEFAULT_INSTALLMENT_DATE.toString()))
            .andExpect(jsonPath("$.installmentLateFees").value(sameNumber(DEFAULT_INSTALLMENT_LATE_FEES)))
            .andExpect(jsonPath("$.paymentDate").value(DEFAULT_PAYMENT_DATE.toString()))
            .andExpect(jsonPath("$.paymentType").value(DEFAULT_PAYMENT_TYPE))
            .andExpect(jsonPath("$.receiptNo").value(DEFAULT_RECEIPT_NO))
            .andExpect(jsonPath("$.creationFees").value(sameNumber(DEFAULT_CREATION_FEES)));
    }

    @Test
    @Transactional
    void getNonExistingContractPayment() throws Exception {
        // Get the contractPayment
        restContractPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractPayment() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();

        // Update the contractPayment
        ContractPayment updatedContractPayment = contractPaymentRepository.findById(contractPayment.getId()).get();
        // Disconnect from session so that the updates on updatedContractPayment are not directly saved in db
        em.detach(updatedContractPayment);
        updatedContractPayment
            .status(UPDATED_STATUS)
            .installmentNo(UPDATED_INSTALLMENT_NO)
            .installmentAmount(UPDATED_INSTALLMENT_AMOUNT)
            .installmentDate(UPDATED_INSTALLMENT_DATE)
            .installmentLateFees(UPDATED_INSTALLMENT_LATE_FEES)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .receiptNo(UPDATED_RECEIPT_NO)
            .creationFees(UPDATED_CREATION_FEES);
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(updatedContractPayment);

        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
        ContractPayment testContractPayment = contractPaymentList.get(contractPaymentList.size() - 1);
        assertThat(testContractPayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractPayment.getInstallmentNo()).isEqualTo(UPDATED_INSTALLMENT_NO);
        assertThat(testContractPayment.getInstallmentAmount()).isEqualByComparingTo(UPDATED_INSTALLMENT_AMOUNT);
        assertThat(testContractPayment.getInstallmentDate()).isEqualTo(UPDATED_INSTALLMENT_DATE);
        assertThat(testContractPayment.getInstallmentLateFees()).isEqualByComparingTo(UPDATED_INSTALLMENT_LATE_FEES);
        assertThat(testContractPayment.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testContractPayment.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testContractPayment.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testContractPayment.getCreationFees()).isEqualByComparingTo(UPDATED_CREATION_FEES);
    }

    @Test
    @Transactional
    void putNonExistingContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractPaymentWithPatch() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();

        // Update the contractPayment using partial update
        ContractPayment partialUpdatedContractPayment = new ContractPayment();
        partialUpdatedContractPayment.setId(contractPayment.getId());

        partialUpdatedContractPayment
            .status(UPDATED_STATUS)
            .installmentNo(UPDATED_INSTALLMENT_NO)
            .installmentLateFees(UPDATED_INSTALLMENT_LATE_FEES)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .receiptNo(UPDATED_RECEIPT_NO);

        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractPayment))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
        ContractPayment testContractPayment = contractPaymentList.get(contractPaymentList.size() - 1);
        assertThat(testContractPayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractPayment.getInstallmentNo()).isEqualTo(UPDATED_INSTALLMENT_NO);
        assertThat(testContractPayment.getInstallmentAmount()).isEqualByComparingTo(DEFAULT_INSTALLMENT_AMOUNT);
        assertThat(testContractPayment.getInstallmentDate()).isEqualTo(DEFAULT_INSTALLMENT_DATE);
        assertThat(testContractPayment.getInstallmentLateFees()).isEqualByComparingTo(UPDATED_INSTALLMENT_LATE_FEES);
        assertThat(testContractPayment.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testContractPayment.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testContractPayment.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testContractPayment.getCreationFees()).isEqualByComparingTo(DEFAULT_CREATION_FEES);
    }

    @Test
    @Transactional
    void fullUpdateContractPaymentWithPatch() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();

        // Update the contractPayment using partial update
        ContractPayment partialUpdatedContractPayment = new ContractPayment();
        partialUpdatedContractPayment.setId(contractPayment.getId());

        partialUpdatedContractPayment
            .status(UPDATED_STATUS)
            .installmentNo(UPDATED_INSTALLMENT_NO)
            .installmentAmount(UPDATED_INSTALLMENT_AMOUNT)
            .installmentDate(UPDATED_INSTALLMENT_DATE)
            .installmentLateFees(UPDATED_INSTALLMENT_LATE_FEES)
            .paymentDate(UPDATED_PAYMENT_DATE)
            .paymentType(UPDATED_PAYMENT_TYPE)
            .receiptNo(UPDATED_RECEIPT_NO)
            .creationFees(UPDATED_CREATION_FEES);

        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractPayment))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
        ContractPayment testContractPayment = contractPaymentList.get(contractPaymentList.size() - 1);
        assertThat(testContractPayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractPayment.getInstallmentNo()).isEqualTo(UPDATED_INSTALLMENT_NO);
        assertThat(testContractPayment.getInstallmentAmount()).isEqualByComparingTo(UPDATED_INSTALLMENT_AMOUNT);
        assertThat(testContractPayment.getInstallmentDate()).isEqualTo(UPDATED_INSTALLMENT_DATE);
        assertThat(testContractPayment.getInstallmentLateFees()).isEqualByComparingTo(UPDATED_INSTALLMENT_LATE_FEES);
        assertThat(testContractPayment.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testContractPayment.getPaymentType()).isEqualTo(UPDATED_PAYMENT_TYPE);
        assertThat(testContractPayment.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testContractPayment.getCreationFees()).isEqualByComparingTo(UPDATED_CREATION_FEES);
    }

    @Test
    @Transactional
    void patchNonExistingContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractPaymentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractPayment() throws Exception {
        int databaseSizeBeforeUpdate = contractPaymentRepository.findAll().size();
        contractPayment.setId(count.incrementAndGet());

        // Create the ContractPayment
        ContractPaymentDTO contractPaymentDTO = contractPaymentMapper.toDto(contractPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractPayment in the database
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractPayment() throws Exception {
        // Initialize the database
        contractPaymentRepository.saveAndFlush(contractPayment);

        int databaseSizeBeforeDelete = contractPaymentRepository.findAll().size();

        // Delete the contractPayment
        restContractPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractPayment> contractPaymentList = contractPaymentRepository.findAll();
        assertThat(contractPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
