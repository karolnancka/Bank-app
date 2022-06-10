package bank.converter;




import bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;


public class AccountConverter implements Converter<String, Optional<bank.model.Account>> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<bank.model.Account> convert(String source) {
        return accountRepository.findById(Long.parseLong(source));
    }
}