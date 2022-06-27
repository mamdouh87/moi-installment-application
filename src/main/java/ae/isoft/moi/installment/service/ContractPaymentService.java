package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.ContractPayment;
import ae.isoft.moi.installment.repository.ContractPaymentRepository;
import ae.isoft.moi.installment.service.dto.ContractPaymentDTO;
import ae.isoft.moi.installment.service.mapper.ContractPaymentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContractPayment}.
 */
@Service
@Transactional
public class ContractPaymentService {

    private final Logger log = LoggerFactory.getLogger(ContractPaymentService.class);

    private final ContractPaymentRepository contractPaymentRepository;

    private final ContractPaymentMapper contractPaymentMapper;

    public ContractPaymentService(ContractPaymentRepository contractPaymentRepository, ContractPaymentMapper contractPaymentMapper) {
        this.contractPaymentRepository = contractPaymentRepository;
        this.contractPaymentMapper = contractPaymentMapper;
    }

    /**
     * Save a contractPayment.
     *
     * @param contractPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPaymentDTO save(ContractPaymentDTO contractPaymentDTO) {
        log.debug("Request to save ContractPayment : {}", contractPaymentDTO);
        ContractPayment contractPayment = contractPaymentMapper.toEntity(contractPaymentDTO);
        contractPayment = contractPaymentRepository.save(contractPayment);
        return contractPaymentMapper.toDto(contractPayment);
    }

    /**
     * Update a contractPayment.
     *
     * @param contractPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPaymentDTO update(ContractPaymentDTO contractPaymentDTO) {
        log.debug("Request to save ContractPayment : {}", contractPaymentDTO);
        ContractPayment contractPayment = contractPaymentMapper.toEntity(contractPaymentDTO);
        contractPayment = contractPaymentRepository.save(contractPayment);
        return contractPaymentMapper.toDto(contractPayment);
    }

    /**
     * Partially update a contractPayment.
     *
     * @param contractPaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractPaymentDTO> partialUpdate(ContractPaymentDTO contractPaymentDTO) {
        log.debug("Request to partially update ContractPayment : {}", contractPaymentDTO);

        return contractPaymentRepository
            .findById(contractPaymentDTO.getId())
            .map(existingContractPayment -> {
                contractPaymentMapper.partialUpdate(existingContractPayment, contractPaymentDTO);

                return existingContractPayment;
            })
            .map(contractPaymentRepository::save)
            .map(contractPaymentMapper::toDto);
    }

    /**
     * Get all the contractPayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContractPayments");
        return contractPaymentRepository.findAll(pageable).map(contractPaymentMapper::toDto);
    }

    /**
     * Get one contractPayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractPaymentDTO> findOne(Long id) {
        log.debug("Request to get ContractPayment : {}", id);
        return contractPaymentRepository.findById(id).map(contractPaymentMapper::toDto);
    }

    /**
     * Delete the contractPayment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractPayment : {}", id);
        contractPaymentRepository.deleteById(id);
    }
}
