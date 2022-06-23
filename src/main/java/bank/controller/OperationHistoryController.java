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
    private final ExchangeRateRepository exchangeRateRepository;

    public OperationHistoryController(OperationHistoryRepository operationHistoryRepository, CategoryRepository categoryRepository, AccountRepository accountRepository, CurrencyRepository currencyRepository, UserRepository userRepository, CommissionRepository commissionRepository, ExchangeRateRepository exchangeRateRepository) {
        this.operationHistoryRepository = operationHistoryRepository;

        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.commissionRepository = commissionRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        List<OperationHistory> operations = operationHistoryRepository.findAll();
        model.addAttribute("operations", operations);
        return "/operations/allOperations";
    }

    @GetMapping("/operation")
    @Transactional
    public String getTransactionForm(Model model) {

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("accounts", accounts);

        List<Currency> currencies = currencyRepository.findAll();
        model.addAttribute("currencies", currencies);
        model.addAttribute("operation", new OperationHistory());

        model.addAttribute("rates", new ExchangeRate(exchangeRateRepository.getOne(1L).getId(), exchangeRateRepository.getOne(1L).getUsdToEur(), exchangeRateRepository.getOne(1L).getEurToUsd(), exchangeRateRepository.getOne(1L).getUsdToPln(),exchangeRateRepository.getOne(1L).getPlnToUsd(),exchangeRateRepository.getOne(1L).getEurToPln(),exchangeRateRepository.getOne(1L).getPlnToEur(), exchangeRateRepository.getOne(1L).getDate()));

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
        int currencyTo = (int) operation.getCurrencyTo().getId();
        double commissionUSD = commissionRepository.getOne(1L).getCommissionRate();
        double commissionEUR = commissionRepository.getOne(2L).getCommissionRate();
        double commissionPLN = commissionRepository.getOne(3L).getCommissionRate();





        //ExchangeTransfer
        // USD -> EUR
        // USD -> PLN
        // EUR -> USD
        // EUR -> PLN
        // PLN -> USD
        // PLN -> EUR


        if (operationType.getId() == 1 && currencyFrom == 1 && currencyTo == 2) {

            double currentUSDFrom = accountFrom.getBalanceUSD();
            double exchangeRateUsdEur = 0.95;

            if (currentUSDFrom >= value + value * commissionUSD) {
                accountFrom.setBalanceUSD(currentUSDFrom - (value * commissionUSD) - value);
                double currentEURTo = accountTo.getBalanceEUR();
                accountTo.setBalanceEUR(value * exchangeRateUsdEur + currentEURTo);
                operation.setCommissionUSD(value * commissionUSD);
            } else {
                return "operations/notEnoughFunds";
            }


        } else if (operationType.getId() == 1 && currencyFrom == 1 && currencyTo == 3) {

            double exchangeRateUsdPln = 4.38;
            double currentUSDFrom = accountFrom.getBalanceUSD();

            if (currentUSDFrom >= value + value * commissionUSD) {
                accountFrom.setBalanceUSD(currentUSDFrom - (value * commissionUSD) - value);
                double currentPLNTo = accountTo.getBalancePLN();
                accountTo.setBalancePLN(value * exchangeRateUsdPln + currentPLNTo);
                operation.setCommissionUSD(value * commissionUSD);
            } else {
                return "operations/notEnoughFunds";
            }


        } else if (operationType.getId() == 1 && currencyFrom == 2 && currencyTo == 1) {

            double exchangeRateEurUsd = 1 / 0.95;
            double currentEURFrom = accountFrom.getBalanceEUR();

            if (currentEURFrom >= value + value * commissionEUR) {
                accountFrom.setBalanceEUR(currentEURFrom - (value * commissionEUR) - value);
                double currentUSDTo = accountTo.getBalanceUSD();
                accountTo.setBalanceUSD(value * exchangeRateEurUsd + currentUSDTo);
                operation.setCommissionEUR(value * commissionEUR);
            } else {
                return "operations/notEnoughFunds";
            }


        } else if (operationType.getId() == 1 && currencyFrom == 2 && currencyTo == 3) {

            double exchangeRateEurPln = 4.61;
            double currentEURFrom = accountFrom.getBalanceEUR();

            if (currentEURFrom >= value + value * commissionEUR) {
                accountFrom.setBalanceEUR(currentEURFrom - (value * commissionEUR) - value);
                double currentPLNTo = accountTo.getBalancePLN();
                accountTo.setBalancePLN(value * exchangeRateEurPln + currentPLNTo);
                operation.setCommissionEUR(value * commissionEUR);
            } else {
                return "operations/notEnoughFunds";
            }


        } else if (operationType.getId() == 1 && currencyFrom == 3 && currencyTo == 1) {

            double exchangeRatePlnUsd = 1 / 4.38;
            double currentPLNFrom = accountFrom.getBalancePLN();

            if (currentPLNFrom >= value + value * commissionPLN) {
                accountFrom.setBalancePLN(currentPLNFrom - (value * commissionPLN) - value);
                double currentUSDTo = accountTo.getBalanceUSD();
                accountTo.setBalanceUSD(value * exchangeRatePlnUsd + currentUSDTo);
                operation.setCommissionPLN(value * commissionPLN);
            } else {
                return "operations/notEnoughFunds";
            }


        } else if (operationType.getId() == 1 && currencyFrom == 3 && currencyTo == 2) {

            double exchangeRatePlnEur = 1 / 4.61;
            double currentPLNFrom = accountFrom.getBalancePLN();

            if (currentPLNFrom >= value + value * commissionPLN) {
                accountFrom.setBalancePLN(currentPLNFrom - (value * commissionPLN) - value);
                double currentEURTo = accountTo.getBalanceEUR();
                accountTo.setBalanceEUR(value * exchangeRatePlnEur + currentEURTo);
                operation.setCommissionPLN(value * commissionPLN);
            } else {
                return "operations/notEnoughFunds";
            }


        }

        //transfer
        else if (operationType.getId() == 1) {
            switch (currencyFrom) {
                case 1 -> {
                    double currentUSDFrom = accountFrom.getBalanceUSD();
                    if (currentUSDFrom >= value + (value * commissionUSD)) {
                        accountFrom.setBalanceUSD(currentUSDFrom - value);
                        double currentUSDTo = accountTo.getBalanceUSD();
                        accountTo.setBalanceUSD(value + currentUSDTo);
                        operation.setCommissionUSD(value * commissionUSD);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
                case 2 -> {
                    double currentEURFrom = accountFrom.getBalanceEUR();
                    if (currentEURFrom >= value + value * commissionEUR) {
                        accountFrom.setBalanceEUR(currentEURFrom - value);
                        double currentEURTo = accountTo.getBalanceEUR();
                        accountTo.setBalanceEUR(value + currentEURTo);
                        operation.setCommissionEUR(value * commissionEUR);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
                case 3 -> {

                    double currentPLNFrom = accountFrom.getBalancePLN();
                    if (currentPLNFrom >= value + value * commissionPLN) {
                        accountFrom.setBalancePLN(currentPLNFrom - value);
                        double currentPLNTo = accountTo.getBalancePLN();
                        accountTo.setBalancePLN(value + currentPLNTo);
                        operation.setCommissionPLN(value * commissionPLN);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
            }
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
                    if (accountFrom.getBalanceUSD() >= value) {
                        double currentUSD = accountFrom.getBalanceUSD();
                        accountFrom.setBalanceUSD(currentUSD - value);
                        operation.setToAccount(accountFrom);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
                case 2 -> {
                    if (accountFrom.getBalanceEUR() >= value) {
                        double currentEUR = accountFrom.getBalanceEUR();
                        accountFrom.setBalanceEUR(currentEUR - value);
                        operation.setToAccount(accountFrom);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
                case 3 -> {
                    if (accountFrom.getBalancePLN() >= value) {
                        double currentPLN = accountFrom.getBalancePLN();
                        accountFrom.setBalancePLN(currentPLN - value);
                        operation.setToAccount(accountFrom);
                    } else {
                        return "operations/notEnoughFunds";
                    }
                }
            }
        }

        //exchange
        else if (operationType.getId() == 4 && currencyFrom == 1 && currencyTo == 2) {

            double currentEUR = accountFrom.getBalanceEUR();
            double exchangeRateUsdEur = 0.95;
            double currentUSD = accountFrom.getBalanceUSD();

            if (currentUSD >= value + value * commissionUSD) {
                operation.setToAccount(accountFrom);
                accountFrom.setBalanceUSD(currentUSD - value - (value * commissionUSD));
                accountTo.setBalanceEUR(currentEUR + value * exchangeRateUsdEur);
                operation.setCommissionUSD(value * commissionUSD);
            } else {
                return "operations/notEnoughFunds";
            }

        } else if (operationType.getId() == 4 && currencyFrom == 1 && currencyTo == 3) {
            double exchangeRateUsdPln = 4.38;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentPLN = accountFrom.getBalancePLN();

            if (currentUSD >= value + value * commissionUSD) {
                operation.setFromAccount(accountTo);
                accountFrom.setBalanceUSD(currentUSD - value - (value * commissionUSD));
                accountFrom.setBalancePLN(currentPLN + value * exchangeRateUsdPln);
                operation.setCommissionUSD(value * commissionUSD);
            } else {
                return "operations/notEnoughFunds";
            }

        } else if (operationType.getId() == 4 && currencyFrom == 2 && currencyTo == 1) {
            double exchangeRateEurUsd = 1 / 0.95;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentEUR = accountFrom.getBalanceEUR();

            if (currentEUR >= value + value * commissionEUR) {
                operation.setFromAccount(accountTo);
                accountFrom.setBalanceEUR(currentEUR - value - (value * commissionEUR));
                accountFrom.setBalanceUSD(currentUSD + value * exchangeRateEurUsd);
            } else {
                return "operations/notEnoughFunds";
            }

        } else if (operationType.getId() == 4 && currencyFrom == 3 && currencyTo == 1) {
            double exchangeRatePlnUsd = 1 / 4.38;
            double currentUSD = accountFrom.getBalanceUSD();
            double currentPLN = accountFrom.getBalancePLN();

            if (currentPLN >= value + value * commissionPLN) {
                operation.setFromAccount(accountTo);
                accountFrom.setBalancePLN(currentPLN - value - (value * commissionPLN));
                accountFrom.setBalanceUSD(currentUSD + value * exchangeRatePlnUsd);
            } else {
                return "operations/notEnoughFunds";
            }

        } else if (operationType.getId() == 4 && currencyFrom == 2 && currencyTo == 3) {
            double exchangeRateEurPln = 4.61;
            double currentEUR = accountFrom.getBalanceEUR();
            double currentPLN = accountFrom.getBalancePLN();

            if (currentEUR >= value + value * commissionEUR) {
                operation.setFromAccount(accountTo);
                accountFrom.setBalanceEUR(currentEUR - value - (value * commissionEUR));
                accountFrom.setBalancePLN(currentPLN + value * exchangeRateEurPln);
            } else {
                return "operations/notEnoughFunds";
            }

        } else if (operationType.getId() == 4 && currencyFrom == 3 && currencyTo == 2) {
            double exchangeRatePlnEur = 1 / 4.61;
            double currentPLN = accountFrom.getBalancePLN();
            double currentEUR = accountFrom.getBalanceEUR();

            if (currentPLN >= value + value * commissionPLN) {
                operation.setFromAccount(accountTo);
                accountFrom.setBalancePLN(currentPLN - value - (value * commissionPLN));
                accountFrom.setBalanceEUR(currentEUR + value * exchangeRatePlnEur);
            } else {
                return "operations/notEnoughFunds";
            }

        }


        operationHistoryRepository.save(operation);
        return "redirect:all";
    }

}
