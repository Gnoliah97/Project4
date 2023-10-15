package com.sem4project.sem4.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class District extends BaseEntity{
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Province province;
    @OneToMany(mappedBy = "district")
    private List<Branch> branches;
}
