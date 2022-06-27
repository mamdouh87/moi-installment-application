package ae.isoft.moi.installment.web.rest;

import ae.isoft.moi.installment.repository.TrafficContractRepository;
import ae.isoft.moi.installment.service.TrafficContractService;
import ae.isoft.moi.installment.service.dto.TrafficContractDTO;
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
 * REST controller for managing {@link ae.isoft.moi.installment.domain.TrafficContract}.
 */
@RestController
@RequestMapping("/api")
public class TrafficContractResource {

    private final Logger log = LoggerFactory.getLogger(TrafficContractResource.class);

    private static final String ENTITY_NAME = "trafficContract";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrafficContractService trafficContractService;

    private final TrafficContractRepository trafficContractRepository;

    public TrafficContractResource(TrafficContractService trafficContractService, TrafficContractRepository trafficContractRepository) {
        this.trafficContractService = trafficContractService;
        this.trafficContractRepository = trafficContractRepository;
    }

    /**
     * {@code POST  /traffic-contracts} : Create a new trafficContract.
     *
     * @param trafficContractDTO the trafficContractDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trafficContractDTO, or with status {@code 400 (Bad Request)} if the trafficContract has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/traffic-contracts")
    public ResponseEntity<TrafficContractDTO> createTrafficContract(@RequestBody TrafficContractDTO trafficContractDTO)
        throws URISyntaxException {
        log.debug("REST request to save TrafficContract : {}", trafficContractDTO);
        if (trafficContractDTO.getId() != null) {
            throw new BadRequestAlertException("A new trafficContract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrafficContractDTO result = trafficContractService.save(trafficContractDTO);
        return ResponseEntity
            .created(new URI("/api/traffic-contracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /traffic-contracts/:id} : Updates an existing trafficContract.
     *
     * @param id the id of the trafficContractDTO to save.
     * @param trafficContractDTO the trafficContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trafficContractDTO,
     * or with status {@code 400 (Bad Request)} if the trafficContractDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trafficContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/traffic-contracts/{id}")
    public ResponseEntity<TrafficContractDTO> updateTrafficContract(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrafficContractDTO trafficContractDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TrafficContract : {}, {}", id, trafficContractDTO);
        if (trafficContractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trafficContractDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trafficContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TrafficContractDTO result = trafficContractService.update(trafficContractDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trafficContractDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /traffic-contracts/:id} : Partial updates given fields of an existing trafficContract, field will ignore if it is null
     *
     * @param id the id of the trafficContractDTO to save.
     * @param trafficContractDTO the trafficContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trafficContractDTO,
     * or with status {@code 400 (Bad Request)} if the trafficContractDTO is not valid,
     * or with status {@code 404 (Not Found)} if the trafficContractDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the trafficContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/traffic-contracts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrafficContractDTO> partialUpdateTrafficContract(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrafficContractDTO trafficContractDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TrafficContract partially : {}, {}", id, trafficContractDTO);
        if (trafficContractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trafficContractDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trafficContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TrafficContractDTO> result = trafficContractService.partialUpdate(trafficContractDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, trafficContractDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /traffic-contracts} : get all the trafficContracts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trafficContracts in body.
     */
    @GetMapping("/traffic-contracts")
    public ResponseEntity<List<TrafficContractDTO>> getAllTrafficContracts(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of TrafficContracts");
        Page<TrafficContractDTO> page = trafficContractService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /traffic-contracts/:id} : get the "id" trafficContract.
     *
     * @param id the id of the trafficContractDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trafficContractDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/traffic-contracts/{id}")
    public ResponseEntity<TrafficContractDTO> getTrafficContract(@PathVariable Long id) {
        log.debug("REST request to get TrafficContract : {}", id);
        Optional<TrafficContractDTO> trafficContractDTO = trafficContractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trafficContractDTO);
    }

    /**
     * {@code DELETE  /traffic-contracts/:id} : delete the "id" trafficContract.
     *
     * @param id the id of the trafficContractDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/traffic-contracts/{id}")
    public ResponseEntity<Void> deleteTrafficContract(@PathVariable Long id) {
        log.debug("REST request to delete TrafficContract : {}", id);
        trafficContractService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
