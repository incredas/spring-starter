package dev.incredas.spring.starter.core;

import dev.incredas.spring.starter.persistence.AuditableEntity;
import dev.incredas.spring.starter.web.Request;
import dev.incredas.spring.starter.web.Response;
import dev.incredas.spring.starter.exception.EntityNotFoundException;
import dev.incredas.spring.starter.persistence.EntityRepository;
import dev.incredas.spring.starter.persistence.EntitySpecification;
import dev.incredas.spring.starter.web.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("squid:S119")
public abstract class CrudServiceImpl<ID, REQUEST extends Request, QUERY extends Query, RESPONSE extends Response<ID>,
        ENTITY extends AuditableEntity>
        implements CrudService<ID, REQUEST, QUERY, RESPONSE> {

    @Autowired
    private EntityRepository<ENTITY, ID> repository;

    @Autowired
    private Mapper<REQUEST, ENTITY, RESPONSE> mapper;

    @Autowired
    private EntitySpecification<ENTITY, QUERY> specification;

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
    public void deleteOne(ID id) {
        repository.deleteById(id);
    }

    protected ENTITY getOrThrow(ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found", 403));
    }

}
