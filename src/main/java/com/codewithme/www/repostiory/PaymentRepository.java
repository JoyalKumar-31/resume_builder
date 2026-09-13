package com.codewithme.www.repostiory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithme.www.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,UUID> {
      Optional<Payment> findByRazorpayOderId(String razorpayOderId);
      Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);
      List<Payment> findByUserIdOrderByCreatedAtDesc(int userId);
      List<Payment> findByStatus(String status);
      

}
