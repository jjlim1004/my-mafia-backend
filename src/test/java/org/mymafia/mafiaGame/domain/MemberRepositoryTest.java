package org.mymafia.mafiaGame.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mymafia.mafiaGame.vo.entity.Member;
import org.mymafia.mafiaGame.vo.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

//    @After
//    public void cleanup(){
//        memberRepository.deleteAll();
//    }

    @Test
    public void memberInsert(){
        String testId = "jjlim1004";
        String testPw = "jjlim1004";
        String testName = "jjlim";

        memberRepository.save(Member.builder()
                        .memberId(testId)
                        .memberPw(testPw)
                        .name(testName).build());

        List<Member> memberList = memberRepository.findAll();

        Member member  = memberList.get(0);
        assertThat(member.getMemberId()).isEqualTo(testId);
        assertThat(member.getMemberPw()).isEqualTo(testPw);
        assertThat(member.getName()).isEqualTo(testName);
    }
}