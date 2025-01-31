package me.hwjoo.backend.common.repository;

import me.hwjoo.backend.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {
}
