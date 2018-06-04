package assignment.web.controllers;

import assignment.config.ApiConfig;
import assignment.services.impl.RestaurantService;
import assignment.web.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

/**
 * Created by kek5 on 4/20/18.
 */
@RestController
@RequestMapping(ApiConfig.API_ENTRY + ApiConfig.VERSION + "/restaurants")
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resources<RestaurantDTO>> getRestaurants() {
        return new ResponseEntity<>(this.restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getOne(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody @Valid final RestaurantDTO restaurantDTO) {
        RestaurantDTO persistedRestaurant = this.restaurantService.addRestaurant(restaurantDTO);

        return new ResponseEntity<>(persistedRestaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpStatus deleteRestaurant(@PathVariable Long id) {
        this.restaurantService.deleteRestaurant(id);

        return HttpStatus.OK;
    }
}
