package dev.incredas.spring.starter.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("squid:S119")
public interface CrudService<ID, REQUEST, QUERY, RESPONSE> {

    RESPONSE createOne(REQUEST request);

    RESPONSE updateOne(ID id, REQUEST request);

    RESPONSE getOne(ID id);

    Page<RESPONSE> getAll(QUERY query, Pageable pageable);

    void deleteOne(ID id);

}
