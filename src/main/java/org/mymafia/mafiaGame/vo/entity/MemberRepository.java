package org.mymafia.mafiaGame.vo.entity;

import org.mymafia.mafiaGame.vo.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select member from Member member where member.memberID =?1")
    Member findByMemberId(MemberDTO memberDTO);
}
