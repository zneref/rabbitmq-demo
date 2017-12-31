package com.kodilla.zneref.rabbitmq.receiver;

import com.kodilla.zneref.rabbitmq.labels.Queue;
import com.kodilla.zneref.rabbitmq.sender.Sender;
import com.rabbitmq.client.*;

import java.io.IOException;

public class RMQReceiver implements Receiver {
    private Channel channel;

    public RMQReceiver(Sender sender, Channel channel) {
        this.channel = channel;
        sender.setReceiver(this);

    }

    public void receive(String message) {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        try {
            channel.basicConsume(Queue.NAME, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
