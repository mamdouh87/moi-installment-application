package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.TrafficContract;
import ae.isoft.moi.installment.repository.TrafficContractRepository;
import ae.isoft.moi.installment.service.dto.TrafficContractDTO;
import ae.isoft.moi.installment.service.mapper.TrafficContractMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TrafficContract}.
 */
@Service
@Transactional
public class TrafficContractService {

    private final Logger log = LoggerFactory.getLogger(TrafficContractService.class);

    private final TrafficContractRepository trafficContractRepository;

    private final TrafficContractMapper trafficContractMapper;

    public TrafficContractService(TrafficContractRepository trafficContractRepository, TrafficContractMapper trafficContractMapper) {
        this.trafficContractRepository = trafficContractRepository;
        this.trafficContractMapper = trafficContractMapper;
    }

    /**
     * Save a trafficContract.
     *
     * @param trafficContractDTO the entity to save.
     * @return the persisted entity.
     */
    public TrafficContractDTO save(TrafficContractDTO trafficContractDTO) {
        log.debug("Request to save TrafficContract : {}", trafficContractDTO);
        TrafficContract trafficContract = trafficContractMapper.toEntity(trafficContractDTO);
        trafficContract = trafficContractRepository.save(trafficContract);
        return trafficContractMapper.toDto(trafficContract);
    }

    /**
     * Update a trafficContract.
     *
     * @param trafficContractDTO the entity to save.
     * @return the persisted entity.
     */
    public TrafficContractDTO update(TrafficContractDTO trafficContractDTO) {
        log.debug("Request to save TrafficContract : {}", trafficContractDTO);
        TrafficContract trafficContract = trafficContractMapper.toEntity(trafficContractDTO);
        trafficContract = trafficContractRepository.save(trafficContract);
        return trafficContractMapper.toDto(trafficContract);
    }

    /**
     * Partially update a trafficContract.
     *
     * @param trafficContractDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrafficContractDTO> partialUpdate(TrafficContractDTO trafficContractDTO) {
        log.debug("Request to partially update TrafficContract : {}", trafficContractDTO);

        return trafficContractRepository
            .findById(trafficContractDTO.getId())
            .map(existingTrafficContract -> {
                trafficContractMapper.partialUpdate(existingTrafficContract, trafficContractDTO);

                return existingTrafficContract;
            })
            .map(trafficContractRepository::save)
            .map(trafficContractMapper::toDto);
    }

    /**
     * Get all the trafficContracts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrafficContractDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrafficContracts");
        return trafficContractRepository.findAll(pageable).map(trafficContractMapper::toDto);
    }

    /**
     * Get one trafficContract by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrafficContractDTO> findOne(Long id) {
        log.debug("Request to get TrafficContract : {}", id);
        return trafficContractRepository.findById(id).map(trafficContractMapper::toDto);
    }

    /**
     * Delete the trafficContract by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrafficContract : {}", id);
        trafficContractRepository.deleteById(id);
    }
}
