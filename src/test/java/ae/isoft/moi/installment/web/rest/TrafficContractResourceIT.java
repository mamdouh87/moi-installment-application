package ae.isoft.moi.installment.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ae.isoft.moi.installment.IntegrationTest;
import ae.isoft.moi.installment.domain.TrafficContract;
import ae.isoft.moi.installment.repository.TrafficContractRepository;
import ae.isoft.moi.installment.service.dto.TrafficContractDTO;
import ae.isoft.moi.installment.service.mapper.TrafficContractMapper;
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
 * Integration tests for the {@link TrafficContractResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TrafficContractResourceIT {

    private static final Integer DEFAULT_REQUEST_ID = 1;
    private static final Integer UPDATED_REQUEST_ID = 2;

    private static final String DEFAULT_REQUEST_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_SERVICE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_SERVICE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PLATE_NO = "AAAAAAAAAA";
    private static final String UPDATED_PLATE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MOTOR_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOTOR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CHASSIS_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHASSIS_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TRAFFIC_UNIT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRAFFIC_UNIT_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/traffic-contracts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TrafficContractRepository trafficContractRepository;

    @Autowired
    private TrafficContractMapper trafficContractMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTrafficContractMockMvc;

    private TrafficContract trafficContract;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrafficContract createEntity(EntityManager em) {
        TrafficContract trafficContract = new TrafficContract()
            .requestId(DEFAULT_REQUEST_ID)
            .requestNo(DEFAULT_REQUEST_NO)
            .requestServiceCode(DEFAULT_REQUEST_SERVICE_CODE)
            .requestDescription(DEFAULT_REQUEST_DESCRIPTION)
            .plateNo(DEFAULT_PLATE_NO)
            .motorNo(DEFAULT_MOTOR_NO)
            .chassisNo(DEFAULT_CHASSIS_NO)
            .trafficUnitCode(DEFAULT_TRAFFIC_UNIT_CODE);
        return trafficContract;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TrafficContract createUpdatedEntity(EntityManager em) {
        TrafficContract trafficContract = new TrafficContract()
            .requestId(UPDATED_REQUEST_ID)
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        return trafficContract;
    }

    @BeforeEach
    public void initTest() {
        trafficContract = createEntity(em);
    }

    @Test
    @Transactional
    void createTrafficContract() throws Exception {
        int databaseSizeBeforeCreate = trafficContractRepository.findAll().size();
        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);
        restTrafficContractMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeCreate + 1);
        TrafficContract testTrafficContract = trafficContractList.get(trafficContractList.size() - 1);
        assertThat(testTrafficContract.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testTrafficContract.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testTrafficContract.getRequestServiceCode()).isEqualTo(DEFAULT_REQUEST_SERVICE_CODE);
        assertThat(testTrafficContract.getRequestDescription()).isEqualTo(DEFAULT_REQUEST_DESCRIPTION);
        assertThat(testTrafficContract.getPlateNo()).isEqualTo(DEFAULT_PLATE_NO);
        assertThat(testTrafficContract.getMotorNo()).isEqualTo(DEFAULT_MOTOR_NO);
        assertThat(testTrafficContract.getChassisNo()).isEqualTo(DEFAULT_CHASSIS_NO);
        assertThat(testTrafficContract.getTrafficUnitCode()).isEqualTo(DEFAULT_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void createTrafficContractWithExistingId() throws Exception {
        // Create the TrafficContract with an existing ID
        trafficContract.setId(1L);
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        int databaseSizeBeforeCreate = trafficContractRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrafficContractMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTrafficContracts() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        // Get all the trafficContractList
        restTrafficContractMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trafficContract.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestId").value(hasItem(DEFAULT_REQUEST_ID)))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO)))
            .andExpect(jsonPath("$.[*].requestServiceCode").value(hasItem(DEFAULT_REQUEST_SERVICE_CODE)))
            .andExpect(jsonPath("$.[*].requestDescription").value(hasItem(DEFAULT_REQUEST_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].plateNo").value(hasItem(DEFAULT_PLATE_NO)))
            .andExpect(jsonPath("$.[*].motorNo").value(hasItem(DEFAULT_MOTOR_NO)))
            .andExpect(jsonPath("$.[*].chassisNo").value(hasItem(DEFAULT_CHASSIS_NO)))
            .andExpect(jsonPath("$.[*].trafficUnitCode").value(hasItem(DEFAULT_TRAFFIC_UNIT_CODE)));
    }

    @Test
    @Transactional
    void getTrafficContract() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        // Get the trafficContract
        restTrafficContractMockMvc
            .perform(get(ENTITY_API_URL_ID, trafficContract.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trafficContract.getId().intValue()))
            .andExpect(jsonPath("$.requestId").value(DEFAULT_REQUEST_ID))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO))
            .andExpect(jsonPath("$.requestServiceCode").value(DEFAULT_REQUEST_SERVICE_CODE))
            .andExpect(jsonPath("$.requestDescription").value(DEFAULT_REQUEST_DESCRIPTION))
            .andExpect(jsonPath("$.plateNo").value(DEFAULT_PLATE_NO))
            .andExpect(jsonPath("$.motorNo").value(DEFAULT_MOTOR_NO))
            .andExpect(jsonPath("$.chassisNo").value(DEFAULT_CHASSIS_NO))
            .andExpect(jsonPath("$.trafficUnitCode").value(DEFAULT_TRAFFIC_UNIT_CODE));
    }

    @Test
    @Transactional
    void getNonExistingTrafficContract() throws Exception {
        // Get the trafficContract
        restTrafficContractMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTrafficContract() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();

        // Update the trafficContract
        TrafficContract updatedTrafficContract = trafficContractRepository.findById(trafficContract.getId()).get();
        // Disconnect from session so that the updates on updatedTrafficContract are not directly saved in db
        em.detach(updatedTrafficContract);
        updatedTrafficContract
            .requestId(UPDATED_REQUEST_ID)
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(updatedTrafficContract);

        restTrafficContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trafficContractDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isOk());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
        TrafficContract testTrafficContract = trafficContractList.get(trafficContractList.size() - 1);
        assertThat(testTrafficContract.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testTrafficContract.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testTrafficContract.getRequestServiceCode()).isEqualTo(UPDATED_REQUEST_SERVICE_CODE);
        assertThat(testTrafficContract.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testTrafficContract.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testTrafficContract.getMotorNo()).isEqualTo(UPDATED_MOTOR_NO);
        assertThat(testTrafficContract.getChassisNo()).isEqualTo(UPDATED_CHASSIS_NO);
        assertThat(testTrafficContract.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void putNonExistingTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, trafficContractDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTrafficContractWithPatch() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();

        // Update the trafficContract using partial update
        TrafficContract partialUpdatedTrafficContract = new TrafficContract();
        partialUpdatedTrafficContract.setId(trafficContract.getId());

        partialUpdatedTrafficContract
            .requestId(UPDATED_REQUEST_ID)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .plateNo(UPDATED_PLATE_NO);

        restTrafficContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrafficContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrafficContract))
            )
            .andExpect(status().isOk());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
        TrafficContract testTrafficContract = trafficContractList.get(trafficContractList.size() - 1);
        assertThat(testTrafficContract.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testTrafficContract.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
        assertThat(testTrafficContract.getRequestServiceCode()).isEqualTo(DEFAULT_REQUEST_SERVICE_CODE);
        assertThat(testTrafficContract.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testTrafficContract.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testTrafficContract.getMotorNo()).isEqualTo(DEFAULT_MOTOR_NO);
        assertThat(testTrafficContract.getChassisNo()).isEqualTo(DEFAULT_CHASSIS_NO);
        assertThat(testTrafficContract.getTrafficUnitCode()).isEqualTo(DEFAULT_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void fullUpdateTrafficContractWithPatch() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();

        // Update the trafficContract using partial update
        TrafficContract partialUpdatedTrafficContract = new TrafficContract();
        partialUpdatedTrafficContract.setId(trafficContract.getId());

        partialUpdatedTrafficContract
            .requestId(UPDATED_REQUEST_ID)
            .requestNo(UPDATED_REQUEST_NO)
            .requestServiceCode(UPDATED_REQUEST_SERVICE_CODE)
            .requestDescription(UPDATED_REQUEST_DESCRIPTION)
            .plateNo(UPDATED_PLATE_NO)
            .motorNo(UPDATED_MOTOR_NO)
            .chassisNo(UPDATED_CHASSIS_NO)
            .trafficUnitCode(UPDATED_TRAFFIC_UNIT_CODE);

        restTrafficContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrafficContract.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrafficContract))
            )
            .andExpect(status().isOk());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
        TrafficContract testTrafficContract = trafficContractList.get(trafficContractList.size() - 1);
        assertThat(testTrafficContract.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testTrafficContract.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
        assertThat(testTrafficContract.getRequestServiceCode()).isEqualTo(UPDATED_REQUEST_SERVICE_CODE);
        assertThat(testTrafficContract.getRequestDescription()).isEqualTo(UPDATED_REQUEST_DESCRIPTION);
        assertThat(testTrafficContract.getPlateNo()).isEqualTo(UPDATED_PLATE_NO);
        assertThat(testTrafficContract.getMotorNo()).isEqualTo(UPDATED_MOTOR_NO);
        assertThat(testTrafficContract.getChassisNo()).isEqualTo(UPDATED_CHASSIS_NO);
        assertThat(testTrafficContract.getTrafficUnitCode()).isEqualTo(UPDATED_TRAFFIC_UNIT_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, trafficContractDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTrafficContract() throws Exception {
        int databaseSizeBeforeUpdate = trafficContractRepository.findAll().size();
        trafficContract.setId(count.incrementAndGet());

        // Create the TrafficContract
        TrafficContractDTO trafficContractDTO = trafficContractMapper.toDto(trafficContract);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTrafficContractMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(trafficContractDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TrafficContract in the database
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTrafficContract() throws Exception {
        // Initialize the database
        trafficContractRepository.saveAndFlush(trafficContract);

        int databaseSizeBeforeDelete = trafficContractRepository.findAll().size();

        // Delete the trafficContract
        restTrafficContractMockMvc
            .perform(delete(ENTITY_API_URL_ID, trafficContract.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TrafficContract> trafficContractList = trafficContractRepository.findAll();
        assertThat(trafficContractList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
