package com.example.lingo.model;

import java.io.Serializable;

public class TMemory implements Serializable {
    private Long userId;

    private Long wordId;

    private Integer memoryCount;

    private Integer memoryStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Integer getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(Integer memoryCount) {
        this.memoryCount = memoryCount;
    }

    public Integer getMemoryStatus() {
        return memoryStatus;
    }

    public void setMemoryStatus(Integer memoryStatus) {
        this.memoryStatus = memoryStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", wordId=").append(wordId);
        sb.append(", memoryCount=").append(memoryCount);
        sb.append(", memoryStatus=").append(memoryStatus);
        sb.append("]");
        return sb.toString();
    }
}