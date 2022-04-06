package com.scz.BillingManagement.Repository;

import com.scz.BillingManagement.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
