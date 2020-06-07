package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(DishController.DISH_BASE_PATH)
@RequiredArgsConstructor
public class DishController {

    public static final String DISH_BASE_PATH = "/restaurants/{restId}/menus/{menuId}/dishes";

    private final DishService dishService;

    private final MenuService menuService;

    @ModelAttribute("menu")
    public MenuDto findMenu(@PathVariable("menuId") Long menuId) {
        return menuService.findById(menuId);
    }

    @InitBinder("menu")
    public void initRestaurantBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "name");
    }

    @GetMapping
    public String findDishes(@PathVariable("menuId") Long id, @PathVariable("restId") Long restaurantId,
                             Model model) {
        List<DishDto> dishes = dishService.findAllByMenuId(id);
        model.addAttribute("dishes", dishes);
        model.addAttribute("restaurantId", restaurantId);
        return "dishes";
    }

    @GetMapping("/new")
    public String showFormForAdd(@PathVariable("restId") Long restaurantId, Model model) {
        model.addAttribute("dish", DishDto.builder().build());
        model.addAttribute("restaurantId", restaurantId);
        return "dish-new";
    }

    @PostMapping
    public String saveDish(@ModelAttribute("menu") MenuDto menu,
                           @Valid @ModelAttribute("dish") DishDto dish, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Dish save error: {}", bindingResult);
            return "dish-new";
        }
        dish.setMenu(menu);
        dishService.save(dish);
        return "redirect:" + DISH_BASE_PATH;
    }

    @GetMapping("/{id}/update")
    public String updateDish(@PathVariable("restId") Long restaurantId, @PathVariable("id") Long id, Model model) {
        model.addAttribute("dish", dishService.findById(id));
        model.addAttribute("restaurantId", restaurantId);
        return "dish-new";
    }

    @GetMapping("/{id}/delete")
    public String deleteDish(@PathVariable("id") Long id) {
        dishService.deleteById(id);
        return "redirect:" + DISH_BASE_PATH;
    }
}
