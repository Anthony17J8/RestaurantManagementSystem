package ru.icoltd.rvs.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuDAO menuDAO;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        return menuDAO.makePersistent(menu);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        menuDAO.removeById(id);
    }

    @Override
    public List<Menu> findAllByRestaurantId(Long restId) {
        return Lists.newArrayList(menuDAO.findAllByRestaurantId(restId));
    }

    @Override
    public Menu findById(Long menuId) {
        return menuDAO.findById(menuId).orElseThrow(
                () -> new ObjNotFoundException("Menu id not found: " + menuId)
        );
    }
}
