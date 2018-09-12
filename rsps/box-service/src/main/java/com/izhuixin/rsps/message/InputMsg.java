package com.izhuixin.rsps.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputMsg {
    String INPUT_BOXDATA = "input_boxdata";

    @Input("input_boxdata")
    SubscribableChannel input_boxdata();

}
