package com.izhuixin.rsps.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputMsg {
    String INPUT_BASESTATION = "input_basestation";

    String INPUT_OPERLOCATION = "input_operlocation";

    @Input("input_basestation")
    SubscribableChannel input_basestation();

    @Input("input_operlocation")
    SubscribableChannel input_operlocation();
}
