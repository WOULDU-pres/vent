package me.hwjoo.backend.common.repository;

import me.hwjoo.backend.common.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
