package org.mymafia.mafiaGame.service;


import org.mymafia.mafiaGame.vo.entity.Member;

public interface MemberService {

    public int Join(Member member);

    public int login(Member member);

    public int logout();

    public int deleteMember(Long memberNumber);

}
