package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.RequestSystemFee;
import ae.isoft.moi.installment.repository.RequestSystemFeeRepository;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDTO;
import ae.isoft.moi.installment.service.mapper.RequestSystemFeeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RequestSystemFee}.
 */
@Service
@Transactional
public class RequestSystemFeeService {

    private final Logger log = LoggerFactory.getLogger(RequestSystemFeeService.class);

    private final RequestSystemFeeRepository requestSystemFeeRepository;

    private final RequestSystemFeeMapper requestSystemFeeMapper;

    public RequestSystemFeeService(RequestSystemFeeRepository requestSystemFeeRepository, RequestSystemFeeMapper requestSystemFeeMapper) {
        this.requestSystemFeeRepository = requestSystemFeeRepository;
        this.requestSystemFeeMapper = requestSystemFeeMapper;
    }

    /**
     * Save a requestSystemFee.
     *
     * @param requestSystemFeeDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestSystemFeeDTO save(RequestSystemFeeDTO requestSystemFeeDTO) {
        log.debug("Request to save RequestSystemFee : {}", requestSystemFeeDTO);
        RequestSystemFee requestSystemFee = requestSystemFeeMapper.toEntity(requestSystemFeeDTO);
        requestSystemFee = requestSystemFeeRepository.save(requestSystemFee);
        return requestSystemFeeMapper.toDto(requestSystemFee);
    }

    /**
     * Update a requestSystemFee.
     *
     * @param requestSystemFeeDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestSystemFeeDTO update(RequestSystemFeeDTO requestSystemFeeDTO) {
        log.debug("Request to save RequestSystemFee : {}", requestSystemFeeDTO);
        RequestSystemFee requestSystemFee = requestSystemFeeMapper.toEntity(requestSystemFeeDTO);
        requestSystemFee = requestSystemFeeRepository.save(requestSystemFee);
        return requestSystemFeeMapper.toDto(requestSystemFee);
    }

    /**
     * Partially update a requestSystemFee.
     *
     * @param requestSystemFeeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RequestSystemFeeDTO> partialUpdate(RequestSystemFeeDTO requestSystemFeeDTO) {
        log.debug("Request to partially update RequestSystemFee : {}", requestSystemFeeDTO);

        return requestSystemFeeRepository
            .findById(requestSystemFeeDTO.getId())
            .map(existingRequestSystemFee -> {
                requestSystemFeeMapper.partialUpdate(existingRequestSystemFee, requestSystemFeeDTO);

                return existingRequestSystemFee;
            })
            .map(requestSystemFeeRepository::save)
            .map(requestSystemFeeMapper::toDto);
    }

    /**
     * Get all the requestSystemFees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestSystemFeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestSystemFees");
        return requestSystemFeeRepository.findAll(pageable).map(requestSystemFeeMapper::toDto);
    }

    /**
     * Get one requestSystemFee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestSystemFeeDTO> findOne(Long id) {
        log.debug("Request to get RequestSystemFee : {}", id);
        return requestSystemFeeRepository.findById(id).map(requestSystemFeeMapper::toDto);
    }

    /**
     * Delete the requestSystemFee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RequestSystemFee : {}", id);
        requestSystemFeeRepository.deleteById(id);
    }
}
