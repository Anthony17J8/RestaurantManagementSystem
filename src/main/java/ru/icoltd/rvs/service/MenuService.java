package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuService {

    Menu getMenu(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);

    List<Menu> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<Menu> findAllByRestaurantId(int restaurantId);
}
