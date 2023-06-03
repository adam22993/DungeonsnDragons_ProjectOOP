package UI;
import Units.Unit;
import java.util.Scanner;

public class GameUI {

    Scanner scanner = new Scanner(System.in);
    public GameUI() {}

    public void playerTurn(Unit currPlayer) {
        char choice;
        char subChoice;
        boolean turnOver = false;
        while (!turnOver){
            System.out.println("Choose an action:\n(1) Move\n(2) Attack\n(3) Special Ability\n(q) Do nothing\nEnter your choice: ");
            choice = scanner.next().charAt(0);
            switch (choice) {
                case '1':
                    System.out.println("Choose where to move:\n(W) Up\n(S) Down\n(A) Left\n(D) Right\nEnter your choice: ");
                    subChoice = scanner.next().charAt(0);
                    switch (subChoice){
                        case 'w':
                            System.out.println("You chose to move up.");
                            break;
                        case 's':
                            System.out.println("You chose to move down.");
                            break;
                        case 'a':
                            System.out.println("You chose to move left.");
                            break;
                        case 'd':
                            System.out.println("You chose to move right.");
                            break;
                        default:
                            System.out.println("Invalid input. Please try again.");
                    }
                    System.out.println("You chose to move " + subChoice);
//                    playerMove(currPlayer, subChoice);
                    turnOver = true;
                case '2':
//                    playerBasicAttack(currPlayer);
                    System.out.println("You chose to attack.");
                    turnOver = true;
                case '3':
//                    playerSpecialAbility(currPlayer);
                    System.out.println("You chose to use your special ability.");
                    turnOver = true;
                case 'q':
                    System.out.println("You chose to do nothing.");
                    turnOver = true;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
