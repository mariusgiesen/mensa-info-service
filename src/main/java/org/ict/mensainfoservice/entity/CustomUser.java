package org.ict.mensainfoservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "custom_user")
public class CustomUser implements UserDetails {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Authority.class)
    private Set<Authority> grantedAuthorities;
    private String password;
    private String username;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Meal.class)
    private List<Meal> favoriteMeals;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public CustomUser(){}

    public CustomUser(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.grantedAuthorities = new HashSet<>(Arrays.asList(new Authority("ROLE_USER")));
        this.favoriteMeals = new ArrayList<>();
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    public Set<Authority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public String getEmail() {
        return email;
    }

    public List<Meal> getFavoriteMeals() {
        return favoriteMeals;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
                "grantedAuthorities=" + grantedAuthorities +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", favoriteMeals=" + favoriteMeals +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
