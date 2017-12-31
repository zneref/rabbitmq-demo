package com.kodilla.zneref.rabbitmq.sender;

import com.kodilla.zneref.rabbitmq.labels.Queue;
import com.kodilla.zneref.rabbitmq.receiver.Receiver;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class RMQSender implements Sender {
    private String message;
    private Receiver receiver;
    private Channel channel;

    public RMQSender(Channel channel) {
        this.channel = channel;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void notifyReceiver() {
        receiver.receive(message);
    }

    public void sendMessage(String message) {
        this.message = message;
        try {
            channel.basicPublish("", Queue.NAME, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [x] Sent '" + message + "'");
        notifyReceiver();
    }
}
