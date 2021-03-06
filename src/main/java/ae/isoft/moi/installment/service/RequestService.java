package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.Request;
import ae.isoft.moi.installment.repository.RequestRepository;
import ae.isoft.moi.installment.service.dto.RequestDTO;
import ae.isoft.moi.installment.service.mapper.RequestMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Request}.
 */
@Service
@Transactional
public class RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestService.class);

    private final RequestRepository requestRepository;

    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    /**
     * Save a request.
     *
     * @param requestDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestDTO save(RequestDTO requestDTO) {
        log.debug("Request to save Request : {}", requestDTO);
        Request request = requestMapper.toEntity(requestDTO);
        request = requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    /**
     * Update a request.
     *
     * @param requestDTO the entity to save.
     * @return the persisted entity.
     */
    public RequestDTO update(RequestDTO requestDTO) {
        log.debug("Request to save Request : {}", requestDTO);
        Request request = requestMapper.toEntity(requestDTO);
        request = requestRepository.save(request);
        return requestMapper.toDto(request);
    }

    /**
     * Partially update a request.
     *
     * @param requestDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RequestDTO> partialUpdate(RequestDTO requestDTO) {
        log.debug("Request to partially update Request : {}", requestDTO);

        return requestRepository
            .findById(requestDTO.getId())
            .map(existingRequest -> {
                requestMapper.partialUpdate(existingRequest, requestDTO);

                return existingRequest;
            })
            .map(requestRepository::save)
            .map(requestMapper::toDto);
    }

    /**
     * Get all the requests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Requests");
        return requestRepository.findAll(pageable).map(requestMapper::toDto);
    }

    /**
     * Get one request by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestDTO> findOne(Long id) {
        log.debug("Request to get Request : {}", id);
        return requestRepository.findById(id).map(requestMapper::toDto);
    }

    /**
     * Delete the request by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Request : {}", id);
        requestRepository.deleteById(id);
    }
}
