package bank.controller;

import bank.model.Category;
import bank.model.Commission;
import bank.repository.CommissionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/commission")
public class CommissionController {

    private final CommissionRepository commissionRepository;

    public CommissionController(CommissionRepository commissionRepository) {
        this.commissionRepository = commissionRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        List<Commission> commissions = commissionRepository.findAll();
        model.addAttribute("commissions", commissions);
        return "/commissions/allCommissions";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("commission", new Commission());
        return "commissions/addCommission";
    }

    @PostMapping("/add")
    public String addCategory(@Valid final Commission commission, final BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            return "commissions/addCommission";
        }

        commissionRepository.save(commission);
        return "redirect:all";
    }
}
