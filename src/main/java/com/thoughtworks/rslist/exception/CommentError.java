package com.thoughtworks.rslist.exception;

import lombok.Data;

@Data
public class CommentError {
    public CommentError(String error) {
        this.error = error;
    }

    private String error;
}
