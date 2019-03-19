package main.java.models.Player;

import main.java.models.Card.Card;
import main.java.models.CardsList.CardsList;
import main.java.models.Table.Table;

public abstract class Player {
    private String playerName;
    protected CardsList hand;
    protected Player(CardsList cardsList, String name) {
        hand = cardsList;
        playerName = name;
    }

    public void addCards(CardsList cardsList) {
        hand.addAll(cardsList);
    }

    public CardsList getHand() {
        return hand;
    }

    public void cardsPrinting() {
        System.out.print(playerName + " ");
        hand.print();
    }

    public int cardsLength() {
        return hand.size();
    }

    public abstract boolean processing(Table table, Player oppositePlayer, int trumpId);

    public Card getMinCard(int trumpSuitId) {
        Card minCard = null;
        for (Card card: hand)
            if (card.getSuitId() == trumpSuitId){
                if (minCard == null)
                    minCard = card;
                else if (card.getNameId() < minCard.getNameId())
                    minCard = card;
            }
        return minCard;
    }

    public String getPlayerName() {
        return playerName;
    }
}
