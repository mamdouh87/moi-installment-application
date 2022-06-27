package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.ContractPaymentRepository;
import ae.isoft.moi.installment.service.ContractPaymentService;
import ae.isoft.moi.installment.service.dto.ContractPaymentDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.ContractPayment}.
 */
@RestController
@RequestMapping("/api")
public class ContractPaymentResource {

    private final Logger log = LoggerFactory.getLogger(ContractPaymentResource.class);

    private static final String ENTITY_NAME = "contractPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractPaymentService contractPaymentService;

    private final ContractPaymentRepository contractPaymentRepository;

    public ContractPaymentResource(ContractPaymentService contractPaymentService, ContractPaymentRepository contractPaymentRepository) {
        this.contractPaymentService = contractPaymentService;
        this.contractPaymentRepository = contractPaymentRepository;
    }

    /**
     * {@code POST  /contract-payments} : Create a new contractPayment.
     *
     * @param contractPaymentDTO the contractPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractPaymentDTO, or with status {@code 400 (Bad Request)} if the contractPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-payments")
    public ResponseEntity<ContractPaymentDTO> createContractPayment(@RequestBody ContractPaymentDTO contractPaymentDTO)
        throws URISyntaxException {
        log.debug("REST request to save ContractPayment : {}", contractPaymentDTO);
        if (contractPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new contractPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractPaymentDTO result = contractPaymentService.save(contractPaymentDTO);
        return ResponseEntity
            .created(new URI("/api/contract-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-payments/:id} : Updates an existing contractPayment.
     *
     * @param id the id of the contractPaymentDTO to save.
     * @param contractPaymentDTO the contractPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the contractPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-payments/{id}")
    public ResponseEntity<ContractPaymentDTO> updateContractPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractPaymentDTO contractPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContractPayment : {}, {}", id, contractPaymentDTO);
        if (contractPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractPaymentDTO result = contractPaymentService.update(contractPaymentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractPaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-payments/:id} : Partial updates given fields of an existing contractPayment, field will ignore if it is null
     *
     * @param id the id of the contractPaymentDTO to save.
     * @param contractPaymentDTO the contractPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the contractPaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractPaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractPaymentDTO> partialUpdateContractPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ContractPaymentDTO contractPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractPayment partially : {}, {}", id, contractPaymentDTO);
        if (contractPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractPaymentDTO> result = contractPaymentService.partialUpdate(contractPaymentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractPaymentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-payments} : get all the contractPayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractPayments in body.
     */
    @GetMapping("/contract-payments")
    public ResponseEntity<List<ContractPaymentDTO>> getAllContractPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ContractPayments");
        Page<ContractPaymentDTO> page = contractPaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contract-payments/:id} : get the "id" contractPayment.
     *
     * @param id the id of the contractPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-payments/{id}")
    public ResponseEntity<ContractPaymentDTO> getContractPayment(@PathVariable Long id) {
        log.debug("REST request to get ContractPayment : {}", id);
        Optional<ContractPaymentDTO> contractPaymentDTO = contractPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractPaymentDTO);
    }

    /**
     * {@code DELETE  /contract-payments/:id} : delete the "id" contractPayment.
     *
     * @param id the id of the contractPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-payments/{id}")
    public ResponseEntity<Void> deleteContractPayment(@PathVariable Long id) {
        log.debug("REST request to delete ContractPayment : {}", id);
        contractPaymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
