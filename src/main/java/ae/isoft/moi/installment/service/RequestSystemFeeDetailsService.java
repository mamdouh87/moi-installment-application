package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.RequestSystemFeeDetails;
import ae.isoft.moi.installment.repository.RequestSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDetailsDTO;
import ae.isoft.moi.installment.service.mapper.RequestSystemFeeDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RequestSystemFeeDetails}.
 */
@Service
@Transactional
public class RequestSystemFeeDetailsService {

    private final Logger log = LoggerFactory.getLogger(RequestSystemFeeDetailsService.class);

    private final RequestSystemFeeDetailsRepository requestSystemFeeDetailsRepository;

    private final RequestSystemFeeDetailsMapper requestSystemFeeDetailsMapper;

    public RequestSystemFeeDetailsService(
        RequestSystemFeeDetailsRepository requestSystemFeeDetailsRepository,
        RequestSystemFeeDetailsMapper requestSystemFeeDetailsMapper
    ) {
        this.requestSystemFeeDetailsRepository = requestSystemFeeDetailsRepository;
        this.requestSystemFeeDetailsMapper = requestSystemFeeDetailsMapper;
    }

    /**
     * Save a requestSystemFeeDetails.
     *
     * @param requestSystemFeeDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestSystemFeeDetailsDTO save(RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO) {
        log.debug("Request to save RequestSystemFeeDetails : {}", requestSystemFeeDetailsDTO);
        RequestSystemFeeDetails requestSystemFeeDetails = requestSystemFeeDetailsMapper.toEntity(requestSystemFeeDetailsDTO);
        requestSystemFeeDetails = requestSystemFeeDetailsRepository.save(requestSystemFeeDetails);
        return requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);
    }

    /**
     * Update a requestSystemFeeDetails.
     *
     * @param requestSystemFeeDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestSystemFeeDetailsDTO update(RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO) {
        log.debug("Request to save RequestSystemFeeDetails : {}", requestSystemFeeDetailsDTO);
        RequestSystemFeeDetails requestSystemFeeDetails = requestSystemFeeDetailsMapper.toEntity(requestSystemFeeDetailsDTO);
        requestSystemFeeDetails = requestSystemFeeDetailsRepository.save(requestSystemFeeDetails);
        return requestSystemFeeDetailsMapper.toDto(requestSystemFeeDetails);
    }

    /**
     * Partially update a requestSystemFeeDetails.
     *
     * @param requestSystemFeeDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RequestSystemFeeDetailsDTO> partialUpdate(RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO) {
        log.debug("Request to partially update RequestSystemFeeDetails : {}", requestSystemFeeDetailsDTO);

        return requestSystemFeeDetailsRepository
            .findById(requestSystemFeeDetailsDTO.getId())
            .map(existingRequestSystemFeeDetails -> {
                requestSystemFeeDetailsMapper.partialUpdate(existingRequestSystemFeeDetails, requestSystemFeeDetailsDTO);

                return existingRequestSystemFeeDetails;
            })
            .map(requestSystemFeeDetailsRepository::save)
            .map(requestSystemFeeDetailsMapper::toDto);
    }

    /**
     * Get all the requestSystemFeeDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestSystemFeeDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestSystemFeeDetails");
        return requestSystemFeeDetailsRepository.findAll(pageable).map(requestSystemFeeDetailsMapper::toDto);
    }

    /**
     * Get one requestSystemFeeDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestSystemFeeDetailsDTO> findOne(Long id) {
        log.debug("Request to get RequestSystemFeeDetails : {}", id);
        return requestSystemFeeDetailsRepository.findById(id).map(requestSystemFeeDetailsMapper::toDto);
    }

    /**
     * Delete the requestSystemFeeDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RequestSystemFeeDetails : {}", id);
        requestSystemFeeDetailsRepository.deleteById(id);
    }
}
