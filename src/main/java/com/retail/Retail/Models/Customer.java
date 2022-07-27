package com.retail.Retail.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String avatar;
    private String phone;
    private String phone2;
    private String aboutUser;
    private boolean active;
    private Date dateOfRegistration;

    @ElementCollection(targetClass = Gender.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_gender", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    private Set<Gender> genders = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DeliveryAdress> deliveryAdressList;

    @OneToOne(cascade = CascadeType.ALL)
    private DeliveryAdress currentDeliveryAdress;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private WishList wishList;


    @OneToOne
    @JsonBackReference
    private OrderSummary orderSummary;

    @OneToMany
    @JsonBackReference
    private List<OrderSummary> orderSummaryListHistory;

    private String notification;

    private boolean isDeleted;

    private boolean accountNonLocked;

    public Customer(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
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
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
