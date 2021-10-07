package org.mymafia.mafiaGame.vo.entity;

import lombok.*;
import org.mymafia.mafiaGame.vo.dto.Role;

import javax.persistence.*;

@Entity(name = "member")
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNumber;

    @Column(nullable=false)
    private String memberId;

    @Column(nullable=false)
    private String memberPw;

    @Column(nullable=false)
    private String name;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    public String getRoleKey(){
        return this.role.getKey();
    }

    @Builder
    public Member(Long memberNumber, String memberId, String memberPw, String name, Role role) {
        this.memberNumber = memberNumber;
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.name = name;
        this.role = role;
    }
}
