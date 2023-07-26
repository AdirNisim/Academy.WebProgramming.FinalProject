package com.hit.server.models;

import java.io.Serializable;

public class Payload<TDATA> implements Serializable {

    private String route;
    private TDATA data;

    public Payload() {
    }

    public TDATA getdata() {
        return data;
    }

    public String getRoute() {
        return route;
    }

    public Payload(String route) {
        this.route = route;
        this.data = null;
    }

    public Payload(String route, TDATA product) {
        this.route = route;
        this.data = product;
    }
}
