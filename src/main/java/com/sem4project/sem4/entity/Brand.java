package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Brand extends BaseEntity{
    private String name;
    @OneToMany
    private List<Photo> photo;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
