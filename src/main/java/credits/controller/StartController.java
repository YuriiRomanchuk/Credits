package credits.controller;

import credits.view.ViewModel;
import credits.view.View;

public class StartController {

    public View showIndexPage() {
        return new ViewModel("index.html");
    }

}
