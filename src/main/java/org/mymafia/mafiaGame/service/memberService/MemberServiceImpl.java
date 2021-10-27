package org.mymafia.mafiaGame.service.memberService;

import org.mymafia.mafiaGame.vo.dto.MemberDTO;
import org.mymafia.mafiaGame.vo.dto.Role;
import org.mymafia.mafiaGame.vo.entity.Member;
import org.mymafia.mafiaGame.vo.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;

    public Member memberIsExist(MemberDTO memberDto){
        Member member  = memberRepository.findByMemberId(memberDto.getMemberId());
        if(member != null){
            return member;
        }else{
            return null;
        }
    }

    @Override
    public MemberDTO Join(MemberDTO memberDto) {
        memberDto.setMemberRole(Role.GUEST);
        Member existMember = memberIsExist(memberDto);
        if(existMember == null){
            Member member = Member.builder()
                    .memberId(memberDto.getMemberId())
                    .memberPw(memberDto.getMemberPw())
                    .memberRole(memberDto.getMemberRole())
                    .memberEmail(memberDto.getEmail())
                    .memberName(memberDto.getMemberName())
                    .build();
            memberRepository.save(member);
            return memberDto;
        }
        return null;
    }

    @Override
    public String login(MemberDTO memberDto) {
        //리팩토링 시 Optional 로 바꿔보기
        Member existMember = memberIsExist(memberDto);
        if(existMember != null){
            boolean isMember = memberDto.getMemberPw().equals(existMember.getMemberPw());
            if(isMember){
                return "success";
            }else{
                return "pw fail";
            }
        }
        return "id fail";
    }

    @Override
    public int logout() {

        return 0;
    }

    @Override
    public int deleteMember(Long memberNumber) {

        return 0;
    }
}
