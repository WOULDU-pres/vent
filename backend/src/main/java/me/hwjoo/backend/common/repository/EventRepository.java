package me.hwjoo.backend.common.repository;

import java.util.List;
import me.hwjoo.backend.common.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE (:type IS NULL OR e.type = :type)")
    List<Event> findAllByType(@Param("type") String type);
}
