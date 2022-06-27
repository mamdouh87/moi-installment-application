package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.ContractSystemFeeDetails;
import ae.isoft.moi.installment.repository.ContractSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDetailsDTO;
import ae.isoft.moi.installment.service.mapper.ContractSystemFeeDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractSystemFeeDetails}.
 */
@Service
@Transactional
public class ContractSystemFeeDetailsService {

    private final Logger log = LoggerFactory.getLogger(ContractSystemFeeDetailsService.class);

    private final ContractSystemFeeDetailsRepository contractSystemFeeDetailsRepository;

    private final ContractSystemFeeDetailsMapper contractSystemFeeDetailsMapper;

    public ContractSystemFeeDetailsService(
        ContractSystemFeeDetailsRepository contractSystemFeeDetailsRepository,
        ContractSystemFeeDetailsMapper contractSystemFeeDetailsMapper
    ) {
        this.contractSystemFeeDetailsRepository = contractSystemFeeDetailsRepository;
        this.contractSystemFeeDetailsMapper = contractSystemFeeDetailsMapper;
    }

    /**
     * Save a contractSystemFeeDetails.
     *
     * @param contractSystemFeeDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractSystemFeeDetailsDTO save(ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO) {
        log.debug("Request to save ContractSystemFeeDetails : {}", contractSystemFeeDetailsDTO);
        ContractSystemFeeDetails contractSystemFeeDetails = contractSystemFeeDetailsMapper.toEntity(contractSystemFeeDetailsDTO);
        contractSystemFeeDetails = contractSystemFeeDetailsRepository.save(contractSystemFeeDetails);
        return contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);
    }

    /**
     * Update a contractSystemFeeDetails.
     *
     * @param contractSystemFeeDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractSystemFeeDetailsDTO update(ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO) {
        log.debug("Request to save ContractSystemFeeDetails : {}", contractSystemFeeDetailsDTO);
        ContractSystemFeeDetails contractSystemFeeDetails = contractSystemFeeDetailsMapper.toEntity(contractSystemFeeDetailsDTO);
        contractSystemFeeDetails = contractSystemFeeDetailsRepository.save(contractSystemFeeDetails);
        return contractSystemFeeDetailsMapper.toDto(contractSystemFeeDetails);
    }

    /**
     * Partially update a contractSystemFeeDetails.
     *
     * @param contractSystemFeeDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractSystemFeeDetailsDTO> partialUpdate(ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO) {
        log.debug("Request to partially update ContractSystemFeeDetails : {}", contractSystemFeeDetailsDTO);

        return contractSystemFeeDetailsRepository
            .findById(contractSystemFeeDetailsDTO.getId())
            .map(existingContractSystemFeeDetails -> {
                contractSystemFeeDetailsMapper.partialUpdate(existingContractSystemFeeDetails, contractSystemFeeDetailsDTO);

                return existingContractSystemFeeDetails;
            })
            .map(contractSystemFeeDetailsRepository::save)
            .map(contractSystemFeeDetailsMapper::toDto);
    }

    /**
     * Get all the contractSystemFeeDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractSystemFeeDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContractSystemFeeDetails");
        return contractSystemFeeDetailsRepository.findAll(pageable).map(contractSystemFeeDetailsMapper::toDto);
    }

    /**
     * Get one contractSystemFeeDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractSystemFeeDetailsDTO> findOne(Long id) {
        log.debug("Request to get ContractSystemFeeDetails : {}", id);
        return contractSystemFeeDetailsRepository.findById(id).map(contractSystemFeeDetailsMapper::toDto);
    }

    /**
     * Delete the contractSystemFeeDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractSystemFeeDetails : {}", id);
        contractSystemFeeDetailsRepository.deleteById(id);
    }
}
