package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Branch extends BaseEntity {
    private String name;
    @ManyToOne
    private Province province;
    @ManyToOne
    private District district;
    @OneToMany(mappedBy = "branch")
    private List<BranchProduct> branchProducts;
}
