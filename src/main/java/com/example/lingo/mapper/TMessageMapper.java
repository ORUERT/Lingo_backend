package com.example.lingo.mapper;

import com.example.lingo.model.TMessage;

import java.util.List;

public interface TMessageMapper {
    int insert(TMessage message);
    List<TMessage> selectByUserId(Long userId);
}
