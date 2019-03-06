package credits.controller;

import credits.exception.ModelException;
import credits.model.Bank;
import credits.service.BankService;
import credits.view.RedirectViewModel;
import credits.view.View;
import credits.view.ViewModel;

import java.util.Date;

public class BankController {

    BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    public View showAllBanks() {

        ViewModel view = new ViewModel("WEB-INF/jsp/bank-list.jsp");
        view.addParameter("currentDateFromBackend", new Date());
        view.addParameter("listOfData", bankService.getBanks());
        return view;

    }


    public View showAddBankPage() {
        return new ViewModel("WEB-INF/jsp/add-bank.jsp");
    }

    public View addBank(Bank bank) {
        View view;
        try {
            bankService.addBank(bank);
            view = new ViewModel("bank-list");
        } catch (ModelException e) {
            view = new ViewModel("add-bank");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

}
