package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.InstallmentsPlan;
import ae.isoft.moi.installment.repository.InstallmentsPlanRepository;
import ae.isoft.moi.installment.service.dto.InstallmentsPlanDTO;
import ae.isoft.moi.installment.service.mapper.InstallmentsPlanMapper;
import java.math.BigDecimal;
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
 * Integration tests for the {@link InstallmentsPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InstallmentsPlanResourceIT {

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_NAME_EN = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_INSTALLMENTS = 1;
    private static final Integer UPDATED_NUMBER_OF_INSTALLMENTS = 2;

    private static final Integer DEFAULT_INSTALLMENT_INTERVAL_DAYS = 1;
    private static final Integer UPDATED_INSTALLMENT_INTERVAL_DAYS = 2;

    private static final BigDecimal DEFAULT_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTEREST_RATE = new BigDecimal(2);

    private static final Integer DEFAULT_INSTALLMENT_GRACE_DAYS = 1;
    private static final Integer UPDATED_INSTALLMENT_GRACE_DAYS = 2;

    private static final BigDecimal DEFAULT_DAILY_LATE_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DAILY_LATE_PERCENTAGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DAILY_LATE_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DAILY_LATE_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_TOTAL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_TOTAL_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_TOTAL_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_TOTAL_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_FIRST_INSTALLMENT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREATION_FEES = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREATION_FEES = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/installments-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InstallmentsPlanRepository installmentsPlanRepository;

    @Autowired
    private InstallmentsPlanMapper installmentsPlanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstallmentsPlanMockMvc;

    private InstallmentsPlan installmentsPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallmentsPlan createEntity(EntityManager em) {
        InstallmentsPlan installmentsPlan = new InstallmentsPlan()
            .status(DEFAULT_STATUS)
            .nameAr(DEFAULT_NAME_AR)
            .nameEn(DEFAULT_NAME_EN)
            .numberOfInstallments(DEFAULT_NUMBER_OF_INSTALLMENTS)
            .installmentIntervalDays(DEFAULT_INSTALLMENT_INTERVAL_DAYS)
            .interestRate(DEFAULT_INTEREST_RATE)
            .installmentGraceDays(DEFAULT_INSTALLMENT_GRACE_DAYS)
            .dailyLatePercentage(DEFAULT_DAILY_LATE_PERCENTAGE)
            .dailyLateFee(DEFAULT_DAILY_LATE_FEE)
            .maxTotalAmount(DEFAULT_MAX_TOTAL_AMOUNT)
            .minTotalAmount(DEFAULT_MIN_TOTAL_AMOUNT)
            .minFirstInstallmentAmount(DEFAULT_MIN_FIRST_INSTALLMENT_AMOUNT)
            .creationFees(DEFAULT_CREATION_FEES);
        return installmentsPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallmentsPlan createUpdatedEntity(EntityManager em) {
        InstallmentsPlan installmentsPlan = new InstallmentsPlan()
            .status(UPDATED_STATUS)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .installmentIntervalDays(UPDATED_INSTALLMENT_INTERVAL_DAYS)
            .interestRate(UPDATED_INTEREST_RATE)
            .installmentGraceDays(UPDATED_INSTALLMENT_GRACE_DAYS)
            .dailyLatePercentage(UPDATED_DAILY_LATE_PERCENTAGE)
            .dailyLateFee(UPDATED_DAILY_LATE_FEE)
            .maxTotalAmount(UPDATED_MAX_TOTAL_AMOUNT)
            .minTotalAmount(UPDATED_MIN_TOTAL_AMOUNT)
            .minFirstInstallmentAmount(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT)
            .creationFees(UPDATED_CREATION_FEES);
        return installmentsPlan;
    }

    @BeforeEach
    public void initTest() {
        installmentsPlan = createEntity(em);
    }

    @Test
    @Transactional
    void createInstallmentsPlan() throws Exception {
        int databaseSizeBeforeCreate = installmentsPlanRepository.findAll().size();
        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);
        restInstallmentsPlanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isCreated());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeCreate + 1);
        InstallmentsPlan testInstallmentsPlan = installmentsPlanList.get(installmentsPlanList.size() - 1);
        assertThat(testInstallmentsPlan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInstallmentsPlan.getNameAr()).isEqualTo(DEFAULT_NAME_AR);
        assertThat(testInstallmentsPlan.getNameEn()).isEqualTo(DEFAULT_NAME_EN);
        assertThat(testInstallmentsPlan.getNumberOfInstallments()).isEqualTo(DEFAULT_NUMBER_OF_INSTALLMENTS);
        assertThat(testInstallmentsPlan.getInstallmentIntervalDays()).isEqualTo(DEFAULT_INSTALLMENT_INTERVAL_DAYS);
        assertThat(testInstallmentsPlan.getInterestRate()).isEqualByComparingTo(DEFAULT_INTEREST_RATE);
        assertThat(testInstallmentsPlan.getInstallmentGraceDays()).isEqualTo(DEFAULT_INSTALLMENT_GRACE_DAYS);
        assertThat(testInstallmentsPlan.getDailyLatePercentage()).isEqualByComparingTo(DEFAULT_DAILY_LATE_PERCENTAGE);
        assertThat(testInstallmentsPlan.getDailyLateFee()).isEqualByComparingTo(DEFAULT_DAILY_LATE_FEE);
        assertThat(testInstallmentsPlan.getMaxTotalAmount()).isEqualByComparingTo(DEFAULT_MAX_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinTotalAmount()).isEqualByComparingTo(DEFAULT_MIN_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinFirstInstallmentAmount()).isEqualByComparingTo(DEFAULT_MIN_FIRST_INSTALLMENT_AMOUNT);
        assertThat(testInstallmentsPlan.getCreationFees()).isEqualByComparingTo(DEFAULT_CREATION_FEES);
    }

    @Test
    @Transactional
    void createInstallmentsPlanWithExistingId() throws Exception {
        // Create the InstallmentsPlan with an existing ID
        installmentsPlan.setId(1L);
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        int databaseSizeBeforeCreate = installmentsPlanRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstallmentsPlanMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInstallmentsPlans() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        // Get all the installmentsPlanList
        restInstallmentsPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(installmentsPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].nameAr").value(hasItem(DEFAULT_NAME_AR)))
            .andExpect(jsonPath("$.[*].nameEn").value(hasItem(DEFAULT_NAME_EN)))
            .andExpect(jsonPath("$.[*].numberOfInstallments").value(hasItem(DEFAULT_NUMBER_OF_INSTALLMENTS)))
            .andExpect(jsonPath("$.[*].installmentIntervalDays").value(hasItem(DEFAULT_INSTALLMENT_INTERVAL_DAYS)))
            .andExpect(jsonPath("$.[*].interestRate").value(hasItem(sameNumber(DEFAULT_INTEREST_RATE))))
            .andExpect(jsonPath("$.[*].installmentGraceDays").value(hasItem(DEFAULT_INSTALLMENT_GRACE_DAYS)))
            .andExpect(jsonPath("$.[*].dailyLatePercentage").value(hasItem(sameNumber(DEFAULT_DAILY_LATE_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].dailyLateFee").value(hasItem(sameNumber(DEFAULT_DAILY_LATE_FEE))))
            .andExpect(jsonPath("$.[*].maxTotalAmount").value(hasItem(sameNumber(DEFAULT_MAX_TOTAL_AMOUNT))))
            .andExpect(jsonPath("$.[*].minTotalAmount").value(hasItem(sameNumber(DEFAULT_MIN_TOTAL_AMOUNT))))
            .andExpect(jsonPath("$.[*].minFirstInstallmentAmount").value(hasItem(sameNumber(DEFAULT_MIN_FIRST_INSTALLMENT_AMOUNT))))
            .andExpect(jsonPath("$.[*].creationFees").value(hasItem(sameNumber(DEFAULT_CREATION_FEES))));
    }

    @Test
    @Transactional
    void getInstallmentsPlan() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        // Get the installmentsPlan
        restInstallmentsPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, installmentsPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(installmentsPlan.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.nameAr").value(DEFAULT_NAME_AR))
            .andExpect(jsonPath("$.nameEn").value(DEFAULT_NAME_EN))
            .andExpect(jsonPath("$.numberOfInstallments").value(DEFAULT_NUMBER_OF_INSTALLMENTS))
            .andExpect(jsonPath("$.installmentIntervalDays").value(DEFAULT_INSTALLMENT_INTERVAL_DAYS))
            .andExpect(jsonPath("$.interestRate").value(sameNumber(DEFAULT_INTEREST_RATE)))
            .andExpect(jsonPath("$.installmentGraceDays").value(DEFAULT_INSTALLMENT_GRACE_DAYS))
            .andExpect(jsonPath("$.dailyLatePercentage").value(sameNumber(DEFAULT_DAILY_LATE_PERCENTAGE)))
            .andExpect(jsonPath("$.dailyLateFee").value(sameNumber(DEFAULT_DAILY_LATE_FEE)))
            .andExpect(jsonPath("$.maxTotalAmount").value(sameNumber(DEFAULT_MAX_TOTAL_AMOUNT)))
            .andExpect(jsonPath("$.minTotalAmount").value(sameNumber(DEFAULT_MIN_TOTAL_AMOUNT)))
            .andExpect(jsonPath("$.minFirstInstallmentAmount").value(sameNumber(DEFAULT_MIN_FIRST_INSTALLMENT_AMOUNT)))
            .andExpect(jsonPath("$.creationFees").value(sameNumber(DEFAULT_CREATION_FEES)));
    }

    @Test
    @Transactional
    void getNonExistingInstallmentsPlan() throws Exception {
        // Get the installmentsPlan
        restInstallmentsPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInstallmentsPlan() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();

        // Update the installmentsPlan
        InstallmentsPlan updatedInstallmentsPlan = installmentsPlanRepository.findById(installmentsPlan.getId()).get();
        // Disconnect from session so that the updates on updatedInstallmentsPlan are not directly saved in db
        em.detach(updatedInstallmentsPlan);
        updatedInstallmentsPlan
            .status(UPDATED_STATUS)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .installmentIntervalDays(UPDATED_INSTALLMENT_INTERVAL_DAYS)
            .interestRate(UPDATED_INTEREST_RATE)
            .installmentGraceDays(UPDATED_INSTALLMENT_GRACE_DAYS)
            .dailyLatePercentage(UPDATED_DAILY_LATE_PERCENTAGE)
            .dailyLateFee(UPDATED_DAILY_LATE_FEE)
            .maxTotalAmount(UPDATED_MAX_TOTAL_AMOUNT)
            .minTotalAmount(UPDATED_MIN_TOTAL_AMOUNT)
            .minFirstInstallmentAmount(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT)
            .creationFees(UPDATED_CREATION_FEES);
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(updatedInstallmentsPlan);

        restInstallmentsPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, installmentsPlanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isOk());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
        InstallmentsPlan testInstallmentsPlan = installmentsPlanList.get(installmentsPlanList.size() - 1);
        assertThat(testInstallmentsPlan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInstallmentsPlan.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInstallmentsPlan.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInstallmentsPlan.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testInstallmentsPlan.getInstallmentIntervalDays()).isEqualTo(UPDATED_INSTALLMENT_INTERVAL_DAYS);
        assertThat(testInstallmentsPlan.getInterestRate()).isEqualByComparingTo(UPDATED_INTEREST_RATE);
        assertThat(testInstallmentsPlan.getInstallmentGraceDays()).isEqualTo(UPDATED_INSTALLMENT_GRACE_DAYS);
        assertThat(testInstallmentsPlan.getDailyLatePercentage()).isEqualByComparingTo(UPDATED_DAILY_LATE_PERCENTAGE);
        assertThat(testInstallmentsPlan.getDailyLateFee()).isEqualByComparingTo(UPDATED_DAILY_LATE_FEE);
        assertThat(testInstallmentsPlan.getMaxTotalAmount()).isEqualByComparingTo(UPDATED_MAX_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinTotalAmount()).isEqualByComparingTo(UPDATED_MIN_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinFirstInstallmentAmount()).isEqualByComparingTo(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT);
        assertThat(testInstallmentsPlan.getCreationFees()).isEqualByComparingTo(UPDATED_CREATION_FEES);
    }

    @Test
    @Transactional
    void putNonExistingInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, installmentsPlanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInstallmentsPlanWithPatch() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();

        // Update the installmentsPlan using partial update
        InstallmentsPlan partialUpdatedInstallmentsPlan = new InstallmentsPlan();
        partialUpdatedInstallmentsPlan.setId(installmentsPlan.getId());

        partialUpdatedInstallmentsPlan
            .status(UPDATED_STATUS)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .installmentIntervalDays(UPDATED_INSTALLMENT_INTERVAL_DAYS)
            .interestRate(UPDATED_INTEREST_RATE)
            .dailyLateFee(UPDATED_DAILY_LATE_FEE)
            .maxTotalAmount(UPDATED_MAX_TOTAL_AMOUNT)
            .minFirstInstallmentAmount(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT);

        restInstallmentsPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstallmentsPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstallmentsPlan))
            )
            .andExpect(status().isOk());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
        InstallmentsPlan testInstallmentsPlan = installmentsPlanList.get(installmentsPlanList.size() - 1);
        assertThat(testInstallmentsPlan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInstallmentsPlan.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInstallmentsPlan.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInstallmentsPlan.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testInstallmentsPlan.getInstallmentIntervalDays()).isEqualTo(UPDATED_INSTALLMENT_INTERVAL_DAYS);
        assertThat(testInstallmentsPlan.getInterestRate()).isEqualByComparingTo(UPDATED_INTEREST_RATE);
        assertThat(testInstallmentsPlan.getInstallmentGraceDays()).isEqualTo(DEFAULT_INSTALLMENT_GRACE_DAYS);
        assertThat(testInstallmentsPlan.getDailyLatePercentage()).isEqualByComparingTo(DEFAULT_DAILY_LATE_PERCENTAGE);
        assertThat(testInstallmentsPlan.getDailyLateFee()).isEqualByComparingTo(UPDATED_DAILY_LATE_FEE);
        assertThat(testInstallmentsPlan.getMaxTotalAmount()).isEqualByComparingTo(UPDATED_MAX_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinTotalAmount()).isEqualByComparingTo(DEFAULT_MIN_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinFirstInstallmentAmount()).isEqualByComparingTo(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT);
        assertThat(testInstallmentsPlan.getCreationFees()).isEqualByComparingTo(DEFAULT_CREATION_FEES);
    }

    @Test
    @Transactional
    void fullUpdateInstallmentsPlanWithPatch() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();

        // Update the installmentsPlan using partial update
        InstallmentsPlan partialUpdatedInstallmentsPlan = new InstallmentsPlan();
        partialUpdatedInstallmentsPlan.setId(installmentsPlan.getId());

        partialUpdatedInstallmentsPlan
            .status(UPDATED_STATUS)
            .nameAr(UPDATED_NAME_AR)
            .nameEn(UPDATED_NAME_EN)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .installmentIntervalDays(UPDATED_INSTALLMENT_INTERVAL_DAYS)
            .interestRate(UPDATED_INTEREST_RATE)
            .installmentGraceDays(UPDATED_INSTALLMENT_GRACE_DAYS)
            .dailyLatePercentage(UPDATED_DAILY_LATE_PERCENTAGE)
            .dailyLateFee(UPDATED_DAILY_LATE_FEE)
            .maxTotalAmount(UPDATED_MAX_TOTAL_AMOUNT)
            .minTotalAmount(UPDATED_MIN_TOTAL_AMOUNT)
            .minFirstInstallmentAmount(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT)
            .creationFees(UPDATED_CREATION_FEES);

        restInstallmentsPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInstallmentsPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInstallmentsPlan))
            )
            .andExpect(status().isOk());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
        InstallmentsPlan testInstallmentsPlan = installmentsPlanList.get(installmentsPlanList.size() - 1);
        assertThat(testInstallmentsPlan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInstallmentsPlan.getNameAr()).isEqualTo(UPDATED_NAME_AR);
        assertThat(testInstallmentsPlan.getNameEn()).isEqualTo(UPDATED_NAME_EN);
        assertThat(testInstallmentsPlan.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testInstallmentsPlan.getInstallmentIntervalDays()).isEqualTo(UPDATED_INSTALLMENT_INTERVAL_DAYS);
        assertThat(testInstallmentsPlan.getInterestRate()).isEqualByComparingTo(UPDATED_INTEREST_RATE);
        assertThat(testInstallmentsPlan.getInstallmentGraceDays()).isEqualTo(UPDATED_INSTALLMENT_GRACE_DAYS);
        assertThat(testInstallmentsPlan.getDailyLatePercentage()).isEqualByComparingTo(UPDATED_DAILY_LATE_PERCENTAGE);
        assertThat(testInstallmentsPlan.getDailyLateFee()).isEqualByComparingTo(UPDATED_DAILY_LATE_FEE);
        assertThat(testInstallmentsPlan.getMaxTotalAmount()).isEqualByComparingTo(UPDATED_MAX_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinTotalAmount()).isEqualByComparingTo(UPDATED_MIN_TOTAL_AMOUNT);
        assertThat(testInstallmentsPlan.getMinFirstInstallmentAmount()).isEqualByComparingTo(UPDATED_MIN_FIRST_INSTALLMENT_AMOUNT);
        assertThat(testInstallmentsPlan.getCreationFees()).isEqualByComparingTo(UPDATED_CREATION_FEES);
    }

    @Test
    @Transactional
    void patchNonExistingInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, installmentsPlanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInstallmentsPlan() throws Exception {
        int databaseSizeBeforeUpdate = installmentsPlanRepository.findAll().size();
        installmentsPlan.setId(count.incrementAndGet());

        // Create the InstallmentsPlan
        InstallmentsPlanDTO installmentsPlanDTO = installmentsPlanMapper.toDto(installmentsPlan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInstallmentsPlanMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(installmentsPlanDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InstallmentsPlan in the database
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInstallmentsPlan() throws Exception {
        // Initialize the database
        installmentsPlanRepository.saveAndFlush(installmentsPlan);

        int databaseSizeBeforeDelete = installmentsPlanRepository.findAll().size();

        // Delete the installmentsPlan
        restInstallmentsPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, installmentsPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstallmentsPlan> installmentsPlanList = installmentsPlanRepository.findAll();
        assertThat(installmentsPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
