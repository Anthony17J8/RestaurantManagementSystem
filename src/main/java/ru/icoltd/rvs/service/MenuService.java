package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;

import java.time.ZonedDateTime;
import java.util.List;

public interface MenuService {

    Menu getMenu(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);

    List<Menu> getBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate);
}
