package com.example.service.customer;

import com.example.model.Customer;
import com.example.model.Deposit;
import com.example.model.LocationRegion;
import com.example.model.Transfer;
import com.example.model.dto.CustomerDTO;
import com.example.repository.CustomerRepository;
import com.example.repository.DepositRepository;
import com.example.repository.LocationRegionRepository;
import com.example.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class CustomerServiceIpm implements ICustomerService {

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private TransferRepository transferRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {

        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);

        customer.setLocationRegion(locationRegion);

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> findAllByDeletedIsFalse() {
        return customerRepository.findAllByDeletedIsFalse();
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse() {
        return customerRepository.findAllCustomerDTOByDeletedIsFalse();
    }

    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return customerRepository.findAllByIdNot(id);
    }

    @Override
    public void deposit(Customer customer, Deposit deposit) {

        Long customerId = customer.getId();

        BigDecimal transactionAmount = deposit.getTransactionAmount();

        deposit.setCustomer(customer);
        depositRepository.save(deposit);

        customerRepository.incrementBalance(customerId, transactionAmount);
    }

    @Override
    public void transfer(Transfer transfer) {

        Long senderId = transfer.getSender().getId();
        BigDecimal transactionAmount = transfer.getTransactionAmount();

        customerRepository.deIncrementBalance(senderId, transactionAmount);

        Long recipient = transfer.getRecipient().getId();
        BigDecimal transferAmount = transfer.getTransferAmount();

        customerRepository.incrementBalance(recipient, transferAmount);

        transferRepository.save(transfer);
    }
}
