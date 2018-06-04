package assignment.dao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "restaurants")
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    @JsonIgnore
    private Long id;

    @NotBlank(message = "not banl restaurant name")
    @Column(name = "name", unique = true)
    private String name;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public Restaurant(){
    }

    @PrePersist
    public void onPersist() {
        Menu menu = new Menu();
        menu.setRestaurant(this);
        this.setMenu(menu);
    }

}

