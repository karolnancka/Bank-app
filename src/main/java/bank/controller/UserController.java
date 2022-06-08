package bank.controller;


import bank.model.Account;
import bank.model.User;
import bank.repository.AccountRepository;
import bank.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserController(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/users/allUsers";
    }

    @GetMapping("/register")
    public String getAddForm(Model model) {
        model.addAttribute("user", new User());
        return "users/registerUser";
    }

    @PostMapping("/register")
    public String addUser(@Valid final User user, final BindingResult validationResult) {
        Account account = new Account();
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        account.setNumber(number);
        account.setUser(user);
        account.setBalanceUSD(0);
        account.setBalanceEUR(0);
        account.setBalancePLN(0);
        if (validationResult.hasErrors()) {
            return "users/register";
        }

        userRepository.save(user);
        accountRepository.save(account);
        return "redirect:all";
    }

    @GetMapping("/get/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        return "users/getOne";
    }

    @GetMapping("/edit/{id}")
    public String editById(@PathVariable long id, Model model) {
        model.addAttribute("edit_url", "edit");
        model.addAttribute("user", userRepository.findById(id));
        return "users/edit";
    }

    @PostMapping("/edit")
    public String editUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        userRepository.save(user);
        return "redirect:/users/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id, Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        userRepository.deleteById(id);
        return "users/allUsers";
    }
}