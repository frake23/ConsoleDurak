class Game {
    private static Player mainPlayer;
    private static Player aiPlayer;
    private static Deck deck;
    private static Table table;

    private static int trumpSuitId;

    private static Player turn = null;

    private static void mainPlayerInit() {
        String mainPlayerName = "Main Player";
        CardsList mainHand = deck.takeCards(6);
        mainPlayer = new MainPlayer(mainHand, mainPlayerName);
        mainPlayer.cardsPrinting();
    }

    private static void aiPlayerInit() {
        String aiPlayerName = "AI Player";
        CardsList aiHand = deck.takeCards(6);
        aiPlayer = new AIPlayer(aiHand, aiPlayerName);
        aiPlayer.cardsPrinting();
    }

    private static void deckInit() {
        deck = new Deck();
        deck.shuffleDeck();
    }

    private static void tableInit() {
        table = new Table(mainPlayer, aiPlayer);
    }

    private static void trumpInit() {
        trumpSuitId = deck.getCardsList().get(23).getId() / 10;
    }

    private static void init() {
        deckInit(); // Инициализация колоды
        mainPlayerInit(); // Инициализация главного игрока
        aiPlayerInit(); // Инициализация аи
        trumpInit(); // Инициализация козыря
        tableInit(); // Инициализация стола

    }

    public static void main(String[] args) {
        init();
        mainPlayer.processing(table);
        mainPlayer.cardsPrinting();
        table.tablePrinting();
        while (true) {
            return;
        }
    }
}
