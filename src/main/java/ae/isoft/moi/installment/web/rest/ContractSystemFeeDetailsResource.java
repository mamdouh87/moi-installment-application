package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.ContractSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.ContractSystemFeeDetailsService;
import ae.isoft.moi.installment.service.dto.ContractSystemFeeDetailsDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.ContractSystemFeeDetails}.
 */
@RestController
@RequestMapping("/api")
public class ContractSystemFeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ContractSystemFeeDetailsResource.class);

    private static final String ENTITY_NAME = "contractSystemFeeDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractSystemFeeDetailsService contractSystemFeeDetailsService;

    private final ContractSystemFeeDetailsRepository contractSystemFeeDetailsRepository;

    public ContractSystemFeeDetailsResource(
        ContractSystemFeeDetailsService contractSystemFeeDetailsService,
        ContractSystemFeeDetailsRepository contractSystemFeeDetailsRepository
    ) {
        this.contractSystemFeeDetailsService = contractSystemFeeDetailsService;
        this.contractSystemFeeDetailsRepository = contractSystemFeeDetailsRepository;
    }

    /**
     * {@code POST  /contract-system-fee-details} : Create a new contractSystemFeeDetails.
     *
     * @param contractSystemFeeDetailsDTO the contractSystemFeeDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractSystemFeeDetailsDTO, or with status {@code 400 (Bad Request)} if the contractSystemFeeDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-system-fee-details")
    public ResponseEntity<ContractSystemFeeDetailsDTO> createContractSystemFeeDetails(
        @RequestBody ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ContractSystemFeeDetails : {}", contractSystemFeeDetailsDTO);
        if (contractSystemFeeDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new contractSystemFeeDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractSystemFeeDetailsDTO result = contractSystemFeeDetailsService.save(contractSystemFeeDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/contract-system-fee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-system-fee-details/:id} : Updates an existing contractSystemFeeDetails.
     *
     * @param id the id of the contractSystemFeeDetailsDTO to save.
     * @param contractSystemFeeDetailsDTO the contractSystemFeeDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSystemFeeDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the contractSystemFeeDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractSystemFeeDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-system-fee-details/{id}")
    public ResponseEntity<ContractSystemFeeDetailsDTO> updateContractSystemFeeDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContractSystemFeeDetails : {}, {}", id, contractSystemFeeDetailsDTO);
        if (contractSystemFeeDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSystemFeeDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSystemFeeDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractSystemFeeDetailsDTO result = contractSystemFeeDetailsService.update(contractSystemFeeDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractSystemFeeDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-system-fee-details/:id} : Partial updates given fields of an existing contractSystemFeeDetails, field will ignore if it is null
     *
     * @param id the id of the contractSystemFeeDetailsDTO to save.
     * @param contractSystemFeeDetailsDTO the contractSystemFeeDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractSystemFeeDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the contractSystemFeeDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractSystemFeeDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractSystemFeeDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-system-fee-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractSystemFeeDetailsDTO> partialUpdateContractSystemFeeDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractSystemFeeDetailsDTO contractSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractSystemFeeDetails partially : {}, {}", id, contractSystemFeeDetailsDTO);
        if (contractSystemFeeDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractSystemFeeDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractSystemFeeDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractSystemFeeDetailsDTO> result = contractSystemFeeDetailsService.partialUpdate(contractSystemFeeDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractSystemFeeDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-system-fee-details} : get all the contractSystemFeeDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractSystemFeeDetails in body.
     */
    @GetMapping("/contract-system-fee-details")
    public ResponseEntity<List<ContractSystemFeeDetailsDTO>> getAllContractSystemFeeDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ContractSystemFeeDetails");
        Page<ContractSystemFeeDetailsDTO> page = contractSystemFeeDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contract-system-fee-details/:id} : get the "id" contractSystemFeeDetails.
     *
     * @param id the id of the contractSystemFeeDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractSystemFeeDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-system-fee-details/{id}")
    public ResponseEntity<ContractSystemFeeDetailsDTO> getContractSystemFeeDetails(@PathVariable Long id) {
        log.debug("REST request to get ContractSystemFeeDetails : {}", id);
        Optional<ContractSystemFeeDetailsDTO> contractSystemFeeDetailsDTO = contractSystemFeeDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractSystemFeeDetailsDTO);
    }

    /**
     * {@code DELETE  /contract-system-fee-details/:id} : delete the "id" contractSystemFeeDetails.
     *
     * @param id the id of the contractSystemFeeDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-system-fee-details/{id}")
    public ResponseEntity<Void> deleteContractSystemFeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete ContractSystemFeeDetails : {}", id);
        contractSystemFeeDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
