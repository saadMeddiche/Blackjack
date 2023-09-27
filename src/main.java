import services.CardService;
import view.View;

public class main {

    public static void main(String[] args) throws Exception {

        CardService cardService = new CardService();

        View app = new View(cardService);

        app.start_game();
    }

}
