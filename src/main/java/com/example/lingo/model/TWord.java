package com.example.lingo.model;

import java.io.Serializable;

public class TWord implements Serializable {
    private Long wordId;

    private Long tableId;

    private String word;

    private String definition;

    private String phonetic;

    private String phrase;

    private String definitionImage;

    private String phraseImage;

    private String phraseExplain;

    private static final long serialVersionUID = 1L;

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition == null ? null : definition.trim();
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic == null ? null : phonetic.trim();
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase == null ? null : phrase.trim();
    }

    public String getDefinitionImage() {
        return definitionImage;
    }

    public void setDefinitionImage(String definitionImage) {
        this.definitionImage = definitionImage == null ? null : definitionImage.trim();
    }

    public String getPhraseImage() {
        return phraseImage;
    }

    public void setPhraseImage(String phraseImage) {
        this.phraseImage = phraseImage == null ? null : phraseImage.trim();
    }

    public String getPhraseExplain() {
        return phraseExplain;
    }

    public void setPhraseExplain(String phraseExplain) {
        this.phraseExplain = phraseExplain == null ? null : phraseExplain.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wordId=").append(wordId);
        sb.append(", tableId=").append(tableId);
        sb.append(", word=").append(word);
        sb.append(", definition=").append(definition);
        sb.append(", phonetic=").append(phonetic);
        sb.append(", phrase=").append(phrase);
        sb.append(", definitionImage=").append(definitionImage);
        sb.append(", phraseImage=").append(phraseImage);
        sb.append(", phraseExplain=").append(phraseExplain);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}