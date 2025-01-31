package me.hwjoo.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @Column(nullable = false)
    private UUID userUuid;

    @Column(nullable = false)
    private ZonedDateTime participationTime = ZonedDateTime.now();

    @Column(columnDefinition = "jsonb")
    private String resultData;
}
