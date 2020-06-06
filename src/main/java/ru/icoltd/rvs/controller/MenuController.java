package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(MenuController.MENU_BASE_PATH)
public class MenuController {

    public static final String MENU_BASE_PATH = "/restaurants/{restId}/menus";

    private final MenuService menuService;

    private final VoteService voteService;

    private final RestaurantService restaurantService;

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
    public String voteForMenu(@PathVariable("id") Long menuId, @CurrentUser User currentUser,
                              RedirectAttributes rAttributes) {
        var menu = menuService.findById(menuId);
        var now = LocalDateTime.now();
        if (DateTimeUtils.isMenuOutDated(menu.getDate().toLocalDate(), now.toLocalDate())) {
            rAttributes.addFlashAttribute("error",
                    "Selected Menu has been outdated. Please choose another one");
            return "redirect:" + MENU_BASE_PATH;
        }

        var usersVote = voteService.getLatestVoteByUserId(currentUser.getId());
        if (Objects.nonNull(usersVote)) {
            if (menu.getId().equals(usersVote.getMenu().getId())) {
                log.info("User has already voted for this menu");
                rAttributes.addFlashAttribute("error", "Your vote has already been counted");
                return "redirect:" + MENU_BASE_PATH;
            }

            if (DateTimeUtils.isWithinVoteInterval(usersVote.getDateTime(), now)) {
                usersVote.setDateTime(now);
                usersVote.setMenu(menu);
            }
        }

        voteService.createNewVote(usersVote);
        rAttributes.addFlashAttribute("success", "Your vote has been successfully counted!");
        return "redirect:" + MENU_BASE_PATH;
    }

    @GetMapping("/new")
    public String showAddMenuForm(Model model) {
        model.addAttribute("menu", MenuDto.builder().build());
        return "menu-new";
    }

    @PostMapping
    public String saveMenu(@Valid @ModelAttribute("menu") MenuDto menu,
                           BindingResult bindingResult, @ModelAttribute("restaurant") RestaurantDto restaurant) {
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
