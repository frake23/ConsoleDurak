package main.java.models.Player;
import main.java.models.CardsList.CardsList;
import main.java.models.Table.Table;

import java.util.Scanner;

public class MainPlayer extends Player {
    private Scanner scan;

    public MainPlayer(CardsList cardsList, String name) {
        super(cardsList, name);
        scan = new Scanner(System.in);
    }

    @Override
    public boolean processing(Table table, Player aiPlayer, int trumpId) {
        int cardNum = 0;
        if (scan.hasNextInt())
            cardNum = scan.nextInt() - 1;
        if (cardNum > hand.size() - 1 || cardNum < 0)
            return false;
        return makeMove(table, aiPlayer, cardNum, trumpId);
    }
}
