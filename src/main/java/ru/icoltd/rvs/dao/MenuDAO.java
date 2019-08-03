package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Menu;

import java.time.ZonedDateTime;
import java.util.List;

public interface MenuDAO {

    Menu findById(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);

    List<Menu> getBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate);
}
