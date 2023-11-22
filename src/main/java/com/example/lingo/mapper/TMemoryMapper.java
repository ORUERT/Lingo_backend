package com.example.lingo.mapper;

import com.example.lingo.model.TMemory;
import java.util.List;

public interface TMemoryMapper {

    int insert(TMemory record);

    List<TMemory> selectAll();

    int updateByPrimaryKey(TMemory record);
}