package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private MenuService service;

    @Autowired
    public void setService(MenuService service) {
        this.service = service;
    }

    @GetMapping("/{menuId}/showDetails")
    public String showMenuDetails(@PathVariable("menuId") int menuId, Model model) {

        Menu menu = service.getMenu(menuId);

        model.addAttribute("dishes", menu.getDishes());
        model.addAttribute("votes", menu.getVotes());
        model.addAttribute("menu", menu);

        return "menuDetails";
    }
}
