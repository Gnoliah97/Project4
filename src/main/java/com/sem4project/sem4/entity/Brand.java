package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Brand extends BaseEntity{
    private String name;
    @OneToMany
    private List<Image> image;
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
