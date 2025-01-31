package me.hwjoo.backend.common.repository;

import me.hwjoo.backend.common.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
