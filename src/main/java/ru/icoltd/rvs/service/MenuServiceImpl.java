package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.MenuMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuDAO menuDAO;

    private final MenuMapper mapper;

    @Override
    @Transactional
    public MenuDto save(MenuDto menu) {
        return mapper.fromMenu(menuDAO.makePersistent(mapper.toMenu(menu)));
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        menuDAO.removeById(id);
    }

    @Override
    public List<MenuDto> findAllByRestaurantId(Long restId) {
        return StreamSupport.stream(menuDAO.findAllByRestaurantId(restId).spliterator(), false)
                .map(mapper::fromMenu)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDto findById(Long menuId) {
        return mapper.fromMenu(menuDAO.findById(menuId).orElseThrow(
                () -> new ObjNotFoundException("Menu id not found: " + menuId)
        ));
    }
}
