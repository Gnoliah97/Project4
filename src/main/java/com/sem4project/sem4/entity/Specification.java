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
public class Specification extends BaseEntity{
    private String name;
    private String content;
}
