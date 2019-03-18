package main.java.models.Card;

import java.util.HashMap;

public class Card {
    private int id;
    private int nameId;
    private int suitId;

    public Card(int id) {
        this.id = id;
        nameId = id / 10;
        suitId = id % 10;
    }

    public HashMap<String, String> decode() {
        HashMap<String, String> cardDecode = new HashMap<>();
        int cardNameId = getNameId();
        int cardSuitId = getSuitId();
        cardDecode.put("name", idToCardName(cardNameId));
        cardDecode.put("suit", idToCardSuit(cardSuitId));
        return cardDecode;
    }

    @Override
    public String toString() {
        int cardNameId = id / 10;
        int cardSuitId = id % 10;
        return String.format("|%s %s|", idToCardName(cardNameId), idToCardSuit(cardSuitId));
    }

    public int getNameId() {
        return nameId;
    }

    public int getSuitId() {
        return suitId;
    }

    private int getId() {
        return id;
    }


    private String idToCardName(int id) {
        switch (id) {
            case 1:
                return "6";
            case 2:
                return "7";
            case 3:
                return "8";
            case 4:
                return "9";
            case 5:
                return "10";
            case 6:
                return "Валет";
            case 7:
                return "Дама";
            case 8:
                return "Король";
            case 9:
                return "Туз";
        }
        return null;
    }

    private String idToCardSuit(int id) {
        switch (id) {
            case 1:
                return "Бубны";
            case 2:
                return "Червы";
            case 3:
                return "Пики";
            case 4:
                return "Трефы";
        }
        return null;
    }

    public boolean canBeat(Card card, int trumpId) {
        if (suitId == trumpId) {
            if (card.getSuitId() == trumpId)
                return id > card.getId();
            else
                return true;
        } else if (suitId == card.getSuitId())
                return id > card.getId();
        else
            return false;
    }
}
