package com.example.lingo.service.impl;

import cn.hutool.core.date.DateTime;
import com.example.lingo.common.Result;
import com.example.lingo.dto.MessageDto;
import com.example.lingo.mapper.TMessageMapper;
import com.example.lingo.model.TMessage;
import com.example.lingo.service.MessageService;
import com.example.lingo.util.ChatGptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private TMessageMapper messageMapper;
    @Override
    public Result deal(MessageDto messageDto,String userId) {
        ChatGptUtil chatGptUtil = new ChatGptUtil();
        System.out.println(messageDto.getContent());
        String ans = chatGptUtil.getAnswer(messageDto.getContent());
        TMessage message = new TMessage();
        message.setUserId(Long.valueOf(userId));
        message.setContent(messageDto.getContent());
        message.setAnswer(ans);
        LocalDateTime currentDateTime = LocalDateTime.now();

        ZoneId zoneId = ZoneId.systemDefault();
        DateTime currentDate = new DateTime(Date.from(currentDateTime.atZone(zoneId).toInstant()));
        System.out.println(currentDate);
        message.setCreatedAt(currentDate);
        messageMapper.insert(message);
        return Result.success(ans);
    }
}
