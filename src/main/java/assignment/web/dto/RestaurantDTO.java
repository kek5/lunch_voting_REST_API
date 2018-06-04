package assignment.web.dto;

import assignment.dao.models.Restaurant;
import assignment.web.controllers.MenuController;
import assignment.web.controllers.RestaurantController;
import assignment.web.controllers.VoteController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import javax.validation.constraints.NotBlank;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by kek5 on 5/5/18.
 */
public class RestaurantDTO extends ResourceSupport{
    @Getter
    @Setter
    @NotBlank(message = "Please, enter restaurant name")
    private String name;



    public RestaurantDTO(){}
    public RestaurantDTO(String name) {
        this.setName(name);
    }
    public RestaurantDTO(final Restaurant restaurant) {
        this.setName(restaurant.getName());
        this.add(linkTo(methodOn(RestaurantController.class).getRestaurants()).withRel("restaurants"));
        this.add(linkTo(methodOn(RestaurantController.class).getRestaurant(restaurant.getId())).withSelfRel());
        this.add(linkTo(methodOn(MenuController.class).getMenu(restaurant.getId())).withRel("menu"));
        this.add(linkTo(methodOn(VoteController.class).vote(restaurant.getId())).withRel("vote"));
    }




}
