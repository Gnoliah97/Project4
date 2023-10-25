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
public class Invoice extends BaseEntity{
    private String fullName;
    private String email;
    private String phone;
    @ManyToOne
    private User user;
    @OneToMany
    @JoinTable(name = "invoice_product", joinColumns = @JoinColumn(name = "invoice_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @ManyToOne
    private Province province;
    @ManyToOne
    private District district;
    private String address;
    private String note;
    @OneToOne
    private Transactional transactional;
}
