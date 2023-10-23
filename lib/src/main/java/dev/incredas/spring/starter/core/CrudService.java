package dev.incredas.spring.starter.core;

import dev.incredas.spring.starter.web.Request;
import dev.incredas.spring.starter.web.Response;
import dev.incredas.spring.starter.web.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("squid:S119")
public interface CrudService<ID, REQUEST extends Request, QUERY extends Query, RESPONSE extends Response<ID>> {

    RESPONSE createOne(REQUEST request);

    RESPONSE updateOne(ID id, REQUEST request);

    RESPONSE getOne(ID id);

    Page<RESPONSE> getAll(QUERY query, Pageable pageable);

    void deleteOne(ID id);

}
