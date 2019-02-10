package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.entity.Menu;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuDAO dao;

    @Autowired
    public void setDao(MenuDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Menu getMenu(int menuId) {
        return dao.findById(menuId);
    }
}
