package me.hwjoo.backend.common.repository;

import me.hwjoo.backend.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
