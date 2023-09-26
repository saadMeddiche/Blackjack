package application;

import services.CardService;
import view.View;

public class App {

    public CardService cardService = new CardService();
    public View view = new View(cardService);

    public void start_game() throws Exception {

        view.lobby();

        prepare_cards();

        distribute_cards();

        while (true) {

            show_board();

        }
    }

    public void prepare_cards() throws Exception {

        cardService.prepare_cards();

        view.prepare_cards(0);

    }

    public void distribute_cards() throws Exception {
        view.distribute_cards(0);
        cardService.distribute_cards();
    }

    public void show_board() throws Exception {
        view.show_board();
    }

}
