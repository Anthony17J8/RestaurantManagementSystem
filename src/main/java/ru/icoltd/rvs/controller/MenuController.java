package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.VoteService;
import ru.icoltd.rvs.user.CurrentUser;
import ru.icoltd.rvs.util.DateTimeUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(MenuController.MENU_BASE_PATH)
public class MenuController {

    public static final String MENU_BASE_PATH = "/restaurants/{restId}/menus";

    private final MenuService menuService;

    private final VoteService voteService;

    private final RestaurantService restaurantService;

    private final MessageSource messageSource;

    @InitBinder("restaurant")
    public void initRestaurantBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "name");
    }

    @ModelAttribute("restaurant")
    public RestaurantDto restaurant(@PathVariable("restId") Long restId) {
        return restaurantService.findById(restId);
    }

    @GetMapping
    public String listMenus(@PathVariable("restId") Long restId, Model model) {
        List<MenuDto> menus = menuService.findAllByRestaurantId(restId);
        model.addAttribute("menus", menus);
        return "menus";
    }

    @GetMapping("/{id}/vote")
    public String voteForMenu(Model model, @PathVariable("id") Long menuId, @CurrentUser User currentUser) {
        MenuDto menu = menuService.findById(menuId);
        LocalDateTime now = LocalDateTime.now();
        if (DateTimeUtils.isNotInPast(menu.getDate().toLocalDate(), now.toLocalDate())) {
            voteService.saveOrUpdateVote(menu, now, currentUser);
        } else {
            model.addAttribute("message", messageSource.getMessage("error.vote.date",
                    new Object[]{menu.getName(), menu.getDate().toLocalDate()},
                    Locale.getDefault()));
            return "error-page";
        }
        return "redirect:" + MENU_BASE_PATH;
    }

    @GetMapping("/new")
    public String showAddMenuForm(Model model) {
        model.addAttribute("menu", MenuDto.builder().build());
        return "menu-new";
    }

    @PostMapping
    public String saveMenu(@Valid @ModelAttribute("menu") MenuDto menu,
                           BindingResult bindingResult, RestaurantDto restaurant) {
        if (bindingResult.hasErrors()) {
            log.error("Save menu error {}", bindingResult);
            return "menu-new";
        }
        menu.setRestaurant(restaurant);
        menuService.save(menu);
        return "redirect:" + MENU_BASE_PATH;
    }

    @GetMapping("/{id}/delete")
    public String deleteMenu(@PathVariable("id") Long menuId) {
        menuService.removeById(menuId);
        return "redirect:" + MENU_BASE_PATH;
    }

    @GetMapping("/{id}/update")
    public String updateMenu(@PathVariable("id") Long menuId, Model model) {
        model.addAttribute("menu", menuService.findById(menuId));
        return "menu-new";
    }
}
