package com.thoughtworks.rslist.dto;

public class RsEventDto {
    public RsEventDto() {
    }

    public RsEventDto(String eventName, String keyword) {
        this.eventName = eventName;
        this.keyword = keyword;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String eventName;
    private String keyword;
}
