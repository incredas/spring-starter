package dev.incredas.spring.starter.persistence;

import org.springframework.data.jpa.domain.Specification;

/**
 * The EntitySpecification interface represents a specification for querying entities based on a specific query.
 *
 * @param <ENTITY> the type of the entity being queried
 * @param <QUERY>  the type of the query object used for filtering
 */
@SuppressWarnings("squid:S119")
public interface EntitySpecification<ENTITY extends AuditableEntity, QUERY> {

    /**
     * Retrieves a specification for querying entities based on a specific query.
     *
     * @param query the query object used for filtering
     * @return the specification for querying entities
     */
    Specification<ENTITY> getSpecification(final QUERY query);

}
