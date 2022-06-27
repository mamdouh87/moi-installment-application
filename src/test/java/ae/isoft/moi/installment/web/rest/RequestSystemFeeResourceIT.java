package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.RequestSystemFee;
import ae.isoft.moi.installment.repository.RequestSystemFeeRepository;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDTO;
import ae.isoft.moi.installment.service.mapper.RequestSystemFeeMapper;
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
 * Integration tests for the {@link RequestSystemFeeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestSystemFeeResourceIT {

    private static final String DEFAULT_SYSTEMCODE = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEMCODE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_NAME_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SYSTEM_TOTAL_FEES = new BigDecimal(1);
    private static final BigDecimal UPDATED_SYSTEM_TOTAL_FEES = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/request-system-fees";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestSystemFeeRepository requestSystemFeeRepository;

    @Autowired
    private RequestSystemFeeMapper requestSystemFeeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestSystemFeeMockMvc;

    private RequestSystemFee requestSystemFee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestSystemFee createEntity(EntityManager em) {
        RequestSystemFee requestSystemFee = new RequestSystemFee()
            .systemcode(DEFAULT_SYSTEMCODE)
            .systemNameAr(DEFAULT_SYSTEM_NAME_AR)
            .systemNameEn(DEFAULT_SYSTEM_NAME_EN)
            .systemTotalFees(DEFAULT_SYSTEM_TOTAL_FEES);
        return requestSystemFee;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestSystemFee createUpdatedEntity(EntityManager em) {
        RequestSystemFee requestSystemFee = new RequestSystemFee()
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES);
        return requestSystemFee;
    }

    @BeforeEach
    public void initTest() {
        requestSystemFee = createEntity(em);
    }

    @Test
    @Transactional
    void createRequestSystemFee() throws Exception {
        int databaseSizeBeforeCreate = requestSystemFeeRepository.findAll().size();
        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);
        restRequestSystemFeeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeCreate + 1);
        RequestSystemFee testRequestSystemFee = requestSystemFeeList.get(requestSystemFeeList.size() - 1);
        assertThat(testRequestSystemFee.getSystemcode()).isEqualTo(DEFAULT_SYSTEMCODE);
        assertThat(testRequestSystemFee.getSystemNameAr()).isEqualTo(DEFAULT_SYSTEM_NAME_AR);
        assertThat(testRequestSystemFee.getSystemNameEn()).isEqualTo(DEFAULT_SYSTEM_NAME_EN);
        assertThat(testRequestSystemFee.getSystemTotalFees()).isEqualByComparingTo(DEFAULT_SYSTEM_TOTAL_FEES);
    }

    @Test
    @Transactional
    void createRequestSystemFeeWithExistingId() throws Exception {
        // Create the RequestSystemFee with an existing ID
        requestSystemFee.setId(1L);
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        int databaseSizeBeforeCreate = requestSystemFeeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestSystemFeeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRequestSystemFees() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        // Get all the requestSystemFeeList
        restRequestSystemFeeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestSystemFee.getId().intValue())))
            .andExpect(jsonPath("$.[*].systemcode").value(hasItem(DEFAULT_SYSTEMCODE)))
            .andExpect(jsonPath("$.[*].systemNameAr").value(hasItem(DEFAULT_SYSTEM_NAME_AR)))
            .andExpect(jsonPath("$.[*].systemNameEn").value(hasItem(DEFAULT_SYSTEM_NAME_EN)))
            .andExpect(jsonPath("$.[*].systemTotalFees").value(hasItem(sameNumber(DEFAULT_SYSTEM_TOTAL_FEES))));
    }

    @Test
    @Transactional
    void getRequestSystemFee() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        // Get the requestSystemFee
        restRequestSystemFeeMockMvc
            .perform(get(ENTITY_API_URL_ID, requestSystemFee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestSystemFee.getId().intValue()))
            .andExpect(jsonPath("$.systemcode").value(DEFAULT_SYSTEMCODE))
            .andExpect(jsonPath("$.systemNameAr").value(DEFAULT_SYSTEM_NAME_AR))
            .andExpect(jsonPath("$.systemNameEn").value(DEFAULT_SYSTEM_NAME_EN))
            .andExpect(jsonPath("$.systemTotalFees").value(sameNumber(DEFAULT_SYSTEM_TOTAL_FEES)));
    }

    @Test
    @Transactional
    void getNonExistingRequestSystemFee() throws Exception {
        // Get the requestSystemFee
        restRequestSystemFeeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequestSystemFee() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();

        // Update the requestSystemFee
        RequestSystemFee updatedRequestSystemFee = requestSystemFeeRepository.findById(requestSystemFee.getId()).get();
        // Disconnect from session so that the updates on updatedRequestSystemFee are not directly saved in db
        em.detach(updatedRequestSystemFee);
        updatedRequestSystemFee
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES);
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(updatedRequestSystemFee);

        restRequestSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestSystemFeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFee testRequestSystemFee = requestSystemFeeList.get(requestSystemFeeList.size() - 1);
        assertThat(testRequestSystemFee.getSystemcode()).isEqualTo(UPDATED_SYSTEMCODE);
        assertThat(testRequestSystemFee.getSystemNameAr()).isEqualTo(UPDATED_SYSTEM_NAME_AR);
        assertThat(testRequestSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testRequestSystemFee.getSystemTotalFees()).isEqualByComparingTo(UPDATED_SYSTEM_TOTAL_FEES);
    }

    @Test
    @Transactional
    void putNonExistingRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestSystemFeeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestSystemFeeWithPatch() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();

        // Update the requestSystemFee using partial update
        RequestSystemFee partialUpdatedRequestSystemFee = new RequestSystemFee();
        partialUpdatedRequestSystemFee.setId(requestSystemFee.getId());

        partialUpdatedRequestSystemFee.systemNameAr(UPDATED_SYSTEM_NAME_AR).systemNameEn(UPDATED_SYSTEM_NAME_EN);

        restRequestSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestSystemFee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestSystemFee))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFee testRequestSystemFee = requestSystemFeeList.get(requestSystemFeeList.size() - 1);
        assertThat(testRequestSystemFee.getSystemcode()).isEqualTo(DEFAULT_SYSTEMCODE);
        assertThat(testRequestSystemFee.getSystemNameAr()).isEqualTo(UPDATED_SYSTEM_NAME_AR);
        assertThat(testRequestSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testRequestSystemFee.getSystemTotalFees()).isEqualByComparingTo(DEFAULT_SYSTEM_TOTAL_FEES);
    }

    @Test
    @Transactional
    void fullUpdateRequestSystemFeeWithPatch() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();

        // Update the requestSystemFee using partial update
        RequestSystemFee partialUpdatedRequestSystemFee = new RequestSystemFee();
        partialUpdatedRequestSystemFee.setId(requestSystemFee.getId());

        partialUpdatedRequestSystemFee
            .systemcode(UPDATED_SYSTEMCODE)
            .systemNameAr(UPDATED_SYSTEM_NAME_AR)
            .systemNameEn(UPDATED_SYSTEM_NAME_EN)
            .systemTotalFees(UPDATED_SYSTEM_TOTAL_FEES);

        restRequestSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestSystemFee.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestSystemFee))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFee testRequestSystemFee = requestSystemFeeList.get(requestSystemFeeList.size() - 1);
        assertThat(testRequestSystemFee.getSystemcode()).isEqualTo(UPDATED_SYSTEMCODE);
        assertThat(testRequestSystemFee.getSystemNameAr()).isEqualTo(UPDATED_SYSTEM_NAME_AR);
        assertThat(testRequestSystemFee.getSystemNameEn()).isEqualTo(UPDATED_SYSTEM_NAME_EN);
        assertThat(testRequestSystemFee.getSystemTotalFees()).isEqualByComparingTo(UPDATED_SYSTEM_TOTAL_FEES);
    }

    @Test
    @Transactional
    void patchNonExistingRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requestSystemFeeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequestSystemFee() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeRepository.findAll().size();
        requestSystemFee.setId(count.incrementAndGet());

        // Create the RequestSystemFee
        RequestSystemFeeDTO requestSystemFeeDTO = requestSystemFeeMapper.toDto(requestSystemFee);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestSystemFee in the database
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequestSystemFee() throws Exception {
        // Initialize the database
        requestSystemFeeRepository.saveAndFlush(requestSystemFee);

        int databaseSizeBeforeDelete = requestSystemFeeRepository.findAll().size();

        // Delete the requestSystemFee
        restRequestSystemFeeMockMvc
            .perform(delete(ENTITY_API_URL_ID, requestSystemFee.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestSystemFee> requestSystemFeeList = requestSystemFeeRepository.findAll();
        assertThat(requestSystemFeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
