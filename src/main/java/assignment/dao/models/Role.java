package assignment.dao.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * Created by kek5 on 4/30/18.
 */
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy="roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<MyUser> myUsers;

    public Role() {}
    public Role(Long id) {this(); this.setId(id);}
    public Role(String name) {
        this();
        this.setName(name);
    }
    public Role(Long id, String name) {
        this(id);
        this.setName(name);
    }

}
