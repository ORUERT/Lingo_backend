package com.example.lingo.service;

import com.example.lingo.common.Result;

public interface MemoryService {
    Result insertMemory(Long userId, Long tableId, Long wordId);
}
