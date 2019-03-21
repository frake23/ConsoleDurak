package main.java.models.Player.AIPlayer;

import main.java.models.CardsList.CardsList;
import main.java.models.Card.Card;
import main.java.models.Player.Player;
import main.java.models.Table.Table;

import java.util.stream.Collectors;

public class AIPlayer extends Player {
    public AIPlayer(CardsList cardsList, String name){ super(cardsList, name); }

    @Override
    public int processing(Table table, Player mainPlayer, int trumpId) {
        CardsList mainTableCards = table.getPlayerCards(mainPlayer);
        CardsList aiTableCards = table.getPlayerCards(this);
        int mainTableCardsLength = mainTableCards.size();
        // int aiTableCardsLength = aiTableCards.size();

        int allTableCardsLength = table.getAllCardsLength();

        if (allTableCardsLength == 0) {
            table.addCard(this, hand.remove(getMinNonTrumpCard(hand, trumpId)));
            return 0;
        } else if (allTableCardsLength % 2 == 1)
        {
            Card mainCard = mainTableCards.get(mainTableCardsLength - 1);
            int beatCardIndex = getBeatCard(mainCard, trumpId);
            if (beatCardIndex != -1) {
                table.addCard(this, hand.remove(beatCardIndex));
                return 0;
            }
            else
                return 1;
        } else if (allTableCardsLength % 2 == 0) {
            int minExistsCardIndex = getMinExistsCard(table, trumpId);
            if (minExistsCardIndex != -1) {
                table.addCard(this, hand.remove(minExistsCardIndex));
                return 0;
            }
            else
                return 2;
        }
        return 1;
    }

    private int getMinExistsCard(Table table, int trumpId) {
        CardsList cardsExists = hand.stream().filter(table::existsCard).collect(Collectors.toCollection(CardsList::new));
        return cardsExists.size() != 0 ? getMinNonTrumpCard(cardsExists, trumpId): -1;
    }

    private int getBeatCard(Card mainCard, int trumpId) {
        CardsList canBeatCards = hand.stream().filter(card -> card.canBeat(mainCard, trumpId)).collect(Collectors.toCollection(CardsList::new));
        return canBeatCards.size() != 0 ? getMinNonTrumpCard(canBeatCards, trumpId): -1;
    }

    private int getMinNonTrumpCard(CardsList cards, int trumpId) {
        Card minCard = cards.get(0);
        int minCardSuitId = minCard.getSuitId();
        int minCardNameId = minCard.getNameId();

        for (Card card: cards) {
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
