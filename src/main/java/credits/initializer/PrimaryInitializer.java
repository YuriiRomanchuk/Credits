package credits.initializer;

import credits.controller.*;
import credits.dao.*;
import credits.service.BankService;
import credits.service.ClientCreditService;
import credits.service.ClientService;
import credits.service.CreditLineService;
import credits.transformer.BankTransformer;
import credits.transformer.ClientCreditTransformer;
import credits.transformer.ClientTransformer;
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
    private DataSource dataSource;
    private BankService bankService;
    private BankController bankController;
    private DaoBank daoBank;
    private DaoCreditLine daoCreditLine;
    private CreditLineController creditLineController;
    private CreditLineService creditLineService;
    private ClientController сlientController;
    private ClientService сlientService;
    private DaoClient daoClient;
    private DaoClientCredit daoClientCredit;
    private ClientCreditService clientCreditService;
    private ClientCreditController clientCreditController;


    public PrimaryInitializer() {
        this.dataSource = new DataSource();
        this.daoBank = new DaoBank(dataSource);
        this.bankService = new BankService(daoBank);
        this.bankController = new BankController(bankService);
        this.daoCreditLine = new DaoCreditLine(dataSource, daoBank);
        this.creditLineService = new CreditLineService(daoCreditLine);
        this.creditLineController = new CreditLineController(bankService, creditLineService);
        this.daoClient = new DaoClient(dataSource);
        this.сlientService = new ClientService(daoClient);
        this.сlientController = new ClientController(сlientService, bankService);
        this.daoClientCredit = new DaoClientCredit(dataSource, daoClient, daoCreditLine);
        this.clientCreditService = new ClientCreditService(daoClientCredit);
        this.clientCreditController = new ClientCreditController(clientCreditService, bankService);

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
        getControllers.put("/choose-credit-line", r -> creditLineController.chooseAllCreditLines());
        getControllers.put("/add-client", r -> сlientController.showAddClient());
        getControllers.put("/client-cabinet", r -> clientCreditController.showClientCabinet());

    }

    private void initializePostControllers() {

        BankTransformer bankTransformer = new BankTransformer();
        CreditLineTransformer creditLineTransformer = new CreditLineTransformer();
        ClientTransformer clientTransformer = new ClientTransformer();
        ClientCreditTransformer clientCreditTransformer = new ClientCreditTransformer();

        postControllers.put("/add-bank", r -> bankController.addBank(bankTransformer.transformToObject(r)));
        postControllers.put("/add-credit-line", r -> creditLineController.addCreditLine(creditLineTransformer.transformToObject(r)));
        postControllers.put("/add-client", r -> сlientController.addClient(clientTransformer.transformToObject(r), clientCreditService));
        postControllers.put("/choose-credit-line", r -> clientCreditController.addClientCredit(clientCreditTransformer.transformToObject(r)));
        postControllers.put("/client-cabinet", r -> creditLineController.chooseSelectionCreditLines(creditLineTransformer.transformToMap(r)));
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
