package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.entity.Dish;

@Mapper
public interface DishMapper {

    @Mapping(target = "menu", ignore = true)
    Dish dishDtoToDish(DishDto dishDto);

    @Mapping(target = "menu", ignore = true)
    DishDto dishToDishDto(Dish dish);
}
