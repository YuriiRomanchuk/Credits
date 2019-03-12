package credits.controller;

import credits.view.View;
import credits.view.ViewModel;

import java.util.Locale;

public class StartController {

    public View showIndexPage() {
        View view = new ViewModel("WEB-INF/jsp/index.jsp");
        view.addParameter("local", new Locale("ru", "RU"));
        return view;
    }
}
