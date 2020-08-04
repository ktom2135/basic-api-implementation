package com.thoughtworks.rslist.domain;

public class RsEvent {

    private String eventName;
    private String keyWord;

    public RsEvent(){
    }

    public RsEvent(String eventName, String  keyWord){
        this.eventName = eventName;
        this.keyWord = keyWord;
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
