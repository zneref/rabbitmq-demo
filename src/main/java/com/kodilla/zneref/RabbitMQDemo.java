/*
* This app demonstrates simple implementation of RabbitMQ messaging broker
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
        new RMQReceiver(sender, channel);
        sender.sendMessage("Hello world!");

        channel.close();
        connection.close();
    }

}
