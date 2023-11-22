package com.example.lingo.service;

import com.example.lingo.common.Result;
import com.example.lingo.model.TWord;

public interface WordService {
    Result getTenWords(Long tableId, Long wordId);
    void wordFinish(Long tableId, Long wordId);
    void insertWord(TWord tWord);
}
