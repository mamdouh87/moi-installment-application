package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.Contract;
import ae.isoft.moi.installment.repository.ContractRepository;
import ae.isoft.moi.installment.service.dto.ContractDTO;
import ae.isoft.moi.installment.service.mapper.ContractMapper;
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
 * Integration tests for the {@link ContractResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractResourceIT {

    private static final String DEFAULT_CONTRACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACT_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMER_ID = 1;
    private static final Integer UPDATED_CUSTOMER_ID = 2;

    private static final String DEFAULT_NATIONAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PASSPORT_NO = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT_NO = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNTRY_ID = 1;
    private static final Integer UPDATED_COUNTRY_ID = 2;

    private static final String DEFAULT_TRADE_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_LICENSE = "BBBBBBBBBB";

    private static final Instant DEFAULT_SIGN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SIGN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final BigDecimal DEFAULT_ACTUAL_CONTRACTED_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACTUAL_CONTRACTED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INTEREST_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEREST_PERCENTAGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACT_AMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/contracts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractMockMvc;

    private Contract contract;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contract createEntity(EntityManager em) {
        Contract contract = new Contract()
            .contractNo(DEFAULT_CONTRACT_NO)
            .status(DEFAULT_STATUS)
            .mobileNo(DEFAULT_MOBILE_NO)
            .address(DEFAULT_ADDRESS)
            .fullName(DEFAULT_FULL_NAME)
            .customerId(DEFAULT_CUSTOMER_ID)
            .nationalId(DEFAULT_NATIONAL_ID)
            .passportNo(DEFAULT_PASSPORT_NO)
            .countryId(DEFAULT_COUNTRY_ID)
            .tradeLicense(DEFAULT_TRADE_LICENSE)
            .signDate(DEFAULT_SIGN_DATE)
            .userId(DEFAULT_USER_ID)
            .actualContractedAmount(DEFAULT_ACTUAL_CONTRACTED_AMOUNT)
            .interestPercentage(DEFAULT_INTEREST_PERCENTAGE)
            .contractAmount(DEFAULT_CONTRACT_AMOUNT);
        return contract;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contract createUpdatedEntity(EntityManager em) {
        Contract contract = new Contract()
            .contractNo(UPDATED_CONTRACT_NO)
            .status(UPDATED_STATUS)
            .mobileNo(UPDATED_MOBILE_NO)
            .address(UPDATED_ADDRESS)
            .fullName(UPDATED_FULL_NAME)
            .customerId(UPDATED_CUSTOMER_ID)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportNo(UPDATED_PASSPORT_NO)
            .countryId(UPDATED_COUNTRY_ID)
            .tradeLicense(UPDATED_TRADE_LICENSE)
            .signDate(UPDATED_SIGN_DATE)
            .userId(UPDATED_USER_ID)
            .actualContractedAmount(UPDATED_ACTUAL_CONTRACTED_AMOUNT)
            .interestPercentage(UPDATED_INTEREST_PERCENTAGE)
            .contractAmount(UPDATED_CONTRACT_AMOUNT);
        return contract;
    }

    @BeforeEach
    public void initTest() {
        contract = createEntity(em);
    }

    @Test
    @Transactional
    void createContract() throws Exception {
        int databaseSizeBeforeCreate = contractRepository.findAll().size();
        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);
        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isCreated());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate + 1);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getContractNo()).isEqualTo(DEFAULT_CONTRACT_NO);
        assertThat(testContract.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testContract.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testContract.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testContract.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testContract.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testContract.getNationalId()).isEqualTo(DEFAULT_NATIONAL_ID);
        assertThat(testContract.getPassportNo()).isEqualTo(DEFAULT_PASSPORT_NO);
        assertThat(testContract.getCountryId()).isEqualTo(DEFAULT_COUNTRY_ID);
        assertThat(testContract.getTradeLicense()).isEqualTo(DEFAULT_TRADE_LICENSE);
        assertThat(testContract.getSignDate()).isEqualTo(DEFAULT_SIGN_DATE);
        assertThat(testContract.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testContract.getActualContractedAmount()).isEqualByComparingTo(DEFAULT_ACTUAL_CONTRACTED_AMOUNT);
        assertThat(testContract.getInterestPercentage()).isEqualByComparingTo(DEFAULT_INTEREST_PERCENTAGE);
        assertThat(testContract.getContractAmount()).isEqualByComparingTo(DEFAULT_CONTRACT_AMOUNT);
    }

    @Test
    @Transactional
    void createContractWithExistingId() throws Exception {
        // Create the Contract with an existing ID
        contract.setId(1L);
        ContractDTO contractDTO = contractMapper.toDto(contract);

        int databaseSizeBeforeCreate = contractRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContracts() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get all the contractList
        restContractMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contract.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractNo").value(hasItem(DEFAULT_CONTRACT_NO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].passportNo").value(hasItem(DEFAULT_PASSPORT_NO)))
            .andExpect(jsonPath("$.[*].countryId").value(hasItem(DEFAULT_COUNTRY_ID)))
            .andExpect(jsonPath("$.[*].tradeLicense").value(hasItem(DEFAULT_TRADE_LICENSE)))
            .andExpect(jsonPath("$.[*].signDate").value(hasItem(DEFAULT_SIGN_DATE.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].actualContractedAmount").value(hasItem(sameNumber(DEFAULT_ACTUAL_CONTRACTED_AMOUNT))))
            .andExpect(jsonPath("$.[*].interestPercentage").value(hasItem(sameNumber(DEFAULT_INTEREST_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].contractAmount").value(hasItem(sameNumber(DEFAULT_CONTRACT_AMOUNT))));
    }

    @Test
    @Transactional
    void getContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        // Get the contract
        restContractMockMvc
            .perform(get(ENTITY_API_URL_ID, contract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contract.getId().intValue()))
            .andExpect(jsonPath("$.contractNo").value(DEFAULT_CONTRACT_NO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID))
            .andExpect(jsonPath("$.nationalId").value(DEFAULT_NATIONAL_ID))
            .andExpect(jsonPath("$.passportNo").value(DEFAULT_PASSPORT_NO))
            .andExpect(jsonPath("$.countryId").value(DEFAULT_COUNTRY_ID))
            .andExpect(jsonPath("$.tradeLicense").value(DEFAULT_TRADE_LICENSE))
            .andExpect(jsonPath("$.signDate").value(DEFAULT_SIGN_DATE.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.actualContractedAmount").value(sameNumber(DEFAULT_ACTUAL_CONTRACTED_AMOUNT)))
            .andExpect(jsonPath("$.interestPercentage").value(sameNumber(DEFAULT_INTEREST_PERCENTAGE)))
            .andExpect(jsonPath("$.contractAmount").value(sameNumber(DEFAULT_CONTRACT_AMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingContract() throws Exception {
        // Get the contract
        restContractMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract
        Contract updatedContract = contractRepository.findById(contract.getId()).get();
        // Disconnect from session so that the updates on updatedContract are not directly saved in db
        em.detach(updatedContract);
        updatedContract
            .contractNo(UPDATED_CONTRACT_NO)
            .status(UPDATED_STATUS)
            .mobileNo(UPDATED_MOBILE_NO)
            .address(UPDATED_ADDRESS)
            .fullName(UPDATED_FULL_NAME)
            .customerId(UPDATED_CUSTOMER_ID)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportNo(UPDATED_PASSPORT_NO)
            .countryId(UPDATED_COUNTRY_ID)
            .tradeLicense(UPDATED_TRADE_LICENSE)
            .signDate(UPDATED_SIGN_DATE)
            .userId(UPDATED_USER_ID)
            .actualContractedAmount(UPDATED_ACTUAL_CONTRACTED_AMOUNT)
            .interestPercentage(UPDATED_INTEREST_PERCENTAGE)
            .contractAmount(UPDATED_CONTRACT_AMOUNT);
        ContractDTO contractDTO = contractMapper.toDto(updatedContract);

        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getContractNo()).isEqualTo(UPDATED_CONTRACT_NO);
        assertThat(testContract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContract.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testContract.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testContract.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testContract.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testContract.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testContract.getPassportNo()).isEqualTo(UPDATED_PASSPORT_NO);
        assertThat(testContract.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testContract.getTradeLicense()).isEqualTo(UPDATED_TRADE_LICENSE);
        assertThat(testContract.getSignDate()).isEqualTo(UPDATED_SIGN_DATE);
        assertThat(testContract.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContract.getActualContractedAmount()).isEqualByComparingTo(UPDATED_ACTUAL_CONTRACTED_AMOUNT);
        assertThat(testContract.getInterestPercentage()).isEqualByComparingTo(UPDATED_INTEREST_PERCENTAGE);
        assertThat(testContract.getContractAmount()).isEqualByComparingTo(UPDATED_CONTRACT_AMOUNT);
    }

    @Test
    @Transactional
    void putNonExistingContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractWithPatch() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract using partial update
        Contract partialUpdatedContract = new Contract();
        partialUpdatedContract.setId(contract.getId());

        partialUpdatedContract
            .status(UPDATED_STATUS)
            .mobileNo(UPDATED_MOBILE_NO)
            .customerId(UPDATED_CUSTOMER_ID)
            .countryId(UPDATED_COUNTRY_ID)
            .userId(UPDATED_USER_ID)
            .actualContractedAmount(UPDATED_ACTUAL_CONTRACTED_AMOUNT)
            .interestPercentage(UPDATED_INTEREST_PERCENTAGE);

        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getContractNo()).isEqualTo(DEFAULT_CONTRACT_NO);
        assertThat(testContract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContract.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testContract.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testContract.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testContract.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testContract.getNationalId()).isEqualTo(DEFAULT_NATIONAL_ID);
        assertThat(testContract.getPassportNo()).isEqualTo(DEFAULT_PASSPORT_NO);
        assertThat(testContract.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testContract.getTradeLicense()).isEqualTo(DEFAULT_TRADE_LICENSE);
        assertThat(testContract.getSignDate()).isEqualTo(DEFAULT_SIGN_DATE);
        assertThat(testContract.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContract.getActualContractedAmount()).isEqualByComparingTo(UPDATED_ACTUAL_CONTRACTED_AMOUNT);
        assertThat(testContract.getInterestPercentage()).isEqualByComparingTo(UPDATED_INTEREST_PERCENTAGE);
        assertThat(testContract.getContractAmount()).isEqualByComparingTo(DEFAULT_CONTRACT_AMOUNT);
    }

    @Test
    @Transactional
    void fullUpdateContractWithPatch() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeUpdate = contractRepository.findAll().size();

        // Update the contract using partial update
        Contract partialUpdatedContract = new Contract();
        partialUpdatedContract.setId(contract.getId());

        partialUpdatedContract
            .contractNo(UPDATED_CONTRACT_NO)
            .status(UPDATED_STATUS)
            .mobileNo(UPDATED_MOBILE_NO)
            .address(UPDATED_ADDRESS)
            .fullName(UPDATED_FULL_NAME)
            .customerId(UPDATED_CUSTOMER_ID)
            .nationalId(UPDATED_NATIONAL_ID)
            .passportNo(UPDATED_PASSPORT_NO)
            .countryId(UPDATED_COUNTRY_ID)
            .tradeLicense(UPDATED_TRADE_LICENSE)
            .signDate(UPDATED_SIGN_DATE)
            .userId(UPDATED_USER_ID)
            .actualContractedAmount(UPDATED_ACTUAL_CONTRACTED_AMOUNT)
            .interestPercentage(UPDATED_INTEREST_PERCENTAGE)
            .contractAmount(UPDATED_CONTRACT_AMOUNT);

        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
            )
            .andExpect(status().isOk());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
        Contract testContract = contractList.get(contractList.size() - 1);
        assertThat(testContract.getContractNo()).isEqualTo(UPDATED_CONTRACT_NO);
        assertThat(testContract.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testContract.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testContract.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testContract.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testContract.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testContract.getNationalId()).isEqualTo(UPDATED_NATIONAL_ID);
        assertThat(testContract.getPassportNo()).isEqualTo(UPDATED_PASSPORT_NO);
        assertThat(testContract.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testContract.getTradeLicense()).isEqualTo(UPDATED_TRADE_LICENSE);
        assertThat(testContract.getSignDate()).isEqualTo(UPDATED_SIGN_DATE);
        assertThat(testContract.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContract.getActualContractedAmount()).isEqualByComparingTo(UPDATED_ACTUAL_CONTRACTED_AMOUNT);
        assertThat(testContract.getInterestPercentage()).isEqualByComparingTo(UPDATED_INTEREST_PERCENTAGE);
        assertThat(testContract.getContractAmount()).isEqualByComparingTo(UPDATED_CONTRACT_AMOUNT);
    }

    @Test
    @Transactional
    void patchNonExistingContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContract() throws Exception {
        int databaseSizeBeforeUpdate = contractRepository.findAll().size();
        contract.setId(count.incrementAndGet());

        // Create the Contract
        ContractDTO contractDTO = contractMapper.toDto(contract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contractDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contract in the database
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContract() throws Exception {
        // Initialize the database
        contractRepository.saveAndFlush(contract);

        int databaseSizeBeforeDelete = contractRepository.findAll().size();

        // Delete the contract
        restContractMockMvc
            .perform(delete(ENTITY_API_URL_ID, contract.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contract> contractList = contractRepository.findAll();
        assertThat(contractList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
