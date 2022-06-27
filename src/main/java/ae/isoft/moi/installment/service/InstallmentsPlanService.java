package ae.isoft.moi.installment.service;

import ae.isoft.moi.installment.domain.InstallmentsPlan;
import ae.isoft.moi.installment.repository.InstallmentsPlanRepository;
import ae.isoft.moi.installment.service.dto.InstallmentsPlanDTO;
import ae.isoft.moi.installment.service.mapper.InstallmentsPlanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InstallmentsPlan}.
 */
@Service
@Transactional
public class InstallmentsPlanService {

    private final Logger log = LoggerFactory.getLogger(InstallmentsPlanService.class);

    private final InstallmentsPlanRepository installmentsPlanRepository;

    private final InstallmentsPlanMapper installmentsPlanMapper;

    public InstallmentsPlanService(InstallmentsPlanRepository installmentsPlanRepository, InstallmentsPlanMapper installmentsPlanMapper) {
        this.installmentsPlanRepository = installmentsPlanRepository;
        this.installmentsPlanMapper = installmentsPlanMapper;
    }

    /**
     * Save a installmentsPlan.
     *
     * @param installmentsPlanDTO the entity to save.
     * @return the persisted entity.
     */
    public InstallmentsPlanDTO save(InstallmentsPlanDTO installmentsPlanDTO) {
        log.debug("Request to save InstallmentsPlan : {}", installmentsPlanDTO);
        InstallmentsPlan installmentsPlan = installmentsPlanMapper.toEntity(installmentsPlanDTO);
        installmentsPlan = installmentsPlanRepository.save(installmentsPlan);
        return installmentsPlanMapper.toDto(installmentsPlan);
    }

    /**
     * Update a installmentsPlan.
     *
     * @param installmentsPlanDTO the entity to save.
     * @return the persisted entity.
     */
    public InstallmentsPlanDTO update(InstallmentsPlanDTO installmentsPlanDTO) {
        log.debug("Request to save InstallmentsPlan : {}", installmentsPlanDTO);
        InstallmentsPlan installmentsPlan = installmentsPlanMapper.toEntity(installmentsPlanDTO);
        installmentsPlan = installmentsPlanRepository.save(installmentsPlan);
        return installmentsPlanMapper.toDto(installmentsPlan);
    }

    /**
     * Partially update a installmentsPlan.
     *
     * @param installmentsPlanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InstallmentsPlanDTO> partialUpdate(InstallmentsPlanDTO installmentsPlanDTO) {
        log.debug("Request to partially update InstallmentsPlan : {}", installmentsPlanDTO);

        return installmentsPlanRepository
            .findById(installmentsPlanDTO.getId())
            .map(existingInstallmentsPlan -> {
                installmentsPlanMapper.partialUpdate(existingInstallmentsPlan, installmentsPlanDTO);

                return existingInstallmentsPlan;
            })
            .map(installmentsPlanRepository::save)
            .map(installmentsPlanMapper::toDto);
    }

    /**
     * Get all the installmentsPlans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InstallmentsPlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstallmentsPlans");
        return installmentsPlanRepository.findAll(pageable).map(installmentsPlanMapper::toDto);
    }

    /**
     * Get one installmentsPlan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstallmentsPlanDTO> findOne(Long id) {
        log.debug("Request to get InstallmentsPlan : {}", id);
        return installmentsPlanRepository.findById(id).map(installmentsPlanMapper::toDto);
    }

    /**
     * Delete the installmentsPlan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InstallmentsPlan : {}", id);
        installmentsPlanRepository.deleteById(id);
    }
}
