package io.codeforall.bootcamp.filhosdamain.uno.server;
import io.codeforall.bootcamp.filhosdamain.uno.game.Color;
import io.codeforall.bootcamp.filhosdamain.uno.game.Player;
import io.codeforall.bootcamp.filhosdamain.uno.game.PlayerList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class MessageSender {

    public void sendMessage(Message message) {
        Thread t  = new Thread(() -> {
            for (PrintStream s : message.receivers) {
                s.print(message.color + message.message + Color.RESET);
            }
        });
        t.start();
    }

    public MessageBuilder getMessageBuilder(String message) {
        return new MessageBuilder(message);
    }

    private class Message {
        public final String type;
        public final String color;
        public final String message;
        public final List<PrintStream> receivers;

        private Message(MessageBuilder builder) {
            this.type = builder.type;
            this.color = builder.color;
            this.message = builder.message;
            this.receivers = builder.receivers;
        }
    }

    public class MessageBuilder {
        private String type = "normal";
        private String color = Color.WHITE.ASCII_CODE;
        private String message;
        private List<PrintStream> receivers = new LinkedList<>();

        public MessageBuilder(String message) {
            this.message = message + "\n";
        }

        public MessageBuilder addReceiver(PrintStream receiver) {
            receivers.add(receiver);
            return this;
        }

        public MessageBuilder addReceivers(PlayerList receivers) {
            for (Player p : receivers) {
                addReceiver(p.getPrintStream());
            }

            return this;
        }

        public MessageBuilder addPrefix(String prefix) {
            this.message = prefix + " " + message;
            return this;
        }

        public MessageBuilder setColor(Color color) {
            this.color = color.ASCII_CODE;
            return this;
        }

        public Message getMessage() {
            return new Message(this);
        }
    }


}
