package dev.incredas.spring.starter.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The CrudService interface provides methods for performing CRUD operations on a resource.
 *
 * @param <ID>       the type of the resource identifier
 * @param <REQUEST>  the type of the request object for create and update operations
 * @param <QUERY>    the type of the query object for retrieving multiple resources
 * @param <RESPONSE> the type of the response object
 */
@SuppressWarnings("squid:S119")
public interface CrudService<ID, REQUEST, QUERY, RESPONSE> {

    /**
     * Creates a new resource.
     *
     * @param request the request object for creating the resource
     * @return the created resource response model
     */
    RESPONSE createOne(REQUEST request);

    /**
     * Updates an existing resource identified by the given ID with the provided request data.
     *
     * @param id      the identifier of the resource
     * @param request the request object containing the updated data
     * @return the updated resource response model
     */
    RESPONSE updateOne(ID id, REQUEST request);

    /**
     * Retrieves a single resource with the given ID.
     *
     * @param id the identifier of the resource
     * @return the retrieved resource response model
     */
    RESPONSE getOne(ID id);

    /**
     * Retrieves a page of resources based on the given query and pagination parameters.
     *
     * @param query    the query object used for filtering and sorting
     * @param pageable the pagination parameters (page number, page size, sort order)
     * @return the page of retrieved resources response models
     */
    Page<RESPONSE> getAll(QUERY query, Pageable pageable);


    /**
     * Deletes a single resource with the given ID.
     *
     * @param id the identifier of the resource to be deleted
     */
    void deleteOne(ID id);

}
