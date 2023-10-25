package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Transactional extends BaseEntity{
    private String bankCode;
    private String bankTransNo;
    private String cardType;
    private String payInfo;
    private String transNo;
    private String amount;
    private String secureHash;
    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "transactional")
    private Invoice invoice;
}
