package com.izhuixin.rsps.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputMsg {
    String INPUT_ORDER = "input_order";
    String INPUT_OPERLOCATION = "input_operlocation";

    @Input("input_order")
    SubscribableChannel input_order();

    @Input("input_operlocation")
    SubscribableChannel input_operlocation();
}
