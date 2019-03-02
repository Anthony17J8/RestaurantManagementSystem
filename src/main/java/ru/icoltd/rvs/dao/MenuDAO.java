package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Menu;

public interface MenuDAO {

    Menu findById(int menuId);

    void saveMenu(Menu menu);

    void deleteMenu(Menu menu);
}
