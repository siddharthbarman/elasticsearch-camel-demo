package com.sbytestream.processors;

import com.sbytestream.MailData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.KeyValueHolder;

public class EmailParser implements Processor {
    public void process(Exchange exchange) throws Exception {
        String originalFileContent = (String) exchange.getIn().getBody(String.class);
        String[] lines = originalFileContent.split("\r\n");
        MailData mail = new MailData();

        for (String line : lines) {
            KeyValueHolder<String, String> kv = getNameValue(line);
            if (kv.getKey().equalsIgnoreCase(MAIL_CONTENT))
                mail.setContent(kv.getValue());
            else if (kv.getKey().equalsIgnoreCase(MAIL_FROM))
                mail.setFrom(kv.getValue());
            else if (kv.getKey().equalsIgnoreCase(MAIL_SUBJECT))
                mail.setSubject(kv.getValue());
            else if (kv.getKey().equalsIgnoreCase(MAIL_TO))
                mail.setTo(kv.getValue());
        }

        exchange.getIn().setBody(mail);
    }

    private KeyValueHolder<String, String> getNameValue(String line) {
        String[] parts = line.split(":");
        return new KeyValueHolder<>(parts[0], parts[1]);
    }

    private final String MAIL_FROM = "from";
    private final String MAIL_TO = "to";
    private final String MAIL_SUBJECT = "subject";
    private final String MAIL_CONTENT = "content";
}
