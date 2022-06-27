package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.ContractSystemFee;
import ae.isoft.moi.installment.repository.ContractSystemFeeRepository;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDTO;
import ae.isoft.moi.installment.service.mapper.ContractSystemFeeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractSystemFee}.
 */
@Service
@Transactional
public class ContractSystemFeeService {

    private final Logger log = LoggerFactory.getLogger(ContractSystemFeeService.class);

    private final ContractSystemFeeRepository contractSystemFeeRepository;

    private final ContractSystemFeeMapper contractSystemFeeMapper;

    public ContractSystemFeeService(
        ContractSystemFeeRepository contractSystemFeeRepository,
        ContractSystemFeeMapper contractSystemFeeMapper
    ) {
        this.contractSystemFeeRepository = contractSystemFeeRepository;
        this.contractSystemFeeMapper = contractSystemFeeMapper;
    }

    /**
     * Save a contractSystemFee.
     *
     * @param contractSystemFeeDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractSystemFeeDTO save(ContractSystemFeeDTO contractSystemFeeDTO) {
        log.debug("Request to save ContractSystemFee : {}", contractSystemFeeDTO);
        ContractSystemFee contractSystemFee = contractSystemFeeMapper.toEntity(contractSystemFeeDTO);
        contractSystemFee = contractSystemFeeRepository.save(contractSystemFee);
        return contractSystemFeeMapper.toDto(contractSystemFee);
    }

    /**
     * Update a contractSystemFee.
     *
     * @param contractSystemFeeDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractSystemFeeDTO update(ContractSystemFeeDTO contractSystemFeeDTO) {
        log.debug("Request to save ContractSystemFee : {}", contractSystemFeeDTO);
        ContractSystemFee contractSystemFee = contractSystemFeeMapper.toEntity(contractSystemFeeDTO);
        contractSystemFee = contractSystemFeeRepository.save(contractSystemFee);
        return contractSystemFeeMapper.toDto(contractSystemFee);
    }

    /**
     * Partially update a contractSystemFee.
     *
     * @param contractSystemFeeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractSystemFeeDTO> partialUpdate(ContractSystemFeeDTO contractSystemFeeDTO) {
        log.debug("Request to partially update ContractSystemFee : {}", contractSystemFeeDTO);

        return contractSystemFeeRepository
            .findById(contractSystemFeeDTO.getId())
            .map(existingContractSystemFee -> {
                contractSystemFeeMapper.partialUpdate(existingContractSystemFee, contractSystemFeeDTO);

                return existingContractSystemFee;
            })
            .map(contractSystemFeeRepository::save)
            .map(contractSystemFeeMapper::toDto);
    }

    /**
     * Get all the contractSystemFees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractSystemFeeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContractSystemFees");
        return contractSystemFeeRepository.findAll(pageable).map(contractSystemFeeMapper::toDto);
    }

    /**
     * Get one contractSystemFee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractSystemFeeDTO> findOne(Long id) {
        log.debug("Request to get ContractSystemFee : {}", id);
        return contractSystemFeeRepository.findById(id).map(contractSystemFeeMapper::toDto);
    }

    /**
     * Delete the contractSystemFee by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractSystemFee : {}", id);
        contractSystemFeeRepository.deleteById(id);
    }
}
