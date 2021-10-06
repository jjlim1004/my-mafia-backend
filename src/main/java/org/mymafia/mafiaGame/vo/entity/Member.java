package org.mymafia.mafiaGame.vo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "member")
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNumber;


    private String memberId;

    private String memberPw;

    private String name;

    @Builder
    public Member(Long memberNumber, String memberId, String memberPw, String name) {
        this.memberNumber = memberNumber;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.name = name;
    }
}
