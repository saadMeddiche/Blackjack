
import views.View;

public class GamePlay {

    public Integer[][] playerHand;
    public Integer[][] dealerHand;

    public static void main(String[] args) throws Exception {
        while (true) {
            View.lobby();

            View.mixing_cards();

            View.distribute_cards();

            View.play_round();
        }
    }

}
