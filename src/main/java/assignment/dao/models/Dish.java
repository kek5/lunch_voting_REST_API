package assignment.dao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by kek5 on 5/5/18.
 */
@Entity
@Data
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Dish name must not be empty")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Dish price must not be empty")
    private int price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public Dish(){}
}
