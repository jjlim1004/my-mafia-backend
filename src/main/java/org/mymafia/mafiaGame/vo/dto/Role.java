package org.mymafia.mafiaGame.vo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("guest","guest"),
    ADMIN("admin","admin");

    private final String key;
    private final String title;
}