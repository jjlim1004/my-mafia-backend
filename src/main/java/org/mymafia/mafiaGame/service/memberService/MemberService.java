package org.mymafia.mafiaGame.service.memberService;


import org.mymafia.mafiaGame.vo.dto.MemberDTO;

public interface MemberService {

     String Join(MemberDTO memberDto);

     String login(MemberDTO member);

     int logout();

     int deleteMember(Long memberNumber);


}
