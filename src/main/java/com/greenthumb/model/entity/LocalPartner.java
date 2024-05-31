package com.greenthumb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "local_partners")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String service;
    private String email;

    @OneToMany(mappedBy = "localPartner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResourceExchange> resourceExchanges;

}
