package org.mymafia.mafiaGame.vo.entity;

import org.mymafia.mafiaGame.vo.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    //List<Member> findByMemberId(String memberId);
    Member findByMemberId(String memberId);



}
