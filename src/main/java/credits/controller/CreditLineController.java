package credits.controller;

import credits.exception.ModelException;
import credits.data.model.CreditLine;
import credits.service.BankService;
import credits.service.CreditLineService;
import credits.view.RedirectViewModel;
import credits.view.View;
import credits.view.ViewModel;

import java.util.Date;
import java.util.Map;

public class CreditLineController {

    private BankService bankService;
    private CreditLineService creditLineService;

    public CreditLineController(BankService bankService, CreditLineService creditLineService) {
        this.bankService = bankService;
        this.creditLineService = creditLineService;
    }

    public View showAddCreditLinePage() {
        ViewModel view = new ViewModel("WEB-INF/jsp/add-credit-line.jsp");
        view.addParameter("listOfBank", bankService.getBanks());
        return view;
    }

    public View showAllCreditLines() {

        ViewModel view = new ViewModel("WEB-INF/jsp/credit-line-list.jsp");
        view.addParameter("currentDateFromBackend", new Date());
        view.addParameter("listOfCreditLines", creditLineService.getCreditLines());
        return view;

    }


    public View chooseAllCreditLines() {

        ViewModel view = new ViewModel("WEB-INF/jsp/choose-credit-line.jsp");
      /*  view.addParameter("currentDateFromBackend", new Date());
        view.addParameter("listOfCreditLines", creditLineService.getCreditLines());*/
        return view;

    }

    public View chooseSelectionCreditLines(Map<String, String> selections) {

        ViewModel view = new ViewModel("choose-credit-line");
        view.addParameter("currentDateFromBackend", new Date());
        view.addParameter("listOfCreditLines", creditLineService.getCreditLines(selections));
        view.addParameter("client_id", selections.get("client_id"));
        return new RedirectViewModel(view);
    }


    public View addCreditLine(CreditLine creditLine) {

        View view;
        try {
            creditLineService.addCreditLine(creditLine);
            view = new ViewModel("credit-line-list");
        } catch (ModelException e) {
            e.printStackTrace();
            view = new ViewModel("add-credit-line");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }

        return new RedirectViewModel(view);
    }
}
