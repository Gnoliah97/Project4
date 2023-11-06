package com.sem4project.sem4.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private boolean disable;
    @Column(nullable = false)
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    @PrePersist
    protected void prePersist(){
        if(this.createdAt == null) {
            this.createdAt = Instant.now();
        }
        if(this.createdBy == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && !authentication.getPrincipal().equals("anonymousUser")){
                this.createdBy = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId().toString();
            }
        }
        this.updatedAt = Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getPrincipal().equals("anonymousUser")){
            this.createdBy = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId().toString();
        }
    }
    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = Instant.now();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.getPrincipal().equals("anonymousUser")){
            this.createdBy = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId().toString();
        }
    }
}
