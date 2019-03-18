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

    protected boolean makeMove(Table table, Player opponentPlayer, int cardNum, int trumpId) {
        CardsList thisTableCards = table.getPlayerCards(this);
        CardsList opponentTableCards = table.getPlayerCards(opponentPlayer);
        int thisTableCardsLength = thisTableCards.size();
        int opponentTableCardsLength = opponentTableCards.size();
        Card thisCard = hand.get(cardNum);
        Card opponentCard = opponentTableCardsLength == 0 ? null: opponentTableCards.get(opponentTableCards.size() - 1);
        int deltaLength = opponentTableCardsLength - thisTableCardsLength;
        switch (deltaLength) {
            case 0: table.addCard(this, hand.remove(cardNum));
                    return true;
            case 1: if (thisCard.canBeat(opponentCard, trumpId)) {
                        table.addCard(this, hand.remove(cardNum));
                        return true;
                    } else
                        return false;
            default: return false;
        }
    }
}
