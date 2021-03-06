package com.izhuixin.rsps.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputMsg {

    String OUTPUT_ORDER = "output_order";

    @Output("output_order")
    MessageChannel output_order();
}

