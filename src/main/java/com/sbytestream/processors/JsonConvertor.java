package com.sbytestream.processors;

import com.google.gson.Gson;
import com.sbytestream.MailData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JsonConvertor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        MailData mail = (MailData) exchange.getIn().getBody();
        String json = gson.toJson(mail);
        exchange.getIn().setBody(json);
    }
}
