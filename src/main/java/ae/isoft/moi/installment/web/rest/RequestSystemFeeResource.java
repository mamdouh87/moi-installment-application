package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.RequestSystemFeeRepository;
import ae.isoft.moi.installment.service.RequestSystemFeeService;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.RequestSystemFee}.
 */
@RestController
@RequestMapping("/api")
public class RequestSystemFeeResource {

    private final Logger log = LoggerFactory.getLogger(RequestSystemFeeResource.class);

    private static final String ENTITY_NAME = "requestSystemFee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestSystemFeeService requestSystemFeeService;

    private final RequestSystemFeeRepository requestSystemFeeRepository;

    public RequestSystemFeeResource(
        RequestSystemFeeService requestSystemFeeService,
        RequestSystemFeeRepository requestSystemFeeRepository
    ) {
        this.requestSystemFeeService = requestSystemFeeService;
        this.requestSystemFeeRepository = requestSystemFeeRepository;
    }

    /**
     * {@code POST  /request-system-fees} : Create a new requestSystemFee.
     *
     * @param requestSystemFeeDTO the requestSystemFeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestSystemFeeDTO, or with status {@code 400 (Bad Request)} if the requestSystemFee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-system-fees")
    public ResponseEntity<RequestSystemFeeDTO> createRequestSystemFee(@RequestBody RequestSystemFeeDTO requestSystemFeeDTO)
        throws URISyntaxException {
        log.debug("REST request to save RequestSystemFee : {}", requestSystemFeeDTO);
        if (requestSystemFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestSystemFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestSystemFeeDTO result = requestSystemFeeService.save(requestSystemFeeDTO);
        return ResponseEntity
            .created(new URI("/api/request-system-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-system-fees/:id} : Updates an existing requestSystemFee.
     *
     * @param id the id of the requestSystemFeeDTO to save.
     * @param requestSystemFeeDTO the requestSystemFeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestSystemFeeDTO,
     * or with status {@code 400 (Bad Request)} if the requestSystemFeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestSystemFeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-system-fees/{id}")
    public ResponseEntity<RequestSystemFeeDTO> updateRequestSystemFee(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestSystemFeeDTO requestSystemFeeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RequestSystemFee : {}, {}", id, requestSystemFeeDTO);
        if (requestSystemFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestSystemFeeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestSystemFeeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RequestSystemFeeDTO result = requestSystemFeeService.update(requestSystemFeeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestSystemFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /request-system-fees/:id} : Partial updates given fields of an existing requestSystemFee, field will ignore if it is null
     *
     * @param id the id of the requestSystemFeeDTO to save.
     * @param requestSystemFeeDTO the requestSystemFeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestSystemFeeDTO,
     * or with status {@code 400 (Bad Request)} if the requestSystemFeeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the requestSystemFeeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestSystemFeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/request-system-fees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RequestSystemFeeDTO> partialUpdateRequestSystemFee(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestSystemFeeDTO requestSystemFeeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RequestSystemFee partially : {}, {}", id, requestSystemFeeDTO);
        if (requestSystemFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestSystemFeeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestSystemFeeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RequestSystemFeeDTO> result = requestSystemFeeService.partialUpdate(requestSystemFeeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestSystemFeeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /request-system-fees} : get all the requestSystemFees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestSystemFees in body.
     */
    @GetMapping("/request-system-fees")
    public ResponseEntity<List<RequestSystemFeeDTO>> getAllRequestSystemFees(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RequestSystemFees");
        Page<RequestSystemFeeDTO> page = requestSystemFeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-system-fees/:id} : get the "id" requestSystemFee.
     *
     * @param id the id of the requestSystemFeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestSystemFeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-system-fees/{id}")
    public ResponseEntity<RequestSystemFeeDTO> getRequestSystemFee(@PathVariable Long id) {
        log.debug("REST request to get RequestSystemFee : {}", id);
        Optional<RequestSystemFeeDTO> requestSystemFeeDTO = requestSystemFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestSystemFeeDTO);
    }

    /**
     * {@code DELETE  /request-system-fees/:id} : delete the "id" requestSystemFee.
     *
     * @param id the id of the requestSystemFeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-system-fees/{id}")
    public ResponseEntity<Void> deleteRequestSystemFee(@PathVariable Long id) {
        log.debug("REST request to delete RequestSystemFee : {}", id);
        requestSystemFeeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
