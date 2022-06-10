package bank.controller;

import bank.model.*;
import bank.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/operations")
public class OperationHistoryController {

    private final OperationHistoryRepository operationHistoryRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    public OperationHistoryController(OperationHistoryRepository operationHistoryRepository, CategoryRepository categoryRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.operationHistoryRepository = operationHistoryRepository;

        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        List<OperationHistory> operations = operationHistoryRepository.findAll();
        model.addAttribute("operations", operations);
        return "/operations/allOperations";
    }

    @GetMapping("/operation")
    public String getTransactionForm(Model model) {

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);

        List<Currency> currencies = currencyRepository.findAll();
        model.addAttribute("currencies", currencies);
        model.addAttribute("operation", new OperationHistory());


        return "operations/actionForm";
    }

    @PostMapping("/operation")
    public String makeTransaction(OperationHistory operation, final BindingResult validationResult) {
        if (validationResult.hasErrors()) {
           return "operations/actionForm";
        }
        Account accountTo = operation.getToAccount();
        List<Account> accounts = accountRepository.findAll();
        Double value = operation.getAmount();
        Category operationType = operation.getOperationType();
        String currency = operation.getCurrencyFrom().getCurrency();
        if (operation.getOperationType().getId() == 2){
            switch (currency){
                case "USD" :
                    accountTo.setBalanceUSD(value);
                    break;
                case "EUR" :
                    accountTo.setBalanceEUR(value);
                    break;

            }
        }



        operationHistoryRepository.save(operation);
        return "redirect:all";
    }
}
