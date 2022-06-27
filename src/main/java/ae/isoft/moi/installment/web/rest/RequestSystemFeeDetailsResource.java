package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.RequestSystemFeeDetailsRepository;
import ae.isoft.moi.installment.service.RequestSystemFeeDetailsService;
import ae.isoft.moi.installment.service.dto.RequestSystemFeeDetailsDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.RequestSystemFeeDetails}.
 */
@RestController
@RequestMapping("/api")
public class RequestSystemFeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(RequestSystemFeeDetailsResource.class);

    private static final String ENTITY_NAME = "requestSystemFeeDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestSystemFeeDetailsService requestSystemFeeDetailsService;

    private final RequestSystemFeeDetailsRepository requestSystemFeeDetailsRepository;

    public RequestSystemFeeDetailsResource(
        RequestSystemFeeDetailsService requestSystemFeeDetailsService,
        RequestSystemFeeDetailsRepository requestSystemFeeDetailsRepository
    ) {
        this.requestSystemFeeDetailsService = requestSystemFeeDetailsService;
        this.requestSystemFeeDetailsRepository = requestSystemFeeDetailsRepository;
    }

    /**
     * {@code POST  /request-system-fee-details} : Create a new requestSystemFeeDetails.
     *
     * @param requestSystemFeeDetailsDTO the requestSystemFeeDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestSystemFeeDetailsDTO, or with status {@code 400 (Bad Request)} if the requestSystemFeeDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-system-fee-details")
    public ResponseEntity<RequestSystemFeeDetailsDTO> createRequestSystemFeeDetails(
        @RequestBody RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save RequestSystemFeeDetails : {}", requestSystemFeeDetailsDTO);
        if (requestSystemFeeDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestSystemFeeDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestSystemFeeDetailsDTO result = requestSystemFeeDetailsService.save(requestSystemFeeDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/request-system-fee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-system-fee-details/:id} : Updates an existing requestSystemFeeDetails.
     *
     * @param id the id of the requestSystemFeeDetailsDTO to save.
     * @param requestSystemFeeDetailsDTO the requestSystemFeeDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestSystemFeeDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the requestSystemFeeDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestSystemFeeDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-system-fee-details/{id}")
    public ResponseEntity<RequestSystemFeeDetailsDTO> updateRequestSystemFeeDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RequestSystemFeeDetails : {}, {}", id, requestSystemFeeDetailsDTO);
        if (requestSystemFeeDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestSystemFeeDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestSystemFeeDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RequestSystemFeeDetailsDTO result = requestSystemFeeDetailsService.update(requestSystemFeeDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestSystemFeeDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /request-system-fee-details/:id} : Partial updates given fields of an existing requestSystemFeeDetails, field will ignore if it is null
     *
     * @param id the id of the requestSystemFeeDetailsDTO to save.
     * @param requestSystemFeeDetailsDTO the requestSystemFeeDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestSystemFeeDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the requestSystemFeeDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the requestSystemFeeDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestSystemFeeDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/request-system-fee-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RequestSystemFeeDetailsDTO> partialUpdateRequestSystemFeeDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RequestSystemFeeDetailsDTO requestSystemFeeDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RequestSystemFeeDetails partially : {}, {}", id, requestSystemFeeDetailsDTO);
        if (requestSystemFeeDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestSystemFeeDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!requestSystemFeeDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RequestSystemFeeDetailsDTO> result = requestSystemFeeDetailsService.partialUpdate(requestSystemFeeDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestSystemFeeDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /request-system-fee-details} : get all the requestSystemFeeDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestSystemFeeDetails in body.
     */
    @GetMapping("/request-system-fee-details")
    public ResponseEntity<List<RequestSystemFeeDetailsDTO>> getAllRequestSystemFeeDetails(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RequestSystemFeeDetails");
        Page<RequestSystemFeeDetailsDTO> page = requestSystemFeeDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-system-fee-details/:id} : get the "id" requestSystemFeeDetails.
     *
     * @param id the id of the requestSystemFeeDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestSystemFeeDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-system-fee-details/{id}")
    public ResponseEntity<RequestSystemFeeDetailsDTO> getRequestSystemFeeDetails(@PathVariable Long id) {
        log.debug("REST request to get RequestSystemFeeDetails : {}", id);
        Optional<RequestSystemFeeDetailsDTO> requestSystemFeeDetailsDTO = requestSystemFeeDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestSystemFeeDetailsDTO);
    }

    /**
     * {@code DELETE  /request-system-fee-details/:id} : delete the "id" requestSystemFeeDetails.
     *
     * @param id the id of the requestSystemFeeDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-system-fee-details/{id}")
    public ResponseEntity<Void> deleteRequestSystemFeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete RequestSystemFeeDetails : {}", id);
        requestSystemFeeDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
