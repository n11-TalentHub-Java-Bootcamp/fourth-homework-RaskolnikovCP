package com.ozansaribal.n11bootcamp.dao;

import com.ozansaribal.n11bootcamp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

}
