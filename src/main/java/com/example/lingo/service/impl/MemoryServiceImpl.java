package com.example.lingo.service.impl;

import com.example.lingo.common.Result;
import com.example.lingo.mapper.TMemoryMapper;
import com.example.lingo.mapper.TUserMemoryMapper;
import com.example.lingo.model.TMemory;
import com.example.lingo.model.TUserMemory;
import com.example.lingo.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoryServiceImpl implements MemoryService {
    @Autowired
    private TMemoryMapper tMemoryMapper;
    @Autowired
    private TUserMemoryMapper tUserMemoryMapper;
    @Override
    public Result insertMemory(Long userId, Long tableId, Long wordId) {
        for(int i = 0 ; i < 10 ; i ++){
            TMemory tMemory = new TMemory();
            tMemory.setUserId(userId);
            tMemory.setWordId(wordId+i);
            tMemory.setMemoryCount(1);
            tMemory.setMemoryStatus(1);
            tMemoryMapper.insert(tMemory);
        }
        tUserMemoryMapper.insert(new TUserMemory(userId,tableId,wordId+9));
        return Result.success("success");
    }
}
