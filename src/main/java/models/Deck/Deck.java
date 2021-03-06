package main.java.models.Deck;

import main.java.models.Card.Card;
import main.java.models.CardsList.CardsList;

import java.util.Collections;

public class Deck {
    private CardsList cardsList = new CardsList();
    public Deck() {
        for (int i = 1; i <= 9; i++)
            for (int j = 1; j <= 4; j++)
                cardsList.add(new Card(i * 10 + j));
    }

    public CardsList getCardsList() {
        return cardsList;
    }

    public void shuffle() {
        Collections.shuffle(cardsList);
    }

    public CardsList takeCards(int num) {
        CardsList takenCardsList = new CardsList();
        for (int i = 0; i < num; i++)
            takenCardsList.add(cardsList.remove(i));
        return takenCardsList;
    }

    public int getLength() {
        return cardsList.size();
    }

    public void clear() {
        cardsList.clear();
    }

}
