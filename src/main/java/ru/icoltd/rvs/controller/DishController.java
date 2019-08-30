package ru.icoltd.rvs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import javax.validation.Valid;

@Controller
@RequestMapping("/dish")
public class DishController {

    private static final Logger log = LoggerFactory.getLogger(DishController.class);

    private DishService dishService;

    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model, @RequestParam("menuId") int menuId) {
        Dish dish = new Dish();
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("dish", dish);
        model.addAttribute("menuId", menu.getId());
        return "dish-form";
    }

    @PostMapping("/save")
    public String saveDish(@Valid @ModelAttribute("dish") Dish dish, BindingResult bindingResult,
                           @RequestParam("menuId") int menuId, Model model) {
        if (bindingResult.hasErrors()) {
          log.error("Dish save error: {}", bindingResult);
          model.addAttribute("menuId", menuId);
          model.addAttribute("dish", dish);
          return "dish-form";
        }
        dish.setMenu(menuService.getMenu(menuId));
        dishService.saveDish(dish);
        return "redirect:/menu/update?menuId=" + menuId;
    }

    @GetMapping("/update")
    public String updateDish(@RequestParam("dishId") int dishId, Model model) {
        Dish dish = dishService.getDish(dishId);
        model.addAttribute("dish", dish);
        model.addAttribute("menuId", dish.getMenu().getId());
        return "dish-form";
    }

    @GetMapping("/delete")
    public String deleteDish(@RequestParam("dishId") int dishId) {
        Dish dish = dishService.getDish(dishId);
        dishService.deleteDish(dish);
        return "redirect:/menu/update?menuId=" + dish.getMenu().getId();
    }
}
