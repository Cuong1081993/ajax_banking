package com.example.controller;

import com.example.model.Customer;
import com.example.service.customer.ICustomerService;
import com.example.service.deposit.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired

    private IDepositService depositService;

    @GetMapping
    public String showListPage(Model model){

        List<Customer> customers = customerService.findAllByDeletedIsFalse();
        model.addAttribute("customers", customers);

        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        model.addAttribute("customer", new Customer());

        return "customer/create";
    }

}
