package com.thoughtworks.rslist.domain;

public class RsEvent {
    private String eventName;
    private String keyWord;

    public RsEvent(String eventName, String keyWord) {
        this.keyWord = keyWord;
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
