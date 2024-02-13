package com.himax.hifood.api.mapper;

import com.himax.hifood.api.model.kitchen.KitchenInputDto;
import com.himax.hifood.api.model.kitchen.KitchenOutputDto;
import com.himax.hifood.domain.model.Kitchen;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface KitchenMapper {
    KitchenOutputDto toDto(Kitchen domain);
    Kitchen toDomain(KitchenInputDto dto);
    List<KitchenOutputDto> toListDto(List<Kitchen> list);
    Kitchen toUpdate(Kitchen updated, @MappingTarget Kitchen existing);
}
