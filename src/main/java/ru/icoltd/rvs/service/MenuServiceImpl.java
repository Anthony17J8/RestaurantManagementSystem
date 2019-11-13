package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuDAO dao;

    @Autowired
    public MenuServiceImpl(MenuDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Menu getMenu(int menuId) {
        return Optional.ofNullable(dao.findById(menuId))
                .orElseThrow(
                        () -> new ObjNotFoundException("Menu id not found: " + menuId)
                );
    }

    @Override
    @Transactional
    public void saveMenu(Menu menu) {
        dao.saveMenu(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(Menu menu) {
        dao.deleteMenu(menu);
    }

    @Override
    @Transactional
    public List<Menu> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return dao.getBetweenDates(
                Optional.ofNullable(startDate).orElse(LocalDateTime.of(DateTimeUtils.MIN_DATE, LocalTime.MIN)),
                Optional.ofNullable(endDate).orElse(LocalDateTime.of(DateTimeUtils.MAX_DATE, LocalTime.MAX))
        );
    }

    @Override
    @Transactional
    public List<Menu> findAllByRestaurantId(int restaurantId) {
        return dao.findAllByRestaurantId(restaurantId);
    }
}
