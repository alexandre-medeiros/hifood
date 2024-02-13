package com.himax.hifood.api.mapper;

import com.himax.hifood.domain.model.Restaurant;
import com.himax.hifood.api.model.restaurant.RestaurantInputDto;
import com.himax.hifood.api.model.restaurant.RestaurantOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

    @Mapping(target = "address.city.state", source = "address.city.state.name")
    RestaurantOutputDto toDto(Restaurant domain);
    Restaurant toDomain(RestaurantInputDto dto);
    List<RestaurantOutputDto> toListDto(List<Restaurant> list);
    Restaurant toUpdate(Restaurant updated, @MappingTarget Restaurant existing);
}
