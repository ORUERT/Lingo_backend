package com.example.lingo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {
    private String nickname;
    private List<Long> glossary;
}
