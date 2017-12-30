package com.kodilla.zneref;

public interface Sender {
    void setReceiver(Receiver receiver);

    void notifyReceiver();

    void sendMessage(String message);
}
