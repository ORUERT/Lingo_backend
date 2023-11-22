package com.example.lingo.service;

import com.example.lingo.common.Result;
import com.example.lingo.dto.MessageDto;

public interface MessageService {
    Result deal(MessageDto messageDto,String userId);
//    int
}
