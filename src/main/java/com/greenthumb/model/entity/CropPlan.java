package com.greenthumb.model.entity;

import com.greenthumb.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "crop_plans")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CropPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private Date plantingDate;
    private Date harvestDate;

    @ManyToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters and Setters
}
