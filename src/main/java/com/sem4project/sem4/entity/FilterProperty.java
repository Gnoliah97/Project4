package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FilterProperty extends BaseEntity{
    private String name;
    @ManyToOne
    private Category category;
    @OneToOne(mappedBy = "filterProperty")
    private FilterOption filterOption;
}
