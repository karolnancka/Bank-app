package bank.controller;

import bank.model.Account;
import bank.model.Category;
import bank.model.OperationHistory;
import bank.repository.AccountRepository;
import bank.repository.CategoryRepository;
import bank.repository.OperationHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/operations")
public class OperationHistoryController {

    private final OperationHistoryRepository operationHistoryRepository;
    private final CategoryRepository categoryRepository;
    private final AccountRepository accountRepository;

    public OperationHistoryController(OperationHistoryRepository operationHistoryRepository, CategoryRepository categoryRepository, AccountRepository accountRepository) {
        this.operationHistoryRepository = operationHistoryRepository;

        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
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

        model.addAttribute("operation", new OperationHistory());

        return "operations/actionForm";
    }

    @PostMapping("/operation")
    public String makeTransaction(@Valid final OperationHistory operation, final BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "operations/actionForm";
        }
        operationHistoryRepository.save(operation);
        return "redirect:all";
    }
}
