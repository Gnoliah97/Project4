package com.sem4project.sem4.dto.dtomodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionalDto{
    private Long id;
    private String bankCode;
    private String bankTransNo;
    private String cardType;
    private String payInfo;
    private String transNo;
    private String amount;
    private String secureHash;
    private UserDto user;
    private InvoiceDto invoice;
}
