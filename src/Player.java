abstract class Player {
    protected String playerName;
    protected CardsList hand;
    public Player(CardsList cardsList, String name) {
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
        System.out.println();
    }

    public int cardsLength() {
        return hand.size();
    }

    public abstract void processing(Table table);

}
