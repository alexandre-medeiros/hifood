package com.himax.hifood.api.mapper;

import com.himax.hifood.api.model.state.StateInputDto;
import com.himax.hifood.api.model.state.StateOutputDto;
import com.himax.hifood.domain.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StateMapper {
    StateOutputDto toDto(State domain);
    State toDomain(StateInputDto dto);
    List<StateOutputDto> toListDto(List<State> list);
    @Mapping(target = "id", ignore = true)
    State toUpdate(State updated, @MappingTarget State existing);
}
