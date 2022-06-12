package bank.controller;

import bank.model.Account;
import bank.model.OperationHistory;
import bank.model.User;
import bank.repository.AccountRepository;
import bank.repository.OperationHistoryRepository;
import bank.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final OperationHistoryRepository operationHistoryRepository;

    public AccountController(AccountRepository accountRepository, UserRepository userRepository, OperationHistoryRepository operationHistoryRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.operationHistoryRepository = operationHistoryRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "/accounts/allAccounts";
    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("account", accountRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return "accounts/getOne";
    }

}
