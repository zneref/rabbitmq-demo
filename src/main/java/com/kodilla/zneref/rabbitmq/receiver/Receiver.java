package com.kodilla.zneref.rabbitmq.receiver;

public interface Receiver {
    void receive(String message);
}
