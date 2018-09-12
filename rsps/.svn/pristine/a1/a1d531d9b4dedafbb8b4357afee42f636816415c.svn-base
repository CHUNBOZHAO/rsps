package com.izhuixin.rsps.resource;

import com.izhuixin.rsps.util.Util;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.CHANGED;

public class LocResource extends CoapResource {

    public LocResource() {

        // set resource identifier
        super("Loc");

        // set display name
        getAttributes().setTitle("Loc Resource");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        byte[] req = exchange.getRequestPayload();
        System.err.println("Receive message : " + Util.bytesToHexString(req," "));
        exchange.respond(CHANGED,String.valueOf(0));
    }
}