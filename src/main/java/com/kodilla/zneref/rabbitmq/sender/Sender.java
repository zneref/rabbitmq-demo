package com.kodilla.zneref.rabbitmq.sender;

import com.kodilla.zneref.rabbitmq.receiver.Receiver;

public interface Sender {
    void setReceiver(Receiver receiver);

    void notifyReceiver();

    void sendMessage(String message);
}
