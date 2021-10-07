package org.mymafia.mafiaGame.vo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
    private Long memberNumber;

    private String memberId;

    private String memberPw;

    private String name;

    private String email;

    private Role role;

}
