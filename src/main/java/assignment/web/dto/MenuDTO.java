package assignment.web.dto;

import assignment.dao.models.Dish;
import assignment.dao.models.Menu;
import assignment.dao.models.Restaurant;
import assignment.web.controllers.MenuController;
import assignment.web.controllers.VoteController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kek5 on 5/18/18.
 */
public class MenuDTO extends ResourceSupport {
    @Getter
    @Setter
    private List<Dish> dishes;

    @Setter
    @Getter
    @JsonIgnore
    private Restaurant restaurant;



    public MenuDTO(final Menu menu) {
        this.setDishes(menu.getDishes());
        this.setRestaurant(menu.getRestaurant());
        this.add(linkTo(MenuController.class).withSelfRel());
        this.add(linkTo(methodOn(MenuController.class).getMenu(menu.getRestaurant().getId())).withRel("restaurant"));
        this.add(linkTo(methodOn(VoteController.class).vote(menu.getRestaurant().getId())).withRel("vote"));
    }

    public MenuDTO (List<Dish> dishes, Restaurant rest) {
        this.setRestaurant(rest);
        this.setDishes(dishes);
    }
}
