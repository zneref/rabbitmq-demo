package com.kodilla.zneref;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RMQSender implements Sender {
    private String message;
    private List<Receiver> receivers = new ArrayList<Receiver>();
    private Channel channel;

    public RMQSender(Channel channel) {
        this.channel = channel;
    }

    public void addReceiver(Receiver receiver) {
        receivers.add(receiver);
    }

    public void notifyReceivers() {
        for (Receiver receiver : receivers) {
            receiver.receive(message);
        }
    }

    public void sendMessage(String message) {
        this.message = message;
        try {
            channel.basicPublish("", Queue.NAME, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [x] Sent '" + message + "'");
        notifyReceivers();
    }
}
