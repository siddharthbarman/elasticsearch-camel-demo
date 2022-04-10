package com.sbytestream.processors;

import com.google.gson.Gson;
import com.sbytestream.MailData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.elasticsearch.ElasticsearchComponent;


import java.util.ArrayList;

public class SpamIdentifier implements Processor {
    public void process(Exchange exchange) throws Exception {
        MailData mail = (MailData) exchange.getIn().getBody();
        if (blockedSenders.stream().anyMatch(s -> s.equalsIgnoreCase(mail.getFrom()))) {
            exchange.getIn().setBody(String.format("from:%s\r\n--DELETED--", mail.getFrom()));
            exchange.setProperty("spam", "true");
        }

    }

    public ArrayList<String> getBlockedSenders() {
        return blockedSenders;
    }

    public void setBlockedSenders(ArrayList<String> blockedSenders) {
        this.blockedSenders = blockedSenders;
    }

    private ArrayList<String> blockedSenders;
}
