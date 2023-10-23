package dev.incredas.spring.starter.persistence;

import dev.incredas.spring.starter.web.Query;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("squid:S119")
public interface EntitySpecification<ENTITY extends AuditableEntity, QUERY extends Query> {

    Specification<ENTITY> getSpecification(final QUERY query);

}
