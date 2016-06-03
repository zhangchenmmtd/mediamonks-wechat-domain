package com.mediamonks.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class Domain {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(name = "archived",nullable = false)
    private boolean archived = false;

    @Column(name = "guid",unique = true)
    private String guid = UUID.randomUUID().toString();

    @Column(name = "createdat",columnDefinition = "timestamp default now()")
    private Date createdAt = new Date();

    public Domain() {
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer id() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void archive() {
        this.archived = true;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}