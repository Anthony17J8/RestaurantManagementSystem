package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.DateTimeUtils;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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
    public List<Menu> getBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        return dao.getBetweenDates(
                Optional.ofNullable(startDate).orElse(ZonedDateTime.of(DateTimeUtils.MIN_DATE, LocalTime.MIN, DateTimeUtils.ZONE_ID_UTC)),
                Optional.ofNullable(endDate).orElse(ZonedDateTime.of(DateTimeUtils.MAX_DATE, LocalTime.MAX, DateTimeUtils.ZONE_ID_UTC))
        );
    }
}
