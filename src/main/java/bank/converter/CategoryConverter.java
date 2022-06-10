package bank.converter;




import bank.repository.AccountRepository;
import bank.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;


public class CategoryConverter implements Converter<String, Optional<bank.model.Category>> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<bank.model.Category> convert(String source) {
        return categoryRepository.findById(Long.parseLong(source));
    }
}