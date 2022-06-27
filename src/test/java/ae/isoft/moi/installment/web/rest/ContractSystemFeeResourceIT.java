package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.ContractSystemFee;
import ae.isoft.moi.installment.repository.ContractSystemFeeRepository;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDTO;
import ae.isoft.moi.installment.service.mapper.ContractSystemFeeMapper;
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
 * Integration tests for the {@link ContractSystemFeeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractSystemFeeResourceIT {

    private static final String DEFAULT_SYSTEMCODE = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEMCODE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SYSTEM_TOTAL_FEES = new BigDecimal(1);
    private static final BigDecimal UPDATED_SYSTEM_TOTAL_FEES = new BigDecimal(2);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Instant DEFAULT_STATUS_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STATUS_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/contract-system-fees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractSystemFeeRepository contractSystemFeeRepository;

    @Autowired
    private ContractSystemFeeMapper contractSystemFeeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractSystemFeeMockMvc;

    private ContractSystemFee contractSystemFee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSystemFee createEntity(EntityManager em) {
        ContractSystemFee contractSystemFee = new ContractSystemFee()
            .systemcode(DEFAULT_SYSTEMCODE)
            .systemNameAr(DEFAULT_SYSTEM_NAME_AR)
            .systemNameEn(DEFAULT_SYSTEM_NAME_EN)
            .systemTotalFees(DEFAULT_SYSTEM_TOTAL_FEES)
            .status(DEFAULT_STATUS)
            .statusDate(DEFAULT_STATUS_DATE);
        return contractSystemFee;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractSystemFee createUpdatedEntity(EntityManager em) {
        ContractSystemFee contractSystemFee = new ContractSystemFee()
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE);
        return contractSystemFee;
    }

    @BeforeEach
    public void initTest() {
        contractSystemFee = createEntity(em);
    }

    @Test
    @Transactional
    void createContractSystemFee() throws Exception {
        int databaseSizeBeforeCreate = contractSystemFeeRepository.findAll().size();
        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);
        restContractSystemFeeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeCreate + 1);
        ContractSystemFee testContractSystemFee = contractSystemFeeList.get(contractSystemFeeList.size() - 1);
        assertThat(testContractSystemFee.getSystemcode()).isEqualTo(DEFAULT_SYSTEMCODE);
        assertThat(testContractSystemFee.getSystemNameAr()).isEqualTo(DEFAULT_SYSTEM_NAME_AR);
        assertThat(testContractSystemFee.getSystemNameEn()).isEqualTo(DEFAULT_SYSTEM_NAME_EN);
        assertThat(testContractSystemFee.getSystemTotalFees()).isEqualByComparingTo(DEFAULT_SYSTEM_TOTAL_FEES);
        assertThat(testContractSystemFee.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContractSystemFee.getStatusDate()).isEqualTo(DEFAULT_STATUS_DATE);
    }

    @Test
    @Transactional
    void createContractSystemFeeWithExistingId() throws Exception {
        // Create the ContractSystemFee with an existing ID
        contractSystemFee.setId(1L);
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        int databaseSizeBeforeCreate = contractSystemFeeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractSystemFeeMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractSystemFees() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        // Get all the contractSystemFeeList
        restContractSystemFeeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractSystemFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemcode").value(hasItem(DEFAULT_SYSTEMCODE)))
            .andExpect(jsonPath("$.[*].systemNameAr").value(hasItem(DEFAULT_SYSTEM_NAME_AR)))
            .andExpect(jsonPath("$.[*].systemNameEn").value(hasItem(DEFAULT_SYSTEM_NAME_EN)))
            .andExpect(jsonPath("$.[*].systemTotalFees").value(hasItem(sameNumber(DEFAULT_SYSTEM_TOTAL_FEES))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDate").value(hasItem(DEFAULT_STATUS_DATE.toString())));
    }

    @Test
    @Transactional
    void getContractSystemFee() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        // Get the contractSystemFee
        restContractSystemFeeMockMvc
            .perform(get(ENTITY_API_URL_ID, contractSystemFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractSystemFee.getId().intValue()))
            .andExpect(jsonPath("$.systemcode").value(DEFAULT_SYSTEMCODE))
            .andExpect(jsonPath("$.systemNameAr").value(DEFAULT_SYSTEM_NAME_AR))
            .andExpect(jsonPath("$.systemNameEn").value(DEFAULT_SYSTEM_NAME_EN))
            .andExpect(jsonPath("$.systemTotalFees").value(sameNumber(DEFAULT_SYSTEM_TOTAL_FEES)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusDate").value(DEFAULT_STATUS_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingContractSystemFee() throws Exception {
        // Get the contractSystemFee
        restContractSystemFeeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractSystemFee() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();

        // Update the contractSystemFee
        ContractSystemFee updatedContractSystemFee = contractSystemFeeRepository.findById(contractSystemFee.getId()).get();
        // Disconnect from session so that the updates on updatedContractSystemFee are not directly saved in db
        em.detach(updatedContractSystemFee);
        updatedContractSystemFee
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE);
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(updatedContractSystemFee);

        restContractSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSystemFeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFee testContractSystemFee = contractSystemFeeList.get(contractSystemFeeList.size() - 1);
        assertThat(testContractSystemFee.getSystemcode()).isEqualTo(UPDATED_SYSTEMCODE);
        assertThat(testContractSystemFee.getSystemNameAr()).isEqualTo(UPDATED_SYSTEM_NAME_AR);
        assertThat(testContractSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testContractSystemFee.getSystemTotalFees()).isEqualByComparingTo(UPDATED_SYSTEM_TOTAL_FEES);
        assertThat(testContractSystemFee.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractSystemFee.getStatusDate()).isEqualTo(UPDATED_STATUS_DATE);
    }

    @Test
    @Transactional
    void putNonExistingContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractSystemFeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractSystemFeeWithPatch() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();

        // Update the contractSystemFee using partial update
        ContractSystemFee partialUpdatedContractSystemFee = new ContractSystemFee();
        partialUpdatedContractSystemFee.setId(contractSystemFee.getId());

        partialUpdatedContractSystemFee.systemNameEn(UPDATED_SYSTEM_NAME_EN).systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES);

        restContractSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSystemFee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSystemFee))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFee testContractSystemFee = contractSystemFeeList.get(contractSystemFeeList.size() - 1);
        assertThat(testContractSystemFee.getSystemcode()).isEqualTo(DEFAULT_SYSTEMCODE);
        assertThat(testContractSystemFee.getSystemNameAr()).isEqualTo(DEFAULT_SYSTEM_NAME_AR);
        assertThat(testContractSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testContractSystemFee.getSystemTotalFees()).isEqualByComparingTo(UPDATED_SYSTEM_TOTAL_FEES);
        assertThat(testContractSystemFee.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContractSystemFee.getStatusDate()).isEqualTo(DEFAULT_STATUS_DATE);
    }

    @Test
    @Transactional
    void fullUpdateContractSystemFeeWithPatch() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();

        // Update the contractSystemFee using partial update
        ContractSystemFee partialUpdatedContractSystemFee = new ContractSystemFee();
        partialUpdatedContractSystemFee.setId(contractSystemFee.getId());

        partialUpdatedContractSystemFee
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES)
            .status(UPDATED_STATUS)
            .statusDate(UPDATED_STATUS_DATE);

        restContractSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractSystemFee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractSystemFee))
            )
            .andExpect(status().isOk());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        ContractSystemFee testContractSystemFee = contractSystemFeeList.get(contractSystemFeeList.size() - 1);
        assertThat(testContractSystemFee.getSystemcode()).isEqualTo(UPDATED_SYSTEMCODE);
        assertThat(testContractSystemFee.getSystemNameAr()).isEqualTo(UPDATED_SYSTEM_NAME_AR);
        assertThat(testContractSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testContractSystemFee.getSystemTotalFees()).isEqualByComparingTo(UPDATED_SYSTEM_TOTAL_FEES);
        assertThat(testContractSystemFee.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContractSystemFee.getStatusDate()).isEqualTo(UPDATED_STATUS_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractSystemFeeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = contractSystemFeeRepository.findAll().size();
        contractSystemFee.setId(count.incrementAndGet());

        // Create the ContractSystemFee
        ContractSystemFeeDTO contractSystemFeeDTO = contractSystemFeeMapper.toDto(contractSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractSystemFeeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractSystemFee in the database
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractSystemFee() throws Exception {
        // Initialize the database
        contractSystemFeeRepository.saveAndFlush(contractSystemFee);

        int databaseSizeBeforeDelete = contractSystemFeeRepository.findAll().size();

        // Delete the contractSystemFee
        restContractSystemFeeMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractSystemFee.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractSystemFee> contractSystemFeeList = contractSystemFeeRepository.findAll();
        assertThat(contractSystemFeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
