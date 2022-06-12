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
    private final CommissionRepository commissionRepository;

    public OperationHistoryController(OperationHistoryRepository operationHistoryRepository, CategoryRepository categoryRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository, UserRepository userRepository, CommissionRepository commissionRepository) {
        this.operationHistoryRepository = operationHistoryRepository;

        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.commissionRepository = commissionRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model) {
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
        Account accountFrom = accountRepository.getOne(operation.getFromAccount().getId());
        Account accountTo = accountRepository.getOne(operation.getToAccount().getId());
        double value = operation.getAmount();
        Category operationType = operation.getOperationType();
        int currencyFrom = (int) operation.getCurrencyFrom().getId();
        int currencyTo = (int) operation.getCurrencyFrom().getId();
        double commissionUSD = commissionRepository.getOne((long) currencyFrom).getCommissionRate();
        double commissionEUR = commissionRepository.getOne((long) currencyFrom).getCommissionRate();
        double commissionPLN = commissionRepository.getOne((long) currencyFrom).getCommissionRate();

        
        //transfer
        if (operationType.getId() == 1) {
            switch (currencyFrom) {
                case 1 -> {

                    double currentUSDFrom = accountFrom.getBalanceUSD();
                    accountFrom.setBalanceUSD(currentUSDFrom - value);
                    double currentUSDTo = accountTo.getBalanceUSD();
                    accountTo.setBalanceUSD(value + currentUSDTo);
                    operation.setCommissionUSD(value * commissionUSD);
                }
                case 2 -> {

                    double currentEURFrom = accountFrom.getBalanceEUR();
                    accountFrom.setBalanceEUR(currentEURFrom - value);
                    double currentEURTo = accountTo.getBalanceEUR();
                    accountTo.setBalanceEUR(value + currentEURTo);
                    operation.setCommissionEUR(value * commissionEUR);
                }
                case 3 -> {

                    double currentPLNFrom = accountFrom.getBalancePLN();
                    accountFrom.setBalancePLN(currentPLNFrom - value);
                    double currentPLNTo = accountTo.getBalancePLN();
                    accountTo.setBalancePLN(value + currentPLNTo);
                    operation.setCommissionPLN(value * commissionPLN);
                }
            }
        }

        //ExchangeTransfer
        // USD -> EUR
        // USD -> PLN
        // EUR -> USD
        // EUR -> PLN
        // PLN -> USD
        // PLN -> EUR
        else if (operationType.getId() == 1 && currencyFrom == 1 && currencyTo == 2) {

            double exchangeRateUsdEur = 0.95;
            double currentUSDFrom = accountFrom.getBalanceUSD();
            accountFrom.setBalanceUSD(currentUSDFrom - (value * commissionUSD) - value);
            double currentEURTo = accountTo.getBalanceEUR();
            accountTo.setBalanceEUR(value * exchangeRateUsdEur + currentEURTo);
            operation.setCommissionUSD(value * commissionUSD);


        } else if (operationType.getId() == 1 && currencyFrom == 1 && currencyTo == 3) {

            double exchangeRateUsdPln = 4.38;
            double currentUSDFrom = accountFrom.getBalanceUSD();
            accountFrom.setBalanceUSD(currentUSDFrom - (value * commissionUSD) - value);
            double currentPLNTo = accountTo.getBalancePLN();
            accountTo.setBalancePLN(value * exchangeRateUsdPln + currentPLNTo);
            operation.setCommissionUSD(value * commissionUSD);


        } else if (operationType.getId() == 1 && currencyFrom == 2 && currencyTo == 1) {

            double exchangeRateEurUsd = 1 / 0.95;
            double currentEURFrom = accountFrom.getBalanceEUR();
            accountFrom.setBalanceEUR(currentEURFrom - (value * commissionEUR) - value);
            double currentUSDTo = accountTo.getBalanceUSD();
            accountTo.setBalanceUSD(value * exchangeRateEurUsd + currentUSDTo);
            operation.setCommissionEUR(value * commissionEUR);


        } else if (operationType.getId() == 1 && currencyFrom == 2 && currencyTo == 3) {

            double exchangeRateEurPln = 4.61;
            double currentEURFrom = accountFrom.getBalanceEUR();
            accountFrom.setBalanceEUR(currentEURFrom - (value * commissionEUR) - value);
            double currentPLNTo = accountTo.getBalancePLN();
            accountTo.setBalancePLN(value * exchangeRateEurPln + currentPLNTo);
            operation.setCommissionEUR(value * commissionEUR);


        } else if (operationType.getId() == 1 && currencyFrom == 3 && currencyTo == 1) {

            double exchangeRatePlnUsd = 1 / 4.38;
            double currentPLNFrom = accountFrom.getBalancePLN();
            accountFrom.setBalancePLN(currentPLNFrom - (value * commissionPLN) - value);
            double currentUSDTo = accountTo.getBalanceUSD();
            accountTo.setBalanceUSD(value * exchangeRatePlnUsd + currentUSDTo);
            operation.setCommissionPLN(value * commissionPLN);


        } else if (operationType.getId() == 1 && currencyFrom == 3 && currencyTo == 2) {

            double exchangeRatePlnEur = 1 / 4.61;
            double currentPLNFrom = accountFrom.getBalancePLN();
            accountFrom.setBalancePLN(currentPLNFrom - (value * commissionPLN) - value);
            double currentEURTo = accountTo.getBalanceEUR();
            accountTo.setBalanceEUR(value * exchangeRatePlnEur + currentEURTo);
            operation.setCommissionPLN(value * commissionPLN);


        }


        //deposit
        else if (operationType.getId() == 2) {
            switch (currencyFrom) {
                case 1 -> {
                    double currentUSD = accountTo.getBalanceUSD();
                    accountTo.setBalanceUSD(value + currentUSD);
                    operation.setFromAccount(accountTo);
                }
                case 2 -> {
                    double currentEUR = accountTo.getBalanceEUR();
                    accountTo.setBalanceEUR(value + currentEUR);
                    operation.setFromAccount(accountTo);
                }
                case 3 -> {
                    double currentPLN = accountTo.getBalancePLN();
                    accountTo.setBalancePLN(value + currentPLN);
                    operation.setFromAccount(accountTo);
                }
            }
        }

        //withdrawal
        else if (operationType.getId() == 3) {
            switch (currencyFrom) {
                case 1 -> {
                    double currentUSD = accountFrom.getBalanceUSD();
                    accountFrom.setBalanceUSD(currentUSD - value);
                    operation.setToAccount(accountFrom);
                }
                case 2 -> {
                    double currentEUR = accountFrom.getBalanceEUR();
                    accountFrom.setBalanceEUR(currentEUR - value);
                    operation.setToAccount(accountFrom);
                }
                case 3 -> {
                    double currentPLN = accountFrom.getBalancePLN();
                    accountFrom.setBalancePLN(currentPLN - value);
                    operation.setToAccount(accountFrom);
                }
            }
        }

        //exchange
        else if (operationType.getId() == 4 && currencyFrom == 1 && currencyTo == 2) {
            double exchangeRateUsdEur = 0.95;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentEUR = accountFrom.getBalanceEUR();
            operation.setToAccount(accountFrom);
            accountFrom.setBalanceUSD(currentUSD - value - (value * commissionUSD));
            accountFrom.setBalanceEUR(currentEUR + value * exchangeRateUsdEur);
            operation.setCommissionUSD(value * commissionUSD);

        } else if (operationType.getId() == 4 && currencyFrom == 1 && currencyTo == 3) {
            double exchangeRateUsdPln = 4.38;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentPLN = accountFrom.getBalancePLN();
            operation.setFromAccount(accountTo);
            accountFrom.setBalanceUSD(currentUSD - value - (value * commissionUSD));
            accountFrom.setBalancePLN(currentPLN + value * exchangeRateUsdPln);
            operation.setCommissionUSD(value * commissionUSD);

        } else if (operationType.getId() == 4 && currencyFrom == 2 && currencyTo == 1) {
            double exchangeRateEurUsd = 1 / 0.95;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentEUR = accountFrom.getBalanceEUR();
            operation.setFromAccount(accountTo);
            accountFrom.setBalanceEUR(currentEUR - value - (value * commissionEUR));
            accountFrom.setBalanceUSD(currentUSD + value * exchangeRateEurUsd);

        } else if (operationType.getId() == 4 && currencyFrom == 3 && currencyTo == 1) {
            double exchangeRatePlnUsd = 1 / 4.38;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentPLN = accountFrom.getBalancePLN();
            operation.setFromAccount(accountTo);
            accountFrom.setBalancePLN(currentPLN - value - (value * commissionPLN));
            accountFrom.setBalanceUSD(currentUSD + value * exchangeRatePlnUsd);

        }   else if (operationType.getId() == 4 && currencyFrom == 2 && currencyTo == 3) {
            double exchangeRateEurPln = 4.61;
            double currentEUR = accountFrom.getBalanceEUR();
            double currentPLN = accountFrom.getBalancePLN();
            operation.setFromAccount(accountTo);
            accountFrom.setBalanceEUR(currentEUR - value - (value * commissionEUR));
            accountFrom.setBalancePLN(currentPLN + value * exchangeRateEurPln);

        } else if (operationType.getId() == 4 && currencyFrom == 3 && currencyTo == 2) {
            double exchangeRatePlnEur = 1 / 4.61;
            double currentPLN = accountFrom.getBalancePLN();
            double currentEUR = accountFrom.getBalanceEUR();
            operation.setFromAccount(accountTo);
            accountFrom.setBalancePLN(currentPLN - value - (value * commissionPLN));
            accountFrom.setBalanceEUR(currentEUR + value * exchangeRatePlnEur);

        }


        operationHistoryRepository.save(operation);
        return "redirect:all";
    }

}
