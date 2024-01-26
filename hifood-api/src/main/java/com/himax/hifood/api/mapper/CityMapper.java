package com.himax.hifood.api.mapper;

import com.himax.hifood.api.model.city.CityInputDto;
import com.himax.hifood.api.model.city.CityOutputDto;
import com.himax.hifood.domain.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {
    //@Mapping(target = "state.name", source = "state.name")
    CityOutputDto toDto(City domain);
    City toDomain(CityInputDto dto);
    List<CityOutputDto> toListDto(List<City> list);
    City toUpdate(City updated, @MappingTarget City existing);
}
