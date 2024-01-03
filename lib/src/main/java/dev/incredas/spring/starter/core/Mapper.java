package dev.incredas.spring.starter.core;

import org.mapstruct.MappingTarget;

/**
 * The Mapper interface provides methods for mapping between request objects, entity objects, and response objects.
 *
 * @param <REQUEST>  the type of the request object
 * @param <ENTITY>   the type of the entity object
 * @param <RESPONSE> the type of the response object
 */
@SuppressWarnings("squid:S119")
public interface Mapper<REQUEST, ENTITY, RESPONSE> {

    /**
     * Converts a request object to an entity object.
     *
     * @param request the request object to convert
     * @return the converted entity object
     */
    ENTITY toEntity(REQUEST request);

    /**
     * Converts a request object to an entity object.
     *
     * @param request The request object containing the data to be mapped to the entity.
     * @param entity  The entity to be updated based on the fields in the request.
     *                The existing values in the entity will be modified or overridden by the corresponding values
     *                in the request.
     */
    void toEntity(REQUEST request, @MappingTarget ENTITY entity);

    /**
     * Converts an entity object to a response object.
     *
     * @param entity the entity object to convert
     * @return the converted response object
     */
    RESPONSE toResponse(ENTITY entity);

}
