import java.util.ArrayList;

class CardsList extends ArrayList<Card> {
    CardsList() {
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
