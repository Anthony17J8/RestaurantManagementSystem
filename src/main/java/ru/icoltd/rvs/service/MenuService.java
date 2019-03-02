package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;

public interface MenuService {

    Menu getMenu(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);
}
