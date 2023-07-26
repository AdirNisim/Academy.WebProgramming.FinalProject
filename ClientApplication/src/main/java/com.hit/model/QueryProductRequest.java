package com.hit.model;

import java.io.Serializable;

public class QueryProductRequest {

    /**
     * The title used to query products.
     */
    private String title;

    /**
     * Sets the title for the product query.
     *
     * @param title the title used to query products
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the title used to query products.
     * If the title is null, an empty string is returned.
     *
     * @return the title used to query products
     */
    public String getTitle() {
        if (title == null) {
            return "";
        }

        return title;
    }
}

