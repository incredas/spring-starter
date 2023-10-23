package dev.incredas.spring.starter.persistence;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.beans.Transient;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity implements Serializable {

    @Column(name = "created_at", nullable = false, updatable = false)
    protected ZonedDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    protected String createdBy;

    @Column(name = "modified_by")
    protected String modifiedBy;

    @Column(name = "modified_at")
    protected ZonedDateTime modifiedAt = ZonedDateTime.now();

    @PrePersist
    private void prePersist() {
        createdAt = ZonedDateTime.now();
        createdBy = getCreatorUsername();
    }

    @PreUpdate
    private void preUpdate() {
        modifiedAt = ZonedDateTime.now();
        modifiedBy = getCreatorUsername();
    }

    @Transient
    protected abstract String getCreatorUsername();

}
