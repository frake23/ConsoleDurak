package main.java.models.Player.AIPlayer;

import main.java.models.CardsList.CardsList;
import main.java.models.Card.Card;
import main.java.models.Player.Player;
import main.java.models.Table.Table;

public class AIPlayer extends Player {
    public AIPlayer(CardsList cardsList, String name){ super(cardsList, name); }

    @Override
    public boolean processing(Table table, Player mainPlayer, int trumpId) {
        CardsList mainTableCards = table.getPlayerCards(mainPlayer);
        CardsList aiTableCards = table.getPlayerCards(this);
        int mainTableCardsLength = mainTableCards.size();
        int aiTableCardsLength = aiTableCards.size();

        int allTableCardsLength = table.getAllCardsLength();

        if (allTableCardsLength == 0) {
            table.addCard(this, hand.remove(getMinNonTrumpCard(trumpId)));
            return true;
        } else
            return true;
    }

    private int getMinNonTrumpCard(int trumpId) {
        Card minCard = hand.get(0);
        int minCardSuitId = minCard.getSuitId();
        int minCardNameId = minCard.getNameId();

        for (int i = 1; i < hand.size(); i++) {
            Card card = hand.get(i);

            int cardNameId = card.getNameId();
            int cardSuitId = card.getSuitId();

            if (cardSuitId != trumpId) {
                if (minCardNameId > cardNameId || minCardSuitId == trumpId) {
                    minCard = card;
                    minCardSuitId = minCard.getSuitId();
                    minCardNameId = minCard.getNameId();
                }
            } else {
                if (minCardSuitId == trumpId && cardNameId < minCardNameId) {
                    minCard = card;
                    minCardSuitId = minCard.getSuitId();
                    minCardNameId = minCard.getNameId();

                }
            }
        }
        return hand.indexOf(minCard);
    }
}
