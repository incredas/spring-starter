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

@Service
@RequiredArgsConstructor
@SuppressWarnings("squid:S119")
public abstract class CrudServiceImpl<ID, REQUEST, QUERY, RESPONSE, ENTITY extends AuditableEntity,
        REPOSITORY extends CrudRepository<ENTITY, ID> & JpaSpecificationExecutor<ENTITY>>
        implements CrudService<ID, REQUEST, QUERY, RESPONSE> {

    private final REPOSITORY repository;

    private final Mapper<REQUEST, ENTITY, RESPONSE> mapper;

    private final EntitySpecification<ENTITY, QUERY> specification;

    @Override
    @Transactional
    public RESPONSE createOne(REQUEST request) {
        ENTITY entity = mapper.toEntity(request);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public RESPONSE updateOne(ID id, REQUEST request) {
        ENTITY entity = this.getOrThrow(id);
        mapper.toEntity(request, entity);
        ENTITY savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public RESPONSE getOne(ID id) {
        ENTITY entity = this.getOrThrow(id);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RESPONSE> getAll(QUERY query, Pageable pageable) {
        Specification<ENTITY> querySpecification = specification.getSpecification(query);
        return repository.findAll(querySpecification, pageable)
                .map(mapper::toResponse);
    }

    @Override
    @Transactional
    public void deleteOne(ID id) {
        repository.deleteById(id);
    }

    protected ENTITY getOrThrow(ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found", 403));
    }

}
