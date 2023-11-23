package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Product extends BaseEntity{
    private String title;
    private String productCode;
    private String warrantyPeriod;
    private Double cost;
    private Double promotional;
    private boolean status;
    @OneToMany
    @JoinTable(name = "product_photo", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "photo_id"))
    private List<Photo> photos;
    private String video;
    @OneToMany
    @JoinTable(name = "product_gift", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "gift_id"))
    private List<Gift> gifts;
    @OneToMany
    @JoinTable(name = "product_specification", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "specification_id"))
    private List<Specification> specifications;
    @OneToOne
    private Post post;
    @ManyToMany(mappedBy = "products")
    private List<Category> categories;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<Rate> rates;
    @OneToMany(mappedBy = "product")
    private List<BranchProduct> branchProducts;
    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}
