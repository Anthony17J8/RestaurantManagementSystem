package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
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
@RequestMapping("/restaurant/{restId}/menu")
public class MenuController {

    private MenuService menuService;

    private VoteService voteService;

    private RestaurantService restaurantService;

    private MessageSource messageSource;

    @Autowired
    public MenuController(MenuService menuService, VoteService voteService, RestaurantService restaurantService,
                          MessageSource messageSource) {
        this.menuService = menuService;
        this.voteService = voteService;
        this.restaurantService = restaurantService;
        this.messageSource = messageSource;
    }

    @InitBinder("restaurant")
    public void initRestaurantBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "name");
    }

    @ModelAttribute("restaurant")
    public Restaurant restaurant(@PathVariable("restId") Long restId) {
        return restaurantService.findById(restId);
    }

    @GetMapping("/showAll")
    public String listMenus(@PathVariable("restId") Long restId, Model model) {
        List<Menu> menus = menuService.findAllByRestaurantId(restId);
        model.addAttribute("menus", menus);
        return "menu-list";
    }

    @PostMapping("/{id}/addVote")
    public String voteForMenu(Model model, @PathVariable("id") Long menuId, @CurrentUser User currentUser,
                              Restaurant restaurant) {
        Menu menu = new Menu();
        LocalDateTime now = LocalDateTime.now();
        if (DateTimeUtils.isNotAfter(menu.getDate(), now.toLocalDate())) {
            voteService.saveOrUpdateVote(menu, now, currentUser);
        } else {
            model.addAttribute("message", messageSource.getMessage("error.vote.date",
                    new Object[]{menu.getName(), menu.getDate()},
                    Locale.getDefault()));
            return "error-page";
        }
        return "redirect:/restaurant/{restId}/menu/showAll";
    }

    @GetMapping("/showFormForAdd")
    public String showAddMenuForm(Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        return "menu-form";
    }

    @PostMapping("/save")
    public String saveMenu(Restaurant restaurant, @Valid @ModelAttribute("menu") Menu menu,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Save menu error {}", bindingResult);
            return "menu-form";
        }
        menu.setRestaurant(restaurant);
        menuService.save(menu);
        return "redirect:/restaurant/{restId}/menu/showAll";
    }

    @GetMapping("/{id}/delete")
    public String deleteMenu(@PathVariable("id") Long menuId) {
        menuService.removeById(menuId);
        return "redirect:/restaurant/{restId}/menu/showAll";
    }

    @GetMapping("/{id}/update")
    public String updateMenu(@PathVariable("id") Long menuId, Model model) {
        model.addAttribute("menu", menuService.findById(menuId));
        return "menu-form";
    }
}
