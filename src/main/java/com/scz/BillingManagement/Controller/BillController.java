package com.scz.BillingManagement.Controller;

import com.scz.BillingManagement.Model.Bill;
import com.scz.BillingManagement.Model.BillStatus;
import com.scz.BillingManagement.Repository.BillRepository;
import com.scz.BillingManagement.Repository.Filters.BillQueryFilter;
import com.scz.BillingManagement.Service.BillRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillRegisterService billRegisterService;

    public static final String REGISTER_VIEW = "BillRegister";

    @RequestMapping("/new")
    public ModelAndView newBill(){
        ModelAndView modelAndView = new ModelAndView(REGISTER_VIEW);
        modelAndView.addObject(new Bill());
        return modelAndView;
    }

    @PostMapping
    public String saveBill(@Validated Bill bill, Errors errors, RedirectAttributes attributes){
        if (errors.hasErrors()){
            return REGISTER_VIEW;
        }
        try {
        billRegisterService.save(bill);
        attributes.addFlashAttribute("message", "New bill saved!!");
        return "redirect:/bills/new";
        } catch (DataIntegrityViolationException e){
            errors.rejectValue("dueDate", null, e.getMessage());
            return REGISTER_VIEW;
        }
    }

    @GetMapping
    public ModelAndView search() {
        List<Bill> allBills = billRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("BillsSearch");
        modelAndView.addObject("bills", allBills);
        return modelAndView;
    }

    @GetMapping("{code}")
    public ModelAndView edit(@PathVariable("code") Bill bill) {
        ModelAndView modelAndView = new ModelAndView(REGISTER_VIEW);
        modelAndView.addObject(bill);
        return modelAndView;
    }

    @DeleteMapping("{code}")
    public String delete (@PathVariable Long code, RedirectAttributes attributes) {
        billRegisterService.delete(code);
        attributes.addFlashAttribute("message", "Bill removed!!");
        return "redirect:/bills";
    }

    @PutMapping("{code}/receive")
    public @ResponseBody String receive (@PathVariable Long code){
       return billRegisterService.receive(code);
    }

    @ModelAttribute("allBillStatus")
    public List<BillStatus> allBillStatus(){
        return Arrays.asList(BillStatus.values());
    }
}
