package com.example.lingo.service.impl;

import com.example.lingo.common.Result;
import com.example.lingo.mapper.TWordMapper;
import com.example.lingo.model.TWord;
import com.example.lingo.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private TWordMapper tWordMapper;
    @Override
    public Result getTenWords(Long tableId, Long wordId) {
        return Result.success(tWordMapper.selectTenByPrimaryId(tableId,wordId));
    }


    @Override
    public void wordFinish(Long tableId, Long wordId) {

    }

    @Override
    public void insertWord(TWord tWord) {
        tWordMapper.insert(tWord);
    }
}
