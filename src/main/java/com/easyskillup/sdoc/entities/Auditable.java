package com.easyskillup.sdoc.entities;

import com.easyskillup.sdoc.domain.RequestContext;
import com.easyskillup.sdoc.exceptions.ApiException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

/**
 * @author Ashish
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class Auditable {

    @Id
    @SequenceGenerator(name = "primary_id_seq", sequenceName = "primary_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_id_seq")
    private long id;

    private String refId = new AlternativeJdkIdGenerator().generateId().toString();

    private long updatedBy;

    private long createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void auditablePrePersist(){
        Long userId = 0L;
        if(userId == null){
            throw new ApiException("Cannot persist the entity with a null user id in the RequestContext.");
        }
        setCreatedAt(now());
        setUpdatedAt(now());
        setCreatedBy(userId);
        setUpdatedBy(userId);
    }

    @PreUpdate
    public void auditablePreUpdate(){
        Long userId = 0l;
        if(userId == null){
            throw new ApiException("Cannot update the entity with a null user id in the RequestContext.");
        }
        setUpdatedAt(now());
        setUpdatedBy(userId);
    }
}
