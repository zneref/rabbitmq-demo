package com.kodilla.zneref;

public interface Sender {
    void addReceiver(Receiver receiver);

    void notifyReceivers();

    void sendMessage(String message);
}
