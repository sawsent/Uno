package io.codeforall.bootcamp.filhosdamain.uno.game;

import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Card;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.DeckFactory;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect;
import io.codeforall.bootcamp.filhosdamain.uno.game.cards.SpecialCard;
import io.codeforall.bootcamp.filhosdamain.uno.server.MessageSender.MessageBuilder;
import io.codeforall.bootcamp.filhosdamain.uno.server.MessageSender;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;

import static io.codeforall.bootcamp.filhosdamain.uno.Utils.STARTING_HAND_SIZE;
import static io.codeforall.bootcamp.filhosdamain.uno.game.cards.Effect.*;

public class Game {

    private final MessageSender messageSender;
    private PlayerList players = new PlayerList();
    private final LinkedList<Card> deck = DeckFactory.getDeck();
    private final Verifier verifier = new Verifier();


    public Game(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public boolean addPlayer(String name, InputStream in, PrintStream out) {
        return players.add(new Player(name, in, out));
    }


    public void run() {
        prepare();

        Effect beforePlayEffect = Effect.NO_EFFECT;
        Effect afterPlayEffect = Effect.NO_EFFECT;

        Card topCard;
        boolean gameEnded = false;
        Player winner = null;

        while (!gameEnded) {


            for (Player player : players) {

                // print board to all players
                topCard = deck.getFirst();

                if (beforePlayEffect.equals(PLUS_2)) {
                    if (verifier.hasPlusTwo(player)) {

                        MenuInputScanner w = new MenuInputScanner(new String[] {"Play +2", "Take 2 cards"});
                        w.setMessage("What you wanna do brother?");

                        int input = player.getInput(w);
                        if (input == 1) {
                            continue;
                        }
                    }
                    player.giveCard(deck.removeLast());
                    player.giveCard(deck.removeLast());
                    beforePlayEffect = NO_EFFECT;
                    continue;

                } else if (beforePlayEffect.equals(PLUS_4)) {
                    if (verifier.hasPlusFour(player)) {
                        // option: wanna play or take +4?

                    }
                    for (int i = 0; i < 4; i++) {
                        player.giveCard(deck.removeLast());
                    }
                    continue;


                } else if (beforePlayEffect.equals(SKIP_TURN)) {
                    beforePlayEffect = NO_EFFECT;
                    continue;
                }


                Card playedCard = getCard(player, topCard);

                deck.addFirst(playedCard);
                player.getHand().remove(playedCard);

                beforePlayEffect = playedCard.getBeforePlayEffect();
                afterPlayEffect = playedCard.getAfterPlayEffect();

                if (afterPlayEffect.equals(REVERSE)) {
                    players.reverse(player);
                    break;
                } else if (afterPlayEffect.equals(SET_COLOR)) {
                    Color color = player.chooseColor();
                    ((SpecialCard) playedCard).setColor(color);
                }

                if (player.getHand().size() == 0) {
                    winner = player;
                    gameEnded = true;
                    break;
                }

            }


        }

        messageSender.sendMessage(messageSender.getMessageBuilder("wins!").addPrefix(winner.getName()).addReceivers(players).getMessage());

    }

    private Card getCard(Player player, Card topCard) {
        boolean valid = false;
        Choice playerChoice = null;

        while (!valid) {

            playerChoice = player.choose();

            if (playerChoice.type == Choice.Type.PLAY_CARD) {
                valid = verifier.isValid(playerChoice.card, topCard);
                continue;
            }

            // PLAYER TOOK A CARD.
            if (player.getHand().size() < 40) {
                player.giveCard(deck.removeLast());
                continue;
            }
            messageSender.sendMessage(messageSender.getMessageBuilder("You have too many cards. Please play.").setColor(Color.RED).addReceiver(player.getPrintStream()).getMessage());


        }
        return playerChoice.card;
    }


    private void prepare() {
        Collections.shuffle(deck);
        Collections.shuffle(players);
        assignInitialCards();
        messageSender.sendMessage((messageSender.getMessageBuilder("Pick a card! ").addPrefix(players.get(0).getName() + ":").setColor(Color.RED).addReceiver(players.get(0).getPrintStream()).getMessage()));
        for (Card c : deck) {
            System.out.println(c.repr());
        }
        System.out.println(deck.size());
        // message goes here \/


    }

    private void assignInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < STARTING_HAND_SIZE; i++) {
                player.giveCard(deck.removeLast());
            }
        }
    }


}
