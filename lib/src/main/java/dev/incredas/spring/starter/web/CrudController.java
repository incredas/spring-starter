package dev.incredas.spring.starter.web;

import dev.incredas.spring.starter.core.CrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("squid:S119")
public class CrudController<ID, REQUEST, QUERY, RESPONSE> {

    private CrudService<ID, REQUEST, QUERY, RESPONSE> crudService;

    public CrudController(CrudService<ID, REQUEST, QUERY, RESPONSE> crudService) {
        this.crudService = crudService;
    }

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
    @ResponseStatus(value = HttpStatus.OK)
    private Page<RESPONSE> getAll(QUERY query, Pageable pageable) {
        return crudService.getAll(query, pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    private void deleteOne(@PathVariable(value = "id") ID id) {
        crudService.deleteOne(id);
    }

}
