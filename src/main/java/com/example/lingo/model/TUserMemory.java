package com.example.lingo.model;

import java.io.Serializable;

public class TUserMemory implements Serializable {
    private Long userId;

    private Long tableId;

    private Long wordId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public TUserMemory() {
    }

    public TUserMemory(Long userId, Long tableId, Long wordId) {
        this.userId = userId;
        this.tableId = tableId;
        this.wordId = wordId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", tableId=").append(tableId);
        sb.append(", wordId=").append(wordId);
        sb.append("]");
        return sb.toString();
    }
}