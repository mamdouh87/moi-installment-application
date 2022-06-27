package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.ContractSystemFeeDetails;
import ae.isoft.moi.installment.repository.ContractSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDetailsDTO;
import ae.isoft.moi.installment.service.mapper.ContractSystemFeeDetailsMapper;
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
 * Integration tests for the {@link ContractSystemFeeDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractSystemFeeDetailsResourceIT {

    private static final String DEFAULT_FEE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FEE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_FEE_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_FEE_NAME_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FEE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_FEE_AMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_STATUS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATUS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_DRAFT_CONTRACT_SYSTEM_FEE = 1;
    private static final Integer UPDATED_DRAFT_CONTRACT_SYSTEM_FEE = 2;

    private static final String ENTITY_API_URL = "/api/contract-system-fee-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractSystemFeeDetailsRepository contractSystemFeeDetailsRepository;

    @Autowired
    private ContractSystemFeeDetailsMapper contractSystemFeeDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractSystemFeeDetailsMockMvc;

    private ContractSystemFeeDetails contractSystemFeeDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSystemFeeDetails createEntity(EntityManager em) {
        ContractSystemFeeDetails contractSystemFeeDetails = new ContractSystemFeeDetails()
            .feeCode(DEFAULT_FEE_CODE)
            .feeNameAr(DEFAULT_FEE_NAME_AR)
            .feeNameEn(DEFAULT_FEE_NAME_EN)
            .feeAmount(DEFAULT_FEE_AMOUNT)
            .status(DEFAULT_STATUS)
            .statusDate(DEFAULT_STATUS_DATE)
            .draftContractSystemFee(DEFAULT_DRAFT_CONTRACT_SYSTEM_FEE);
        return contractSystemFeeDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSystemFeeDetails createUpdatedEntity(EntityManager em) {
        ContractSystemFeeDetails contractSystemFeeDetails = new ContractSystemFeeDetails()
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE)
            .draftContractSystemFee(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);
        return contractSystemFeeDetails;
    }

    @BeforeEach
    public void initTest() {
        contractSystemFeeDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeCreate = contractSystemFeeDetailsRepository.findAll().size();
        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);
        restContractSystemFeeDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ContractSystemFeeDetails testContractSystemFeeDetails = contractSystemFeeDetailsList.get(contractSystemFeeDetailsList.size() - 1);
        assertThat(testContractSystemFeeDetails.getFeeCode()).isEqualTo(DEFAULT_FEE_CODE);
        assertThat(testContractSystemFeeDetails.getFeeNameAr()).isEqualTo(DEFAULT_FEE_NAME_AR);
        assertThat(testContractSystemFeeDetails.getFeeNameEn()).isEqualTo(DEFAULT_FEE_NAME_EN);
        assertThat(testContractSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(DEFAULT_FEE_AMOUNT);
        assertThat(testContractSystemFeeDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContractSystemFeeDetails.getStatusDate()).isEqualTo(DEFAULT_STATUS_DATE);
        assertThat(testContractSystemFeeDetails.getDraftContractSystemFee()).isEqualTo(DEFAULT_DRAFT_CONTRACT_SYSTEM_FEE);
    }

    @Test
    @Transactional
    void createContractSystemFeeDetailsWithExistingId() throws Exception {
        // Create the ContractSystemFeeDetails with an existing ID
        contractSystemFeeDetails.setId(1L);
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        int databaseSizeBeforeCreate = contractSystemFeeDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractSystemFeeDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractSystemFeeDetails() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        // Get all the contractSystemFeeDetailsList
        restContractSystemFeeDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractSystemFeeDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeCode").value(hasItem(DEFAULT_FEE_CODE)))
            .andExpect(jsonPath("$.[*].feeNameAr").value(hasItem(DEFAULT_FEE_NAME_AR)))
            .andExpect(jsonPath("$.[*].feeNameEn").value(hasItem(DEFAULT_FEE_NAME_EN)))
            .andExpect(jsonPath("$.[*].feeAmount").value(hasItem(sameNumber(DEFAULT_FEE_AMOUNT))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDate").value(hasItem(DEFAULT_STATUS_DATE.toString())))
            .andExpect(jsonPath("$.[*].draftContractSystemFee").value(hasItem(DEFAULT_DRAFT_CONTRACT_SYSTEM_FEE)));
    }

    @Test
    @Transactional
    void getContractSystemFeeDetails() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        // Get the contractSystemFeeDetails
        restContractSystemFeeDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, contractSystemFeeDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractSystemFeeDetails.getId().intValue()))
            .andExpect(jsonPath("$.feeCode").value(DEFAULT_FEE_CODE))
            .andExpect(jsonPath("$.feeNameAr").value(DEFAULT_FEE_NAME_AR))
            .andExpect(jsonPath("$.feeNameEn").value(DEFAULT_FEE_NAME_EN))
            .andExpect(jsonPath("$.feeAmount").value(sameNumber(DEFAULT_FEE_AMOUNT)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusDate").value(DEFAULT_STATUS_DATE.toString()))
            .andExpect(jsonPath("$.draftContractSystemFee").value(DEFAULT_DRAFT_CONTRACT_SYSTEM_FEE));
    }

    @Test
    @Transactional
    void getNonExistingContractSystemFeeDetails() throws Exception {
        // Get the contractSystemFeeDetails
        restContractSystemFeeDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractSystemFeeDetails() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();

        // Update the contractSystemFeeDetails
        ContractSystemFeeDetails updatedContractSystemFeeDetails = contractSystemFeeDetailsRepository
            .findById(contractSystemFeeDetails.getId())
            .get();
        // Disconnect from session so that the updates on updatedContractSystemFeeDetails are not directly saved in db
        em.detach(updatedContractSystemFeeDetails);
        updatedContractSystemFeeDetails
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE)
            .draftContractSystemFee(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(updatedContractSystemFeeDetails);

        restContractSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSystemFeeDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFeeDetails testContractSystemFeeDetails = contractSystemFeeDetailsList.get(contractSystemFeeDetailsList.size() - 1);
        assertThat(testContractSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testContractSystemFeeDetails.getFeeNameAr()).isEqualTo(UPDATED_FEE_NAME_AR);
        assertThat(testContractSystemFeeDetails.getFeeNameEn()).isEqualTo(UPDATED_FEE_NAME_EN);
        assertThat(testContractSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(UPDATED_FEE_AMOUNT);
        assertThat(testContractSystemFeeDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractSystemFeeDetails.getStatusDate()).isEqualTo(UPDATED_STATUS_DATE);
        assertThat(testContractSystemFeeDetails.getDraftContractSystemFee()).isEqualTo(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);
    }

    @Test
    @Transactional
    void putNonExistingContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSystemFeeDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractSystemFeeDetailsWithPatch() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();

        // Update the contractSystemFeeDetails using partial update
        ContractSystemFeeDetails partialUpdatedContractSystemFeeDetails = new ContractSystemFeeDetails();
        partialUpdatedContractSystemFeeDetails.setId(contractSystemFeeDetails.getId());

        partialUpdatedContractSystemFeeDetails
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE)
            .draftContractSystemFee(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);

        restContractSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSystemFeeDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSystemFeeDetails))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFeeDetails testContractSystemFeeDetails = contractSystemFeeDetailsList.get(contractSystemFeeDetailsList.size() - 1);
        assertThat(testContractSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testContractSystemFeeDetails.getFeeNameAr()).isEqualTo(UPDATED_FEE_NAME_AR);
        assertThat(testContractSystemFeeDetails.getFeeNameEn()).isEqualTo(DEFAULT_FEE_NAME_EN);
        assertThat(testContractSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(UPDATED_FEE_AMOUNT);
        assertThat(testContractSystemFeeDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractSystemFeeDetails.getStatusDate()).isEqualTo(UPDATED_STATUS_DATE);
        assertThat(testContractSystemFeeDetails.getDraftContractSystemFee()).isEqualTo(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);
    }

    @Test
    @Transactional
    void fullUpdateContractSystemFeeDetailsWithPatch() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();

        // Update the contractSystemFeeDetails using partial update
        ContractSystemFeeDetails partialUpdatedContractSystemFeeDetails = new ContractSystemFeeDetails();
        partialUpdatedContractSystemFeeDetails.setId(contractSystemFeeDetails.getId());

        partialUpdatedContractSystemFeeDetails
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE)
            .draftContractSystemFee(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);

        restContractSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSystemFeeDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSystemFeeDetails))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFeeDetails testContractSystemFeeDetails = contractSystemFeeDetailsList.get(contractSystemFeeDetailsList.size() - 1);
        assertThat(testContractSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testContractSystemFeeDetails.getFeeNameAr()).isEqualTo(UPDATED_FEE_NAME_AR);
        assertThat(testContractSystemFeeDetails.getFeeNameEn()).isEqualTo(UPDATED_FEE_NAME_EN);
        assertThat(testContractSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(UPDATED_FEE_AMOUNT);
        assertThat(testContractSystemFeeDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractSystemFeeDetails.getStatusDate()).isEqualTo(UPDATED_STATUS_DATE);
        assertThat(testContractSystemFeeDetails.getDraftContractSystemFee()).isEqualTo(UPDATED_DRAFT_CONTRACT_SYSTEM_FEE);
    }

    @Test
    @Transactional
    void patchNonExistingContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractSystemFeeDetailsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeDetailsRepository.findAll().size();
        contractSystemFeeDetails.setId(count.incrementAndGet());

        // Create the ContractSystemFeeDetails
        ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO = contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSystemFeeDetails in the database
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractSystemFeeDetails() throws Exception {
        // Initialize the database
        contractSystemFeeDetailsRepository.saveAndFlush(contractSystemFeeDetails);

        int databaseSizeBeforeDelete = contractSystemFeeDetailsRepository.findAll().size();

        // Delete the contractSystemFeeDetails
        restContractSystemFeeDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractSystemFeeDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractSystemFeeDetails> contractSystemFeeDetailsList = contractSystemFeeDetailsRepository.findAll();
        assertThat(contractSystemFeeDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
