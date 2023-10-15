package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Gift extends BaseEntity{
    private String name;
}
