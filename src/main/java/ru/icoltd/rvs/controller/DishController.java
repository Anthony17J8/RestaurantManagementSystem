package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import javax.validation.Valid;

@Controller
@RequestMapping("/dish")
@SessionAttributes(value = {"dish", "menuId"})
@Slf4j
public class DishController {

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
                           @RequestParam("menuId") int menuId, SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()){
          log.error("Dish save error: {}", bindingResult);
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
