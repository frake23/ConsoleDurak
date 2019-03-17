import java.util.Scanner;

class MainPlayer extends Player {
    MainPlayer(CardsList cardsList, String name) {
        super(cardsList, name);
    }

    @Override
    public void processing(Table table) {
        Scanner scan = new Scanner(System.in);
        int cardNum = scan.nextInt();
        scan.close();
        table.addCard(this, hand.get(cardNum));
        hand.remove(cardNum);
    }
}
