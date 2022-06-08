package bank.controller;


import bank.model.Account;
import bank.model.Category;
import bank.model.Currency;
import bank.model.User;
import bank.repository.CurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyRepository currencyRepository;

    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    @GetMapping("/all")
    public String showAll(Model model){
        List<Currency> currencies = currencyRepository.findAll();
        model.addAttribute("currencies", currencies);
        return "/currency/all";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("currency", new Currency());
        return "currency/addCurrency";
    }

    @PostMapping("/add")
    public String addUser(@Valid final Currency currency, final BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            return "currency/addCurrency";
        }

        currencyRepository.save(currency);
        return "redirect:all";
    }
}
