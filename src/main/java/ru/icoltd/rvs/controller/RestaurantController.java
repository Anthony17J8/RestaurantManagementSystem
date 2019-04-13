package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/restaurant")
@SessionAttributes("restaurant")
public class RestaurantController {

    private static final String TITLE_NEW = "NEW";

    private RestaurantService service;

    @Autowired
    public void setService(RestaurantService service) {
        this.service = service;
    }

    @InitBinder
    public void initDataBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/list")
    public String listRestaurants(Model model) {

        List<Restaurant> restaurants = service.getRestaurants();
        model.addAttribute("restaurants", restaurants);

        return "restaurant-list";
    }

    @GetMapping("/menus")
    public String listMenus(@RequestParam(name = "restId") int restaurantId, Model model) {
        Restaurant restaurant = getValidateRestaurant(restaurantId);
        model.addAttribute("restaurant", restaurant);
        List<Menu> menus = restaurant.getMenus();
        model.addAttribute("menus", menus);
        return "menu-list";
    }

    private Restaurant getValidateRestaurant(int restaurantId) {
        return Optional.ofNullable(service.getRestaurant(restaurantId))
                .orElseThrow(
                        () -> new ObjNotFoundException("Restaurant id not found: " + restaurantId)
                );
    }

    @GetMapping("/showFormForAdd")
    public String showAddRestaurantForm(ModelMap model) {
        Restaurant theRestaurant = new Restaurant();
        RestaurantDetail theDetail = new RestaurantDetail();
        model.addAttribute("title", TITLE_NEW);
        model.addAttribute("detail", theDetail);
        model.addAttribute("restaurant", theRestaurant);
        return "restaurant-form";
    }

    @PostMapping("/save")
    public String addRestaurant(@ModelAttribute("restaurant") @Valid Restaurant restaurant,
                                BindingResult restBindingResult,
                                @ModelAttribute("detail") @Valid RestaurantDetail detail,
                                BindingResult detailBindingResult) {

        if (restBindingResult.hasErrors() || detailBindingResult.hasErrors()) {
            return "restaurant-form";
        }

        restaurant.setRestaurantDetail(detail);
        service.saveRestaurant(restaurant);

        return "redirect:/restaurant/list";
    }

//    @GetMapping("/{restaurantId}/showAddMenuForm")
//    public String showAddMenuForm(@PathVariable("restaurantId") int restaurantId, Model model) {
//        Menu menu = new Menu();
//        Restaurant restaurant = getValidateRestaurant(restaurantId);
//        model.addAttribute("title", TITLE_NEW);
//        model.addAttribute("menu", menu);
//        model.addAttribute("restaurantId", restaurant.getId());
//
//        return "menu-form";
//    }

    @GetMapping("/delete")
    public String delete(@RequestParam("restId") int restaurantId) {

        Restaurant restaurant = service.getRestaurant(restaurantId);
        service.deleteRestaurant(restaurant);

        return "redirect:/restaurant/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("restId") int restaurantId, Model model) {

        Restaurant restaurant = service.getRestaurant(restaurantId);

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("detail", restaurant.getRestaurantDetail());

        return "restaurant-form";
    }

    @ExceptionHandler({ObjNotFoundException.class})
    public String handle(Model model, RuntimeException exc){
        model.addAttribute("message", exc.getMessage());
        return "error-page";
    }
}
