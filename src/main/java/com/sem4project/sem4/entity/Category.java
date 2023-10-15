package com.sem4project.sem4.entity;

import jakarta.persistence.*;
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
public class Category extends BaseEntity{
    private String name;
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> categories;
    @ManyToOne
    private Category parentCategory;
    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Product> products;
}
