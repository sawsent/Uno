package io.codeforall.bootcamp.filhosdamain.uno.server;

import io.codeforall.bootcamp.filhosdamain.uno.messages.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageSender {
    public static void send(Message a, Message b, Message c) {
        send(a);
        send(b);
        send(c);
    }

    public static void send(Message a, Message b) {
        send(a);
        send(b);
    }

    public static void send(Message message) {
        message.send();
    }

    public static void send(List<Message> messages) {
        for (Message message : messages) {
            send(message);
        }
    }


}
