package com.radichev.workforyou.domain.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditableEntity<U> {

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDate lastModifiedDate;

    @Column(name = "created_by")
    @CreatedBy
    private U createdBy;

    @Column(name = "last_modified_by")
    @LastModifiedBy
    private U lastModifiedBy;

    //TODO to implement Delete Logic

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_on")
    private LocalDate deletedOn;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public U getModifiedBy() {
        return lastModifiedBy;
    }

    public void setModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(LocalDate deletedOn) {
        this.deletedOn = deletedOn;
    }
}
