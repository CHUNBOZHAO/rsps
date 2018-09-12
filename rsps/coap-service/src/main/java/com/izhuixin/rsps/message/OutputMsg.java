package com.izhuixin.rsps.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputMsg {
    String OUTPUT_BASESTATION = "output_basestation";
    String OUTPUT_BOXDATA = "output_boxdata";

    @Output("output_basestation")
    MessageChannel output_basestation();

    @Output("output_boxdata")
    MessageChannel output_boxdata();
}
