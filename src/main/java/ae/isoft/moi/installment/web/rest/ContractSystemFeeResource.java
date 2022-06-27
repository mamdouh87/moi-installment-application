package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.ContractSystemFeeRepository;
import ae.isoft.moi.installment.service.ContractSystemFeeService;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.ContractSystemFee}.
 */
@RestController
@RequestMapping("/api")
public class ContractSystemFeeResource {

    private final Logger log = LoggerFactory.getLogger(ContractSystemFeeResource.class);

    private static final String ENTITY_NAME = "contractSystemFee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractSystemFeeService contractSystemFeeService;

    private final ContractSystemFeeRepository contractSystemFeeRepository;

    public ContractSystemFeeResource(
        ContractSystemFeeService contractSystemFeeService,
        ContractSystemFeeRepository contractSystemFeeRepository
    ) {
        this.contractSystemFeeService = contractSystemFeeService;
        this.contractSystemFeeRepository = contractSystemFeeRepository;
    }

    /**
     * {@code POST  /contract-system-fees} : Create a new contractSystemFee.
     *
     * @param contractSystemFeeDTO the contractSystemFeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractSystemFeeDTO, or with status {@code 400 (Bad Request)} if the contractSystemFee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-system-fees")
    public ResponseEntity<ContractSystemFeeDTO> createContractSystemFee(@RequestBody ContractSystemFeeDTO contractSystemFeeDTO)
        throws URISyntaxException {
        log.debug("REST request to save ContractSystemFee : {}", contractSystemFeeDTO);
        if (contractSystemFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new contractSystemFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractSystemFeeDTO result = contractSystemFeeService.save(contractSystemFeeDTO);
        return ResponseEntity
            .created(new URI("/api/contract-system-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-system-fees/:id} : Updates an existing contractSystemFee.
     *
     * @param id the id of the contractSystemFeeDTO to save.
     * @param contractSystemFeeDTO the contractSystemFeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSystemFeeDTO,
     * or with status {@code 400 (Bad Request)} if the contractSystemFeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractSystemFeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-system-fees/{id}")
    public ResponseEntity<ContractSystemFeeDTO> updateContractSystemFee(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractSystemFeeDTO contractSystemFeeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContractSystemFee : {}, {}", id, contractSystemFeeDTO);
        if (contractSystemFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSystemFeeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSystemFeeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractSystemFeeDTO result = contractSystemFeeService.update(contractSystemFeeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractSystemFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-system-fees/:id} : Partial updates given fields of an existing contractSystemFee, field will ignore if it is null
     *
     * @param id the id of the contractSystemFeeDTO to save.
     * @param contractSystemFeeDTO the contractSystemFeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSystemFeeDTO,
     * or with status {@code 400 (Bad Request)} if the contractSystemFeeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractSystemFeeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractSystemFeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-system-fees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractSystemFeeDTO> partialUpdateContractSystemFee(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractSystemFeeDTO contractSystemFeeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractSystemFee partially : {}, {}", id, contractSystemFeeDTO);
        if (contractSystemFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSystemFeeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSystemFeeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractSystemFeeDTO> result = contractSystemFeeService.partialUpdate(contractSystemFeeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractSystemFeeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-system-fees} : get all the contractSystemFees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractSystemFees in body.
     */
    @GetMapping("/contract-system-fees")
    public ResponseEntity<List<ContractSystemFeeDTO>> getAllContractSystemFees(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ContractSystemFees");
        Page<ContractSystemFeeDTO> page = contractSystemFeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contract-system-fees/:id} : get the "id" contractSystemFee.
     *
     * @param id the id of the contractSystemFeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractSystemFeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-system-fees/{id}")
    public ResponseEntity<ContractSystemFeeDTO> getContractSystemFee(@PathVariable Long id) {
        log.debug("REST request to get ContractSystemFee : {}", id);
        Optional<ContractSystemFeeDTO> contractSystemFeeDTO = contractSystemFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractSystemFeeDTO);
    }

    /**
     * {@code DELETE  /contract-system-fees/:id} : delete the "id" contractSystemFee.
     *
     * @param id the id of the contractSystemFeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-system-fees/{id}")
    public ResponseEntity<Void> deleteContractSystemFee(@PathVariable Long id) {
        log.debug("REST request to delete ContractSystemFee : {}", id);
        contractSystemFeeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
