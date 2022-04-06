package com.scz.BillingManagement.Service;

import com.scz.BillingManagement.Model.Bill;
import com.scz.BillingManagement.Model.BillStatus;
import com.scz.BillingManagement.Repository.BillRepository;
import com.scz.BillingManagement.Repository.Filters.BillQueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillRegisterService {

    @Autowired
    private BillRepository billRepository;

    public void save(Bill bill) {
        try {
            billRepository.save(bill);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Date format invalid");
        }
    }

    public void delete(Long code) {
        billRepository.deleteById(code);
    }

    public String receive(Long code) {
        var bill = billRepository.getById(code);
        bill.setStatus(BillStatus.RECEIVED);
        billRepository.save(bill);
        return BillStatus.RECEIVED.getDescription();
    }

}
