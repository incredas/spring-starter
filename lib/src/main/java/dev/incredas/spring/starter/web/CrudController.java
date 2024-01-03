package dev.incredas.spring.starter.web;

import dev.incredas.spring.starter.core.CrudService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The CrudController class is a generic controller class that handles CRUD operations for a specific resource.
 *
 * @param <ID>      the type of the resource identifier
 * @param <REQUEST> the type of the HTTP request body for create and update operations
 * @param <QUERY>   the type of the HTTP request parameters for filtering and sorting operations
 * @param <RESPONSE> the type of the HTTP response body
 */
@RequiredArgsConstructor
@SuppressWarnings("squid:S119")
public class CrudController<ID, REQUEST, QUERY, RESPONSE> {

    private final CrudService<ID, REQUEST, QUERY, RESPONSE> crudService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    private RESPONSE createOne(@RequestBody @Valid REQUEST request) {
        return crudService.createOne(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private RESPONSE updateOne(@PathVariable(value = "id") ID id, @RequestBody @Valid REQUEST request) {
        return crudService.updateOne(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    private RESPONSE getOne(@PathVariable(value = "id") ID id) {
        return crudService.getOne(id);
    }

    @GetMapping
    @PageableAsQueryParam
    @ResponseStatus(value = HttpStatus.OK)
    private Page<RESPONSE> getAll(QUERY query, @Parameter(hidden = true) Pageable pageable) {
        return crudService.getAll(query, pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    private void deleteOne(@PathVariable(value = "id") ID id) {
        crudService.deleteOne(id);
    }

}
