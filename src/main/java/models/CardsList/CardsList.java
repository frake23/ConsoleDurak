package main.java.models.CardsList;

import main.java.models.Card.Card;

import java.util.ArrayList;

public class CardsList extends ArrayList<Card> {
    public CardsList() {
        super();
    }
    public void print() {
        this.forEach(card -> {
            String cardString = card.toString();
            System.out.print(cardString + " ");
        });
        System.out.println();
    }
}
