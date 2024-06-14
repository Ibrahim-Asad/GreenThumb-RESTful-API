package com.greenthumb.model.entity;

import com.greenthumb.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "resource_exchanges")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resourceType;
    private String description;
    private int quantity;

    @ManyToMany
    @JoinTable(
            name = "resource_exchange_user",
            joinColumns = @JoinColumn(name = "resource_exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;

    @ManyToOne
    @JoinColumn(name = "local_partner_id", nullable = false)
    private LocalPartner localPartner;

}
