package dev.incredas.spring.starter.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.beans.Transient;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * The AuditableEntity class is an abstract class that represents an entity with audit fields.
 *
 * <p>
 * The audit fields include createdAt, createdBy, modifiedBy, and modifiedAt.
 * These fields are automatically populated and updated through the @PrePersist and @PreUpdate annotations.
 * </p>
 *
 * <p>
 * This class is meant to be extended by other entity classes, such as ENTITY, in order to inherit
 * the audit fields from AuditableEntity.
 * </p>
 *
 * <p>
 * Note that the getCreatorUsername method is abstract and must be implemented by the subclasses in
 * order to provide the username of the creator user.
 * </p>
 */
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
