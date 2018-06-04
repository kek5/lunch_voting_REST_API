package assignment.services.impl;

import assignment.dao.models.Dish;
import assignment.dao.models.Menu;
import assignment.dao.models.Restaurant;
import assignment.dao.repositories.DishRepository;
import assignment.dao.repositories.MenuRepository;
import assignment.dao.repositories.RestaurantRepository;
import assignment.web.dto.MenuDTO;
import assignment.web.dto.RestaurantDTO;
import assignment.web.errors.exceptions.AlreadyExistsException;
import assignment.web.errors.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;
    private MenuRepository menuRepository;
    private ModelMapper modelMapper;
    private DishRepository dishRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             ModelMapper modelMapper,
                             MenuRepository menuRepository,
                             DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }


    public Resources<RestaurantDTO> getAll() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        List<RestaurantDTO> allRestaurantsDTO = allRestaurants.stream().map(RestaurantDTO::new).collect(Collectors.toList());

        Resources<RestaurantDTO> resources = new Resources<>(allRestaurantsDTO);
        final String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uri, "self"));

        return resources;
    }

    public RestaurantDTO getOne(final Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Restaurant", "id", id));

        return new RestaurantDTO(restaurant);
    }

    public RestaurantDTO addRestaurant(final RestaurantDTO restaurantDTO) {
        Optional<Restaurant> opt = restaurantRepository.findByName(restaurantDTO.getName());
        opt.ifPresent(rest -> {throw new AlreadyExistsException("Restaurant", "name", rest.getName());});

        Restaurant createdRestaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        restaurantRepository.save(createdRestaurant);

        return new RestaurantDTO(createdRestaurant);
    }

    public void deleteRestaurant(final Long id) {
        Restaurant restaurant = this.getIfExist(id);
        restaurantRepository.delete(restaurant);
    }

    @Transactional
    public HttpStatus updateMenu(final Long restaurant_id, final MenuDTO newMenuDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElseThrow(
                () -> new NotFoundException("Restaurant", "id", restaurant_id));


        Menu menu = modelMapper.map(newMenuDTO, Menu.class);
        menu.setRestaurant(restaurant);
        for (Dish dish : menu.getDishes()) {
            dish.setMenu(menu);
        }
        restaurant.setMenu(menu);
        restaurantRepository.save(restaurant);


        return HttpStatus.OK;
    }

    public MenuDTO getMenu(final Long restaurant_id) {
        Restaurant restaurant = this.getIfExist(restaurant_id);
        Menu menu = restaurant.getMenu();

        return new MenuDTO(menu);
    }



    private Restaurant getIfExist(final Long id) throws NotFoundException {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant", "id", id));
    }
    public void checkIfExist(final Long id) throws NotFoundException {
        restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant", "id", id));
    }
}
