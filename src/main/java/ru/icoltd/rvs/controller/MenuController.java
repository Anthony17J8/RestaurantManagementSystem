package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.service.MenuService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private MenuService service;

    private VoteDAO voteDAO;

    @Autowired
    public void setService(MenuService service) {
        this.service = service;
    }

    @Autowired
    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    @GetMapping("/{menuId}/showDetails")
    public String showMenuDetails(@PathVariable("menuId") int menuId, Model model) {

        Menu menu = service.getMenu(menuId);
        List<Dish> dishes = menu.getDishes().stream()
                .sorted(Comparator.comparing(Dish::getName))
                .collect(Collectors.toList());

        model.addAttribute("dishes", dishes);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurant", menu.getRestaurant());

        return "menuDetails";
    }

    @PostMapping("/{menuId}/addVote")
    public String voteForMenu(@ModelAttribute("menu") Menu menu) {
        Vote vote = new Vote(menu, LocalDateTime.now());
        voteDAO.saveVote(vote);

        return "redirect:/menu/{menuId}/showDetails";
    }
}
