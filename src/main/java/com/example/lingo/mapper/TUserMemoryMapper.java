package com.example.lingo.mapper;

import com.example.lingo.model.TUserMemory;
import java.util.List;

public interface TUserMemoryMapper {

    int insert(TUserMemory record);

    List<Long> selectByUserId(Long userId);

    List<TUserMemory> selectAll();

    int updateByPrimaryKey(TUserMemory record);
}