package com.example.lingo.mapper;

import com.example.lingo.model.TWord;
import java.util.List;

public interface TWordMapper {
    int deleteByPrimaryKey(Long wordId);

    int insert(TWord record);

    TWord selectByPrimaryKey(Long wordId);

    List<TWord> selectTenByPrimaryId(Long tableId,Long wordId);

    List<TWord> selectAll();

    int updateByPrimaryKey(TWord record);
}