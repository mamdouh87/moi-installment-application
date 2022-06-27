package ae.isoft.moi.installment.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.Request;
import ae.isoft.moi.installment.repository.RequestRepository;
import ae.isoft.moi.installment.service.dto.RequestDTO;
import ae.isoft.moi.installment.service.mapper.RequestMapper;
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
 * Integration tests for the {@link RequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestResourceIT {

    private static final String DEFAULT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_SERVICE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_SERVICE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_VEHICLE_TYPE_ID = 1;
    private static final Integer UPDATED_VEHICLE_TYPE_ID = 2;

    private static final Integer DEFAULT_LICENSE_TYPE_ID = 1;
    private static final Integer UPDATED_LICENSE_TYPE_ID = 2;

    private static final String DEFAULT_PLATE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTOR_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTOR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CHASSIS_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHASSIS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TRAFFIC_UNIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_UNIT_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .requestNo(DEFAULT_REQUEST_NO)
            .requestServiceCode(DEFAULT_REQUEST_SERVICE_CODE)
            .requestDescription(DEFAULT_REQUEST_DESCRIPTION)
            .vehicleTypeId(DEFAULT_VEHICLE_TYPE_ID)
            .licenseTypeId(DEFAULT_LICENSE_TYPE_ID)
            .plateNo(DEFAULT_PLATE_NO)
            .motorNo(DEFAULT_MOTOR_NO)
            .chassisNo(DEFAULT_CHASSIS_NO)
            .trafficUnitCode(DEFAULT_TRAFFIC_UNIT_CODE);
        return request;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createUpdatedEntity(EntityManager em) {
        Request request = new Request()
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .vehicleTypeId(UPDATED_VEHICLE_TYPE_ID)
            .licenseTypeId(UPDATED_LICENSE_TYPE_ID)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        return request;
    }

    @BeforeEach
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();
        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);
        restRequestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testRequest.getRequestServiceCode()).isEqualTo(DEFAULT_REQUEST_SERVICE_CODE);
        assertThat(testRequest.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testRequest.getVehicleTypeId()).isEqualTo(DEFAULT_VEHICLE_TYPE_ID);
        assertThat(testRequest.getLicenseTypeId()).isEqualTo(DEFAULT_LICENSE_TYPE_ID);
        assertThat(testRequest.getPlateNo()).isEqualTo(DEFAULT_PLATE_NO);
        assertThat(testRequest.getMotorNo()).isEqualTo(DEFAULT_MOTOR_NO);
        assertThat(testRequest.getChassisNo()).isEqualTo(DEFAULT_CHASSIS_NO);
        assertThat(testRequest.getTrafficUnitCode()).isEqualTo(DEFAULT_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void createRequestWithExistingId() throws Exception {
        // Create the Request with an existing ID
        request.setId(1L);
        RequestDTO requestDTO = requestMapper.toDto(request);

        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO)))
            .andExpect(jsonPath("$.[*].requestServiceCode").value(hasItem(DEFAULT_REQUEST_SERVICE_CODE)))
            .andExpect(jsonPath("$.[*].requestDescription").value(hasItem(DEFAULT_REQUEST_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].vehicleTypeId").value(hasItem(DEFAULT_VEHICLE_TYPE_ID)))
            .andExpect(jsonPath("$.[*].licenseTypeId").value(hasItem(DEFAULT_LICENSE_TYPE_ID)))
            .andExpect(jsonPath("$.[*].plateNo").value(hasItem(DEFAULT_PLATE_NO)))
            .andExpect(jsonPath("$.[*].motorNo").value(hasItem(DEFAULT_MOTOR_NO)))
            .andExpect(jsonPath("$.[*].chassisNo").value(hasItem(DEFAULT_CHASSIS_NO)))
            .andExpect(jsonPath("$.[*].trafficUnitCode").value(hasItem(DEFAULT_TRAFFIC_UNIT_CODE)));
    }

    @Test
    @Transactional
    void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc
            .perform(get(ENTITY_API_URL_ID, request.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO))
            .andExpect(jsonPath("$.requestServiceCode").value(DEFAULT_REQUEST_SERVICE_CODE))
            .andExpect(jsonPath("$.requestDescription").value(DEFAULT_REQUEST_DESCRIPTION))
            .andExpect(jsonPath("$.vehicleTypeId").value(DEFAULT_VEHICLE_TYPE_ID))
            .andExpect(jsonPath("$.licenseTypeId").value(DEFAULT_LICENSE_TYPE_ID))
            .andExpect(jsonPath("$.plateNo").value(DEFAULT_PLATE_NO))
            .andExpect(jsonPath("$.motorNo").value(DEFAULT_MOTOR_NO))
            .andExpect(jsonPath("$.chassisNo").value(DEFAULT_CHASSIS_NO))
            .andExpect(jsonPath("$.trafficUnitCode").value(DEFAULT_TRAFFIC_UNIT_CODE));
    }

    @Test
    @Transactional
    void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .vehicleTypeId(UPDATED_VEHICLE_TYPE_ID)
            .licenseTypeId(UPDATED_LICENSE_TYPE_ID)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        RequestDTO requestDTO = requestMapper.toDto(updatedRequest);

        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testRequest.getRequestServiceCode()).isEqualTo(UPDATED_REQUEST_SERVICE_CODE);
        assertThat(testRequest.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testRequest.getVehicleTypeId()).isEqualTo(UPDATED_VEHICLE_TYPE_ID);
        assertThat(testRequest.getLicenseTypeId()).isEqualTo(UPDATED_LICENSE_TYPE_ID);
        assertThat(testRequest.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testRequest.getMotorNo()).isEqualTo(UPDATED_MOTOR_NO);
        assertThat(testRequest.getChassisNo()).isEqualTo(UPDATED_CHASSIS_NO);
        assertThat(testRequest.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void putNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(requestDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestWithPatch() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request using partial update
        Request partialUpdatedRequest = new Request();
        partialUpdatedRequest.setId(request.getId());

        partialUpdatedRequest
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .vehicleTypeId(UPDATED_VEHICLE_TYPE_ID)
            .licenseTypeId(UPDATED_LICENSE_TYPE_ID)
            .plateNo(UPDATED_PLATE_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);

        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testRequest.getRequestServiceCode()).isEqualTo(UPDATED_REQUEST_SERVICE_CODE);
        assertThat(testRequest.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testRequest.getVehicleTypeId()).isEqualTo(UPDATED_VEHICLE_TYPE_ID);
        assertThat(testRequest.getLicenseTypeId()).isEqualTo(UPDATED_LICENSE_TYPE_ID);
        assertThat(testRequest.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testRequest.getMotorNo()).isEqualTo(DEFAULT_MOTOR_NO);
        assertThat(testRequest.getChassisNo()).isEqualTo(UPDATED_CHASSIS_NO);
        assertThat(testRequest.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void fullUpdateRequestWithPatch() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request using partial update
        Request partialUpdatedRequest = new Request();
        partialUpdatedRequest.setId(request.getId());

        partialUpdatedRequest
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .vehicleTypeId(UPDATED_VEHICLE_TYPE_ID)
            .licenseTypeId(UPDATED_LICENSE_TYPE_ID)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);

        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testRequest.getRequestServiceCode()).isEqualTo(UPDATED_REQUEST_SERVICE_CODE);
        assertThat(testRequest.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testRequest.getVehicleTypeId()).isEqualTo(UPDATED_VEHICLE_TYPE_ID);
        assertThat(testRequest.getLicenseTypeId()).isEqualTo(UPDATED_LICENSE_TYPE_ID);
        assertThat(testRequest.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testRequest.getMotorNo()).isEqualTo(UPDATED_MOTOR_NO);
        assertThat(testRequest.getChassisNo()).isEqualTo(UPDATED_CHASSIS_NO);
        assertThat(testRequest.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requestDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setId(count.incrementAndGet());

        // Create the Request
        RequestDTO requestDTO = requestMapper.toDto(request);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(requestDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Delete the request
        restRequestMockMvc
            .perform(delete(ENTITY_API_URL_ID, request.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
