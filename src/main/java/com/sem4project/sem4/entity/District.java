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
public class District extends BaseEntity{
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Province province;
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<Branch> branches;
}
