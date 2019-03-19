package main.java.models.Player.MainPlayer;
import main.java.models.CardsList.CardsList;
import main.java.models.Player.Player;
import main.java.models.Table.Table;
import main.java.models.Card.Card;

import java.util.Scanner;

public class MainPlayer extends Player {
    private Scanner scan;

    public MainPlayer(CardsList cardsList, String name) {
        super(cardsList, name);
        scan = new Scanner(System.in);
    }

    @Override
    public boolean processing(Table table, Player aiPlayer, int trumpId) {
        int cardNum = 0;
        if (scan.hasNextInt())
            cardNum = scan.nextInt() - 1;
        if (cardNum > hand.size() - 1 || cardNum < 0)
            return false;

        CardsList mainTableCards = table.getPlayerCards(this);
        CardsList aiTableCards = table.getPlayerCards(aiPlayer);
        int mainTableCardsLength = mainTableCards.size();
        int aiTableCardsLength = aiTableCards.size();

        Card mainCard = hand.get(cardNum);

        int allTableCardsLength = table.getAllCardsLength();

        if (allTableCardsLength == 0 || table.existsCard(mainCard)) {
            table.addCard(this, hand.remove(cardNum));
            return true;
        } else if (aiTableCardsLength != 0) {
            Card aiCard = aiTableCards.get(aiTableCardsLength - 1);
            if (mainCard.canBeat(aiCard, trumpId)) {
                table.addCard(this, hand.remove(cardNum));
                return true;
            }
        }
        return false;
    }
}
