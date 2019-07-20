package ru.icoltd.rvs.aspects;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import ru.icoltd.rvs.controller.DishController;
import ru.icoltd.rvs.controller.MenuController;
import ru.icoltd.rvs.controller.RestaurantController;

@ControllerAdvice(assignableTypes = {RestaurantController.class,
        MenuController.class,
        DishController.class})
public class GlobalInitBinder {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
