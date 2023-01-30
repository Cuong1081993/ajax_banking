package com.example.service.transfer;

import com.example.model.Transfer;
import com.example.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceIpm implements ITransferService{
    @Autowired
    private TransferRepository transferRepository;


    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer save(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Transfer transfer) {

    }
}
