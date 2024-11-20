package com.edmondsinc.wishlist.config;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "guid", nullable = false, unique = true, updatable = false)
    private UUID guid;

    protected AbstractEntity(){
        this.guid = UUID.randomUUID();
    }

    public AbstractEntity(Long id, LocalDateTime createdAt, UUID guid) {
        this.id = id;
        this.createdAt = createdAt;
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }
}
