package com.greenthumb.model.entity;

import com.greenthumb.security.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "knowledge_shares")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters and Setters
}
