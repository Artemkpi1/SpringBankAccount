package com.example.bankAccountProject.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<CreditCard> cards;
    @OneToMany(mappedBy = "owner")
    private List<Payment> payments;

    @OneToMany(mappedBy = "owner")
    private List<CardRequest> unblockRequests;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String firstName, String lastName, Role role, State state, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.state = state;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }


    private State state = State.ACTIVE;
    public enum State {
        ACTIVE,
        BLOCKED
    }

    @Override
    public String getUsername() {
        return email;
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
