package credits.initializer;

import credits.controller.BankController;
import credits.controller.StartController;
import credits.service.BankService;
import credits.transformer.BankTransformer;
import credits.view.View;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PrimaryInitializer {

    private static final String MODEL_VIEW_ATTRIBUTE = "MODEL_VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();
    private BankController bankController = new BankController(new BankService());


    public PrimaryInitializer() {
        initializeGetControllers();
        initializePostControllers();
    }

    private void initializeGetControllers() {

        StartController startController = new StartController();

        getControllers.put("/", r -> startController.showIndexPage());
        getControllers.put("/add-bank", r -> bankController.showAddBankPage());
        getControllers.put("/bank-list", r -> bankController.showAllBanks());

    }

    private void initializePostControllers() {

        BankTransformer bankTransformer = new BankTransformer();
        postControllers.put("/add-bank", r -> bankController.addBank(bankTransformer.transformToObject(r)));

    }


    public View getView() {

        return null;

    }


    public Map<String, Function<HttpServletRequest, View>> getGetControllers() {
        return getControllers;
    }

    public Map<String, Function<HttpServletRequest, View>> getPostControllers() {
        return postControllers;
    }


    public static String getModelViewAttribute() {
        return MODEL_VIEW_ATTRIBUTE;
    }

}
