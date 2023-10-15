package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
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
public class FilterOption extends BaseEntity{
    private String name;
    private String rule;
    @OneToOne
    private FilterProperty filterProperty;
}
