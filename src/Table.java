import java.util.HashMap;

class Table {
    private HashMap<Player, CardsList> cardsOnTable;

    public Table(Player mainPlayer, Player aiPlayer) {
        cardsOnTable = new HashMap<>();
        cardsOnTable.put(mainPlayer, new CardsList());
        cardsOnTable.put(aiPlayer, new CardsList());
    }

    public void addCard(Player player, Card card) {
        cardsOnTable.get(player).add(card);
    }

    public void tablePrinting() {
        for (Player p: cardsOnTable.keySet()) {
            System.out.print(p.getPlayerName() + " ");
            cardsOnTable.get(p).print();
        }
    }

    public CardsList getPlayerCards(Player player) {
        return cardsOnTable.get(player);
    }
}
