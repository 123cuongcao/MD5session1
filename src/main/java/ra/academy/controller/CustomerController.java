package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.dto.CustomerRequest;
import ra.academy.model.Customer;
import ra.academy.repository.CustomerRepositoryImpl;
import ra.academy.repository.ICustomerRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping
public class CustomerController {

    @Autowired
    ICustomerRepository repository;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("list", repository.findAll());
        return "/listCustomer";
    }

    @RequestMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("customer", new CustomerRequest());
        return "/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String doAdd(@ModelAttribute("customer") CustomerRequest cr, Model model) {
        Customer customer = new Customer( cr.getName(), cr.getPhoneNumber(), cr.getBirthday(), cr.isSex());
        repository.save(customer);
        model.addAttribute("customer", cr);
        return "redirect:/home";
    }

    @RequestMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") long id, Model model) {
        repository.remove(id);
        return "/listCustomer";
    }
}
