package bank.controller;

import bank.model.*;
import bank.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
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
    @Transactional
    public String makeTransaction(OperationHistory operation, final BindingResult validationResult) {
        if (validationResult.hasErrors()) {
           return "operations/actionForm";
        }
        Account accountTo = accountRepository.getOne(operation.getToAccount().getId());
        List<Account> accounts = accountRepository.findAll();
        Double value = operation.getAmount();
        Category operationType = operation.getOperationType();
        int currency = (int) operation.getCurrencyFrom().getId();
        if (operation.getOperationType().getId() == 2){
            switch (currency){
                case 1 :
                    double currentUSD = accountTo.getBalanceUSD();
                    accountTo.setBalanceUSD(value + currentUSD);
                    break;
                case 2 :
                    double currentEUR = accountTo.getBalanceEUR();
                    accountTo.setBalanceEUR(value + currentEUR);
                    break;
                case 3 :
                    double currentPLN = accountTo.getBalancePLN();
                    accountTo.setBalancePLN(value + currentPLN);
                    break;
            }
        }

        operationHistoryRepository.save(operation);
        return "redirect:all";
    }
}
