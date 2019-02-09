package ru.icoltd.rvs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.service.RestaurantService;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public void setService(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model) {

        List<Restaurant> restaurants = service.getRestaurants();
        model.addAttribute("restaurants", restaurants);

        return "restaurants";
    }

    @GetMapping("/{restaurantId}/showMenus")
    public String listMenus(@PathVariable(name = "restaurantId") int restaurantId, Model model) {

        Restaurant restaurant = service.getRestaurant(restaurantId);
        List<Menu> menus = restaurant.getMenus();
        model.addAttribute("menus", menus);

        return "menus";
    }
}
