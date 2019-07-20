package ru.icoltd.rvs.aspects;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.icoltd.rvs.controller.DishController;
import ru.icoltd.rvs.controller.MenuController;
import ru.icoltd.rvs.controller.RestaurantController;
import ru.icoltd.rvs.exception.ObjNotFoundException;

@ControllerAdvice(assignableTypes = {
        RestaurantController.class,
        MenuController.class,
        DishController.class
})
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handle(Model model, ObjNotFoundException exc) {
        model.addAttribute("message", exc.getMessage());
        return "error-page";
    }
}
