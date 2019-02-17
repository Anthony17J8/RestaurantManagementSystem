package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.service.MenuService;

import java.time.LocalDateTime;

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

        Vote vote = new Vote();

        model.addAttribute("dishes", menu.getDishes());
        model.addAttribute("votes", menu.getVotes());
        model.addAttribute("menu", menu);
        model.addAttribute("vote", vote);

        return "menuDetails";
    }

    @PostMapping("/{menuId}/addVote")
    public String voteForMenu(@ModelAttribute("menu") Menu menu) {
        Vote vote = new Vote(menu, LocalDateTime.now());
        voteDAO.saveVote(vote);

        return "redirect:/menu/{menuId}/showDetails";
    }
}
