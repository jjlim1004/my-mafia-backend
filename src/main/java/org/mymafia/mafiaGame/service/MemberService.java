package org.mymafia.mafiaGame.service;


import org.mymafia.mafiaGame.vo.dto.MemberDTO;
import org.mymafia.mafiaGame.vo.entity.Member;

public interface MemberService {

     int Join(MemberDTO memberDto);

     int login(Member member);

     int logout();

     int deleteMember(Long memberNumber);

}
