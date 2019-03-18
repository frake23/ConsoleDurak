package main.java;

import main.java.models.Player.*;
import main.java.models.Card.*;
import main.java.models.Player.AIPlayer.AIPlayer;
import main.java.models.Player.MainPlayer.MainPlayer;
import main.java.models.Table.*;
import main.java.models.Deck.Deck;
import main.java.models.CardsList.CardsList;

class Game {
    private static Player mainPlayer;
    private static Player aiPlayer;
    private static Deck deck;
    private static Table table;

    private static int trumpSuitId;

    private static Player turnPlayer = null;

    private static Player firstTurnPlayer(Card mainCard, Card aiCard) {
        if (mainCard == null && aiCard == null)
            return mainPlayer;
        else if (mainCard == null)
            return aiPlayer;
        else if (aiCard == null)
            return mainPlayer;
        else
            return mainCard.getNameId() < aiCard.getNameId()? mainPlayer : aiPlayer;
    }

    private static void mainPlayerInit() {
        String mainPlayerName = "Main Player";
        CardsList mainHand = deck.takeCards(6);
        mainPlayer = new MainPlayer(mainHand, mainPlayerName);
        mainPlayer.cardsPrinting();
    }

    private static void aiPlayerInit() {
        String aiPlayerName = "AI Player";
        CardsList aiHand = deck.takeCards(6);
        aiPlayer = new AIPlayer(aiHand, aiPlayerName);
        aiPlayer.cardsPrinting();
    }

    private static void deckInit() {
        deck = new Deck();
        deck.shuffleDeck();
    }

    private static void tableInit() {
        table = new Table(mainPlayer, aiPlayer);
    }

    private static void trumpInit() {
        Card trumpCard = deck.getCardsList().get(23);
        trumpSuitId = trumpCard.getSuitId();
        String trumpSuit = trumpCard.decode().get("suit");
        System.out.println("Козырь - " + trumpSuit);
    }

    private static void turnInit() {
        Card mainCard = mainPlayer.getMinCard(trumpSuitId);
        Card aiCard = aiPlayer.getMinCard(trumpSuitId);
        turnPlayer = firstTurnPlayer(mainCard, aiCard);
        System.out.println("Первым ходит " + turnPlayer.getPlayerName());
    }

    private static void init() {
        deckInit(); // Инициализация колоды
        mainPlayerInit(); // Инициализация главного игрока
        aiPlayerInit(); // Инициализация аи
        trumpInit(); // Инициализация козыря
        tableInit(); // Инициализация стола
        turnInit(); // Инициализация первого хода
    }

    private static void printing() {
        System.out.println("--------------Деки---------------");
        mainPlayer.cardsPrinting();
        aiPlayer.cardsPrinting();
        System.out.println("--------------Стол---------------");
        table.tablePrinting();
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) {
        init();
        while (true) {
            Player secondPlayer = turnPlayer == mainPlayer ? aiPlayer : mainPlayer;

            while (true)
                if (turnPlayer.processing(table, secondPlayer, trumpSuitId))
                    break;

            turnPlayer = secondPlayer;

            printing();

            if (turnPlayer.cardsLength() == 0){
                System.out.println("Победитель - " + turnPlayer.getPlayerName());
                break;
            }
        }
    }
}
