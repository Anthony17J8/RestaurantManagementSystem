package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
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
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/restaurant/{restId}/menu/{menuId}/dish")
public class DishController {

    private final DishService dishService;
    private final MenuService menuService;

    public DishController(DishService dishService, MenuService menuService) {
        this.dishService = dishService;
        this.menuService = menuService;
    }

    @ModelAttribute("menu")
    public Menu findMenu(@PathVariable("menuId") Long menuId) {
        return menuService.findById(menuId);
    }

    @InitBinder("menu")
    public void initRestaurantBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "name");
    }

    @GetMapping("/showAll")
    public String findDishes(@PathVariable("menuId") Long id, Model model) {
        List<Dish> dishes = dishService.findAllByMenuId(id);
        model.addAttribute("dishes", dishes);
        return "dish-list";
    }

    @GetMapping("/addNew")
    public String showFormForAdd(Model model) {
        Dish dish = new Dish();
        model.addAttribute("dish", dish);
        return "dish-form";
    }

    @PostMapping("/save")
    public String saveDish(@Valid @ModelAttribute("dish") Dish dish, BindingResult bindingResult,
                           Menu menu, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Dish save error: {}", bindingResult);
            model.addAttribute("dish", dish);
            return "dish-form";
        }
        dish.setMenu(menu);
        dishService.save(dish);
        return "redirect:/restaurant/{restId}/menu/{menuId}/dish/showAll";
    }

    @GetMapping("/{id}/update")
    public String updateDish(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dish", dishService.findById(id));
        return "dish-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteDish(@PathVariable("id") Long id) {
        dishService.deleteById(id);
        return "redirect:/restaurant/{restId}/menu/{menuId}/dish/showAll";
    }
}
