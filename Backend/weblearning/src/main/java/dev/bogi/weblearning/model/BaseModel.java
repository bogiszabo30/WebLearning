package dev.bogi.weblearning.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(nullable = false, updatable = false)
    public ZonedDateTime created;

    @Column
    public ZonedDateTime updated;

    @PrePersist
    protected void onCreate() {
        created = ZonedDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = ZonedDateTime.now();
    }

}
