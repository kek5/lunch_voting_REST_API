package assignment;

import assignment.config.SecurityConfig;
import assignment.dao.models.*;
import assignment.dao.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.*;

/**
 * Created by kek5 on 4/19/18.
 */
@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }





    @Autowired
    SecurityConfig securityConfig;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    MenuRepository menuRepository;



    @Bean
    public CommandLineRunner init() {
        return (evt) -> {

            // role creation
            Role user_role = new Role(1L, "USER");
            Role admin_role = new Role(2L, "ADMIN");
            Set<Role> roles = new HashSet<>(Arrays.asList(user_role, admin_role));
            Set<Role> admins = new HashSet<>(Collections.singletonList(admin_role));
            roleRepository.saveAll(roles);
            MyUser admin = new MyUser("admin", securityConfig.passwordEncoder().encode("admin"), admins);
            userRepository.save(admin);

            // user creation
            MyUser user = new MyUser("user", securityConfig.passwordEncoder().encode("user"), new HashSet<>(Collections.singletonList(user_role)));
            userRepository.save(user);


            // restaurant creation
            Restaurant restaurant = new Restaurant();
            restaurant.setName("rest_test");
            restaurant.setId(1L);
            restaurant = restaurantRepository.save(restaurant);

            Menu menu = new Menu();
            menu.setId(1L);
            menu.setRestaurant(restaurant);
            menu = menuRepository.save(menu);

            // menu creation
            Dish dish1 = new Dish();
            dish1.setId(1L);
            dish1.setName("dish1");
            dish1.setPrice(1);
            dish1.setMenu(menu);
            Dish dish2 = new Dish();
            dish2.setId(2L);
            dish2.setName("dish2");
            dish2.setPrice(2);
            dish2.setMenu(menu);
            List<Dish> dishes = new ArrayList<>(Arrays.asList(dish1, dish2));
            dishRepository.saveAll(dishes);

//            List<Dish> dishes = dishRepository.save(new ArrayList<>(Arrays.asList(dish1, dish2)));

//            menu.setDishes(dishes);
//            MenuDTO menuDTO = new MenuDTO(new ArrayList<>(Arrays.asList(dish1, dish2)), 1L);
//            menu = menuRepository.save(menu);


            restaurant.setMenu(menu);
            restaurantRepository.save(restaurant);

        };
    }




    // users
//        Role user = new Role(1L, "USER");
//        Role admin = new Role(2L, "ADMIN");
//        Set<Role> roles = new HashSet<>();
//        Set<Role> users = new HashSet<>();
//        Set<Role> admins = new HashSet<>();
//
//        // restaurant
//        Restaurant restaurant = new Restaurant();
//
//        users.add(user);
//        admins.add(admin);
//        roles.add(user);
//        roles.add(admin);
//
//        roleRepository.saveAll(roles);
//
//        return (evt) -> LongStream.range(1, 10).forEach(n -> {
//            MyUser u = new MyUser("user" + n, encoder.encode("user" + n), user);
//            userRepository.save(u);
//
//            if (n % 3 == 0) {
//                MyUser a = new MyUser("admin" + n/3, encoder.encode("admin" + (n/3)), admin);
//                userRepository.save(a);
//            }
//        });

}


