package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {

    Menu getMenu(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);

    List<Menu> getBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Menu> findAllByRestaurantId(int restaurantId);
}
