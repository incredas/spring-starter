package dev.incredas.spring.starter.core;

import org.mapstruct.MappingTarget;

@SuppressWarnings("squid:S119")
public interface Mapper<REQUEST, ENTITY, RESPONSE> {

    ENTITY toEntity(REQUEST request);

    void toEntity(REQUEST request, @MappingTarget ENTITY entity);

    RESPONSE toResponse(ENTITY entity);

}
