package com.example.lingo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private boolean self;
    private String content;
}
