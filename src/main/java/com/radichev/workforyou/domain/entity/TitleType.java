package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "title_types")
public class TitleType extends BaseEntity {
    private String titleType;

    public TitleType() {
    }

    public TitleType(String titleType) {
        this.titleType = titleType;
    }

    @Column(nullable = false)
    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
}
