package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rate extends BaseEntity{
    private int rating;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}
