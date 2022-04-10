# Elastic search with Apache Camel demo
## Overview
Sample code for reading mocked emails files, converting them to POJO, spam detecting, converting to JSON and finally storing in Elastic Search.

# Details
The code demonstrates how to use Camel components, wire them using Spring XML context file. 

The flow of data is as follows:
```
Read email --> Parse email and convert to POJO --> Detect spam --> Store spam in a folder --> Legit emails are sent to Elastic Search
```

## Reading emails
Input emails are stored in the 'data/input' folder in the current directory. The format of the email file is mocked, for the purpose of the demo, the file structure should be as shown below:
```
from:siddharth@email.com
to:neel@email.com
subject:hello world
content:this email is a test email for telling Hello World
```
Emails are read as plain files using the file:// protocol. Two sample email files are available in the data directory, these can be copied into the input folder for testing.

## Processing emails
Email files are sent to the EmailParser class for converting the content to a POJO. The POJO is then sent to the SpamIdentifier. The SpamIdentifier class checks if the sender is one of the email addresses in its list of blocked senders. It adds a new property named "spam" to "true".

## Output
The output of spam processing is sent to a check block where spam emails are stored in a folder named "spam" and other emails are sent to Elastic Search in an index named "mails" using the ElasticsearchComponent. You can query these items using the following request:
```
GET mails/_search
{
  "query": {
    "match_all": {}
  }
}
```

## Wiring everything together
Components and wiring are defined in the routes.xml file.

## Elastic search configuration
Make sure you have set the Elastic Search configuration to match your setup. This is defined in the routes.xml file as well.

## And finally ...
If this code works then it was written by me else I have no idea how this code in my github repo!