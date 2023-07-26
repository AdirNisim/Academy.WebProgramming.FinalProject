package com.hit.services.models;

import java.io.Serializable;

public class QueryProductRequest {
    private String title;

    public String getTitle() {
        if (title == null) {
            return "";
        }

        return title;
    }
}
