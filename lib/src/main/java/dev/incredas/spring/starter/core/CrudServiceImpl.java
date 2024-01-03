package dev.incredas.spring.starter.core;

import dev.incredas.spring.starter.exception.EntityNotFoundException;
import dev.incredas.spring.starter.persistence.AuditableEntity;
import dev.incredas.spring.starter.persistence.EntitySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The CrudServiceImpl class is an abstract class that implements the CrudService interface.
 * It provides common implementation for CRUD operations on a resource.
 *
 * @param <ID>         the type of the resource identifier
 * @param <REQUEST>    the type of the request object for create and update operations
 * @param <QUERY>      the type of the query object for retrieving multiple resources
 * @param <RESPONSE>   the type of the response object (preferable DTO)
 * @param <ENTITY>     the type of the entity class that extends AuditableEntity
 * @param <REPOSITORY> the type of the repository class that extends CrudRepository and JpaSpecificationExecutor
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("squid:S119")
public abstract class CrudServiceImpl<ID, REQUEST, QUERY, RESPONSE, ENTITY extends AuditableEntity,
        REPOSITORY extends CrudRepository<ENTITY, ID> & JpaSpecificationExecutor<ENTITY>>
        implements CrudService<ID, REQUEST, QUERY, RESPONSE> {

    private final REPOSITORY repository;

    private final Mapper<REQUEST, ENTITY, RESPONSE> mapper;

    private final EntitySpecification<ENTITY, QUERY> specification;

    /**
     * Creates a new resource.
     *
     * @param request the request object for creating the resource
     * @return the created resource response model
     */
    @Override
    @Transactional
    public RESPONSE createOne(REQUEST request) {
        ENTITY entity = mapper.toEntity(request);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    /**
     * Updates an existing resource identified by the given ID with the provided request data.
     *
     * @param id      the identifier of the resource
     * @param request the request object containing the updated data
     * @return the updated resource response model
     */
    @Override
    @Transactional
    public RESPONSE updateOne(ID id, REQUEST request) {
        ENTITY entity = this.getOrThrow(id);
        mapper.toEntity(request, entity);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    /**
     * Retrieves a single resource with the given ID.
     *
     * @param id the identifier of the resource
     * @return the retrieved resource model
     */
    @Override
    @Transactional(readOnly = true)
    public RESPONSE getOne(ID id) {
        ENTITY entity = this.getOrThrow(id);
        return mapper.toResponse(entity);
    }

    /**
     * Retrieves a page of resources based on the given query and pagination parameters.
     *
     * @param query    the query object used for filtering and sorting
     * @param pageable the pagination parameters (page number, page size, sort order)
     * @return the page of retrieved resources response models
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> getAll(QUERY query, Pageable pageable) {
        Specification<ENTITY> querySpecification = specification.getSpecification(query);
        return repository.findAll(querySpecification, pageable)
                .map(mapper::toResponse);
    }

    /**
     * Deletes a single resource with the given ID.
     *
     * @param id the identifier of the resource to be deleted
     */
    @Override
    @Transactional
    public void deleteOne(ID id) {
        repository.deleteById(id);
    }

    /**
     * Retrieves the entity with the given ID from the repository. If the entity does not exist,
     * throws an EntityNotFoundException with the specified message and status code.
     *
     * @param id the identifier of the entity
     * @return the retrieved entity
     * @throws EntityNotFoundException if the entity is not found in the repository
     */
    protected ENTITY getOrThrow(ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found", 403));
    }

}
