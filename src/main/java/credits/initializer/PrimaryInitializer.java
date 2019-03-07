package credits.initializer;

import credits.controller.BankController;
import credits.controller.CreditLineController;
import credits.controller.StartController;
import credits.service.BankService;
import credits.service.CreditLineService;
import credits.transformer.BankTransformer;
import credits.transformer.CreditLineTransformer;
import credits.view.View;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PrimaryInitializer {

    private static final String MODEL_VIEW_ATTRIBUTE = "MODEL_VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();
    private BankService bankService = new BankService();
    private BankController bankController = new BankController(bankService);
    private CreditLineController creditLineController = new CreditLineController(bankService, new CreditLineService());


    public PrimaryInitializer() {
        initializeGetControllers();
        initializePostControllers();
    }

    private void initializeGetControllers() {

        StartController startController = new StartController();

        getControllers.put("/", r -> startController.showIndexPage());
        getControllers.put("/add-bank", r -> bankController.showAddBankPage());
        getControllers.put("/bank-list", r -> bankController.showAllBanks());
        getControllers.put("/add-credit-line", r -> creditLineController.showAddCreditLinePage());
        getControllers.put("/credit-line-list", r -> creditLineController.showAllCreditLines());

    }

    private void initializePostControllers() {

        BankTransformer bankTransformer = new BankTransformer();
        CreditLineTransformer creditLineTransformer = new CreditLineTransformer();

        postControllers.put("/add-bank", r -> bankController.addBank(bankTransformer.transformToObject(r)));
        postControllers.put("/add-credit-line", r ->  creditLineController.addCreditLine(creditLineTransformer.transformToObject(r)));
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
