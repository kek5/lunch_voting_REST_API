package assignment.web.controllers;

import assignment.config.ApiConfig;
import assignment.services.impl.RestaurantService;
import assignment.web.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by kek5 on 5/18/18.
 */
@RestController
@RequestMapping
public class MenuController {
    private RestaurantService restaurantService;

    @Autowired
    public MenuController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PutMapping(ApiConfig.API_ENTRY + ApiConfig.VERSION + "/restaurants/{restaurant_id}/menu")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long restaurant_id, @RequestBody @Valid MenuDTO menuDTO) {
        restaurantService.updateMenu(restaurant_id, menuDTO);
        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }

    @GetMapping(ApiConfig.API_ENTRY + ApiConfig.VERSION + "/restaurants/{restaurant_id}/menu")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MenuDTO> getMenu(@PathVariable Long restaurant_id) {

        MenuDTO menuDTO = restaurantService.getMenu(restaurant_id);

        return new ResponseEntity<>(menuDTO, HttpStatus.OK);
    }
}
