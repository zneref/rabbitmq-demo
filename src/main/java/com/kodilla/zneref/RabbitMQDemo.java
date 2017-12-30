/*
 * Simple implementation using RabbitMQ messaging broker
 * @author Marian Ferenz
 * */
package com.kodilla.zneref;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class RabbitMQDemo {
    public static void main(String[] args) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Queue.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queue.NAME, false, false, false, null);
        Sender sender = new RMQSender(channel);
        new RMQReceiver(sender, channel);
        for (int i = 0; i < 10; i++) {
            sender.sendMessage("message no: " + Integer.toString(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        channel.close();
        connection.close();
    }

}
