package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

@Controller
@RequestMapping("/dish")
public class DishController {

    private static final String TITLE_NEW = "NEW";

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
        model.addAttribute("title", TITLE_NEW);
        model.addAttribute("dish", dish);
        model.addAttribute("menuId", menuId);
        return "dish-form";
    }

    @PostMapping("/save")
    public String saveDish(@ModelAttribute("dish") Dish dish, @RequestParam("menuId") int menuId) {
        dish.setMenu(menuService.getMenu(menuId));
        dishService.saveDish(dish);
        return "redirect:/menu/update?menuId=" + menuId;
    }

    @GetMapping("/update")
    public String updateDish(@RequestParam("menuId") int menuId, @RequestParam("dishId") int dishId, Model model) {
        Dish dish = dishService.getDish(dishId);
        model.addAttribute("dish", dish);
        model.addAttribute("menuId", menuId);
        return "dish-form";
    }

    @GetMapping("/delete")
    public String deleteDish(@RequestParam("menuId") int menuId, @RequestParam("dishId") int dishId){
        Dish dish = dishService.getDish(dishId);
        dishService.deleteDish(dish);
        return "redirect:/menu/update?menuId=" + menuId;
    }
}