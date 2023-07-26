package com.hit.model;

import java.io.Serializable;

/**
 * Represents a payload object that encapsulates data and a route for communication.
 * The payload is serializable to allow for network transmission.
 *
 * @param <TDATA> the type of data contained in the payload
 */

public class Payload<TDATA> implements Serializable {

    /**
     * The route for communication.
     */
    private String route;
    /**
     * The data contained in the payload.
     */
    private TDATA data;

    /**
     * Constructs a Payload object with the specified route and no data.
     *
     * @param route the route for communication
     */
    public Payload(String route) {
        this.route = route;
        this.data = null;
    }

    /**
     * Constructs a Payload object with the specified route and data.
     *
     * @param route   the route for communication
     * @param product the data contained in the payload
     */
    public Payload(String route, TDATA product) {
        this.route = route;
        this.data = product;
    }

    /**
     * Retrieves the data contained in the payload.
     *
     * @return the data contained in the payload
     */
    public TDATA getdata() {
        return data;
    }

    /**
     * Retrieves the route for communication.
     *
     * @return the route for communication
     */
    public String getRoute() {
        return route;
    }


}



