package com.sem4project.sem4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class Comment extends BaseEntity{
    private String content;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
