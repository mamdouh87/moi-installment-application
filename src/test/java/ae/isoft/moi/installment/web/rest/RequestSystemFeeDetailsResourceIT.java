package ae.isoft.moi.installment.web.rest;

import static ae.isoft.moi.installment.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.RequestSystemFeeDetails;
import ae.isoft.moi.installment.repository.RequestSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDetailsDTO;
import ae.isoft.moi.installment.service.mapper.RequestSystemFeeDetailsMapper;
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
 * Integration tests for the {@link RequestSystemFeeDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestSystemFeeDetailsResourceIT {

    private static final String DEFAULT_FEE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FEE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_FEE_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_FEE_NAME_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_FEE_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_FEE_AMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/request-system-fee-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestSystemFeeDetailsRepository requestSystemFeeDetailsRepository;

    @Autowired
    private RequestSystemFeeDetailsMapper requestSystemFeeDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestSystemFeeDetailsMockMvc;

    private RequestSystemFeeDetails requestSystemFeeDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestSystemFeeDetails createEntity(EntityManager em) {
        RequestSystemFeeDetails requestSystemFeeDetails = new RequestSystemFeeDetails()
            .feeCode(DEFAULT_FEE_CODE)
            .feeNameAr(DEFAULT_FEE_NAME_AR)
            .feeNameEn(DEFAULT_FEE_NAME_EN)
            .feeAmount(DEFAULT_FEE_AMOUNT);
        return requestSystemFeeDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestSystemFeeDetails createUpdatedEntity(EntityManager em) {
        RequestSystemFeeDetails requestSystemFeeDetails = new RequestSystemFeeDetails()
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT);
        return requestSystemFeeDetails;
    }

    @BeforeEach
    public void initTest() {
        requestSystemFeeDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeCreate = requestSystemFeeDetailsRepository.findAll().size();
        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);
        restRequestSystemFeeDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        RequestSystemFeeDetails testRequestSystemFeeDetails = requestSystemFeeDetailsList.get(requestSystemFeeDetailsList.size() - 1);
        assertThat(testRequestSystemFeeDetails.getFeeCode()).isEqualTo(DEFAULT_FEE_CODE);
        assertThat(testRequestSystemFeeDetails.getFeeNameAr()).isEqualTo(DEFAULT_FEE_NAME_AR);
        assertThat(testRequestSystemFeeDetails.getFeeNameEn()).isEqualTo(DEFAULT_FEE_NAME_EN);
        assertThat(testRequestSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(DEFAULT_FEE_AMOUNT);
    }

    @Test
    @Transactional
    void createRequestSystemFeeDetailsWithExistingId() throws Exception {
        // Create the RequestSystemFeeDetails with an existing ID
        requestSystemFeeDetails.setId(1L);
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        int databaseSizeBeforeCreate = requestSystemFeeDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestSystemFeeDetailsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRequestSystemFeeDetails() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        // Get all the requestSystemFeeDetailsList
        restRequestSystemFeeDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestSystemFeeDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].feeCode").value(hasItem(DEFAULT_FEE_CODE)))
            .andExpect(jsonPath("$.[*].feeNameAr").value(hasItem(DEFAULT_FEE_NAME_AR)))
            .andExpect(jsonPath("$.[*].feeNameEn").value(hasItem(DEFAULT_FEE_NAME_EN)))
            .andExpect(jsonPath("$.[*].feeAmount").value(hasItem(sameNumber(DEFAULT_FEE_AMOUNT))));
    }

    @Test
    @Transactional
    void getRequestSystemFeeDetails() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        // Get the requestSystemFeeDetails
        restRequestSystemFeeDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, requestSystemFeeDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestSystemFeeDetails.getId().intValue()))
            .andExpect(jsonPath("$.feeCode").value(DEFAULT_FEE_CODE))
            .andExpect(jsonPath("$.feeNameAr").value(DEFAULT_FEE_NAME_AR))
            .andExpect(jsonPath("$.feeNameEn").value(DEFAULT_FEE_NAME_EN))
            .andExpect(jsonPath("$.feeAmount").value(sameNumber(DEFAULT_FEE_AMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingRequestSystemFeeDetails() throws Exception {
        // Get the requestSystemFeeDetails
        restRequestSystemFeeDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequestSystemFeeDetails() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();

        // Update the requestSystemFeeDetails
        RequestSystemFeeDetails updatedRequestSystemFeeDetails = requestSystemFeeDetailsRepository
            .findById(requestSystemFeeDetails.getId())
            .get();
        // Disconnect from session so that the updates on updatedRequestSystemFeeDetails are not directly saved in db
        em.detach(updatedRequestSystemFeeDetails);
        updatedRequestSystemFeeDetails
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT);
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(updatedRequestSystemFeeDetails);

        restRequestSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestSystemFeeDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFeeDetails testRequestSystemFeeDetails = requestSystemFeeDetailsList.get(requestSystemFeeDetailsList.size() - 1);
        assertThat(testRequestSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testRequestSystemFeeDetails.getFeeNameAr()).isEqualTo(UPDATED_FEE_NAME_AR);
        assertThat(testRequestSystemFeeDetails.getFeeNameEn()).isEqualTo(UPDATED_FEE_NAME_EN);
        assertThat(testRequestSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(UPDATED_FEE_AMOUNT);
    }

    @Test
    @Transactional
    void putNonExistingRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestSystemFeeDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestSystemFeeDetailsWithPatch() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();

        // Update the requestSystemFeeDetails using partial update
        RequestSystemFeeDetails partialUpdatedRequestSystemFeeDetails = new RequestSystemFeeDetails();
        partialUpdatedRequestSystemFeeDetails.setId(requestSystemFeeDetails.getId());

        partialUpdatedRequestSystemFeeDetails.feeCode(UPDATED_FEE_CODE);

        restRequestSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestSystemFeeDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestSystemFeeDetails))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFeeDetails testRequestSystemFeeDetails = requestSystemFeeDetailsList.get(requestSystemFeeDetailsList.size() - 1);
        assertThat(testRequestSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testRequestSystemFeeDetails.getFeeNameAr()).isEqualTo(DEFAULT_FEE_NAME_AR);
        assertThat(testRequestSystemFeeDetails.getFeeNameEn()).isEqualTo(DEFAULT_FEE_NAME_EN);
        assertThat(testRequestSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(DEFAULT_FEE_AMOUNT);
    }

    @Test
    @Transactional
    void fullUpdateRequestSystemFeeDetailsWithPatch() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();

        // Update the requestSystemFeeDetails using partial update
        RequestSystemFeeDetails partialUpdatedRequestSystemFeeDetails = new RequestSystemFeeDetails();
        partialUpdatedRequestSystemFeeDetails.setId(requestSystemFeeDetails.getId());

        partialUpdatedRequestSystemFeeDetails
            .feeCode(UPDATED_FEE_CODE)
            .feeNameAr(UPDATED_FEE_NAME_AR)
            .feeNameEn(UPDATED_FEE_NAME_EN)
            .feeAmount(UPDATED_FEE_AMOUNT);

        restRequestSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestSystemFeeDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestSystemFeeDetails))
            )
            .andExpect(status().isOk());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        RequestSystemFeeDetails testRequestSystemFeeDetails = requestSystemFeeDetailsList.get(requestSystemFeeDetailsList.size() - 1);
        assertThat(testRequestSystemFeeDetails.getFeeCode()).isEqualTo(UPDATED_FEE_CODE);
        assertThat(testRequestSystemFeeDetails.getFeeNameAr()).isEqualTo(UPDATED_FEE_NAME_AR);
        assertThat(testRequestSystemFeeDetails.getFeeNameEn()).isEqualTo(UPDATED_FEE_NAME_EN);
        assertThat(testRequestSystemFeeDetails.getFeeAmount()).isEqualByComparingTo(UPDATED_FEE_AMOUNT);
    }

    @Test
    @Transactional
    void patchNonExistingRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requestSystemFeeDetailsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequestSystemFeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = requestSystemFeeDetailsRepository.findAll().size();
        requestSystemFeeDetails.setId(count.incrementAndGet());

        // Create the RequestSystemFeeDetails
        RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO = requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestSystemFeeDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestSystemFeeDetailsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestSystemFeeDetails in the database
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequestSystemFeeDetails() throws Exception {
        // Initialize the database
        requestSystemFeeDetailsRepository.saveAndFlush(requestSystemFeeDetails);

        int databaseSizeBeforeDelete = requestSystemFeeDetailsRepository.findAll().size();

        // Delete the requestSystemFeeDetails
        restRequestSystemFeeDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, requestSystemFeeDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestSystemFeeDetails> requestSystemFeeDetailsList = requestSystemFeeDetailsRepository.findAll();
        assertThat(requestSystemFeeDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
