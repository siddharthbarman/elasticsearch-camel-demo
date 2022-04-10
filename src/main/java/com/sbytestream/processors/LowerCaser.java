package com.sbytestream.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LowerCaser implements Processor {
    public void process(Exchange exchange) throws Exception {
        String originalFileContent = (String) exchange.getIn().getBody(String.class);
        String upperCaseFileContent = originalFileContent.toLowerCase();
        exchange.getIn().setBody(upperCaseFileContent);
    }
}
