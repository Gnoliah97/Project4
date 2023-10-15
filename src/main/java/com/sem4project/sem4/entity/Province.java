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
public class Province extends BaseEntity{
    private String name;
    @OneToMany(mappedBy = "province")
    private List<District> districts;
    @OneToMany(mappedBy = "province")
    private List<Branch> branches;
}
