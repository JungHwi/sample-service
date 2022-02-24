package com.breeze.idus.user.converter;

import com.breeze.idus.user.persistence.entity.LastActionEntity;
import com.breeze.idus.user.service.vo.LastAction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LastActionConverter {

    LastAction converts(LastActionEntity entity);

    @Mapping(target = "user", ignore = true)
    LastActionEntity convertsEntity(LastAction vo);
}
