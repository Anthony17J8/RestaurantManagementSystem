package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;

@Mapper
public interface DishMapper {

    Dish toDish(DishDto dishDto);

    DishDto fromDish(Dish dish);

    @Mapping(source = "restaurant", target = "restaurant", ignore = true)
    MenuDto fromMenu(Menu menu);

    @Mapping(source = "restaurant", target = "restaurant", ignore = true)
    Menu toMenu(MenuDto menuDto);
}
