package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.Menu;

@Mapper
public interface MenuMapper {

    @Mapping(target = "restaurant", ignore = true)
    MenuDto menuToMenuDto(Menu menu);

    Menu menuDtoToMenu(MenuDto menuDto);
}
