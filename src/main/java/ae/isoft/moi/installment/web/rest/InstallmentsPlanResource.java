package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.InstallmentsPlanRepository;
import ae.isoft.moi.installment.service.InstallmentsPlanService;
import ae.isoft.moi.installment.service.dto.InstallmentsPlanDTO;
import ae.isoft.moi.installment.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ae.isoft.moi.installment.domain.InstallmentsPlan}.
 */
@RestController
@RequestMapping("/api")
public class InstallmentsPlanResource {

    private final Logger log = LoggerFactory.getLogger(InstallmentsPlanResource.class);

    private static final String ENTITY_NAME = "installmentsPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstallmentsPlanService installmentsPlanService;

    private final InstallmentsPlanRepository installmentsPlanRepository;

    public InstallmentsPlanResource(
        InstallmentsPlanService installmentsPlanService,
        InstallmentsPlanRepository installmentsPlanRepository
    ) {
        this.installmentsPlanService = installmentsPlanService;
        this.installmentsPlanRepository = installmentsPlanRepository;
    }

    /**
     * {@code POST  /installments-plans} : Create a new installmentsPlan.
     *
     * @param installmentsPlanDTO the installmentsPlanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new installmentsPlanDTO, or with status {@code 400 (Bad Request)} if the installmentsPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/installments-plans")
    public ResponseEntity<InstallmentsPlanDTO> createInstallmentsPlan(@RequestBody InstallmentsPlanDTO installmentsPlanDTO)
        throws URISyntaxException {
        log.debug("REST request to save InstallmentsPlan : {}", installmentsPlanDTO);
        if (installmentsPlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new installmentsPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstallmentsPlanDTO result = installmentsPlanService.save(installmentsPlanDTO);
        return ResponseEntity
            .created(new URI("/api/installments-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /installments-plans/:id} : Updates an existing installmentsPlan.
     *
     * @param id the id of the installmentsPlanDTO to save.
     * @param installmentsPlanDTO the installmentsPlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated installmentsPlanDTO,
     * or with status {@code 400 (Bad Request)} if the installmentsPlanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the installmentsPlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/installments-plans/{id}")
    public ResponseEntity<InstallmentsPlanDTO> updateInstallmentsPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InstallmentsPlanDTO installmentsPlanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InstallmentsPlan : {}, {}", id, installmentsPlanDTO);
        if (installmentsPlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, installmentsPlanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!installmentsPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InstallmentsPlanDTO result = installmentsPlanService.update(installmentsPlanDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, installmentsPlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /installments-plans/:id} : Partial updates given fields of an existing installmentsPlan, field will ignore if it is null
     *
     * @param id the id of the installmentsPlanDTO to save.
     * @param installmentsPlanDTO the installmentsPlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated installmentsPlanDTO,
     * or with status {@code 400 (Bad Request)} if the installmentsPlanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the installmentsPlanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the installmentsPlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/installments-plans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InstallmentsPlanDTO> partialUpdateInstallmentsPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InstallmentsPlanDTO installmentsPlanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InstallmentsPlan partially : {}, {}", id, installmentsPlanDTO);
        if (installmentsPlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, installmentsPlanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!installmentsPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InstallmentsPlanDTO> result = installmentsPlanService.partialUpdate(installmentsPlanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, installmentsPlanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /installments-plans} : get all the installmentsPlans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of installmentsPlans in body.
     */
    @GetMapping("/installments-plans")
    public ResponseEntity<List<InstallmentsPlanDTO>> getAllInstallmentsPlans(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of InstallmentsPlans");
        Page<InstallmentsPlanDTO> page = installmentsPlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /installments-plans/:id} : get the "id" installmentsPlan.
     *
     * @param id the id of the installmentsPlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the installmentsPlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/installments-plans/{id}")
    public ResponseEntity<InstallmentsPlanDTO> getInstallmentsPlan(@PathVariable Long id) {
        log.debug("REST request to get InstallmentsPlan : {}", id);
        Optional<InstallmentsPlanDTO> installmentsPlanDTO = installmentsPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(installmentsPlanDTO);
    }

    /**
     * {@code DELETE  /installments-plans/:id} : delete the "id" installmentsPlan.
     *
     * @param id the id of the installmentsPlanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/installments-plans/{id}")
    public ResponseEntity<Void> deleteInstallmentsPlan(@PathVariable Long id) {
        log.debug("REST request to delete InstallmentsPlan : {}", id);
        installmentsPlanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
