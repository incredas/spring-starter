package dev.incredas.spring.starter.persistence;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("squid:S119")
public interface EntitySpecification<ENTITY extends AuditableEntity, QUERY> {

    Specification<ENTITY> getSpecification(final QUERY query);

}
