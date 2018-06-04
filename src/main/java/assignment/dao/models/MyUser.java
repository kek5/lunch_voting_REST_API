package assignment.dao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.Identifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kek5 on 4/30/18.
 */
@Entity
@Table(name = "users")
@Data
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;


    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "vote_id")
    private Vote vote;


    public MyUser(){super();}
    public MyUser(Long id){this(); this.setId(id);}
    public MyUser(String username, String password, Set roles ){
        super();
        this.setUsername(username);
        this.setPassword(password);
        this.setRoles(roles);
    }
    public MyUser(String username, String password, Role role ){
        super();
        this.setUsername(username);
        this.setPassword(password);
        this.roles = new HashSet<>();
        roles.add(role);
        this.setRoles(roles);
    }
    public MyUser(MyUser myUser) {
        this.setId(myUser.getId());
        this.setUsername(myUser.getUsername());
        this.setPassword(myUser.getPassword());
        this.setRoles(myUser.getRoles());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    @PrePersist
    public void onCreate() {
        Vote vote = new Vote();
        vote.setUser(this);
        this.setVote(vote);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
