package com.example.service.customer;

import com.example.model.Customer;
import com.example.model.Deposit;
import com.example.model.Transfer;
import com.example.model.dto.CustomerDTO;
import com.example.service.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {

    List<Customer> findAllByDeletedIsFalse();

    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();

    List<Customer> findAllByIdNot(Long id);

    void deposit(Customer customer, Deposit deposit);

    void transfer(Transfer transfer);
}
