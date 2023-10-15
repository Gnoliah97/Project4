package com.sem4project.sem4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Post extends BaseEntity{
    private String title;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
