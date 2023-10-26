package com.sem4project.sem4.dto.dtomodel;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BaseDto {
    private Long id;
    private boolean disable;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
