package com.sem4project.sem4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Table(name = "ImageData")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Image extends BaseEntity{

    private String name;
    private String type;
    @Column(name = "imgdata", length = 1000)
    private String imgData;
    private byte[] data;
}
