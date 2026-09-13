package com.codewithme.www.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithme.www.model.Payment;
import com.codewithme.www.model.user;
import com.codewithme.www.service.PaymentService;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Payment")
@Slf4j
public class PaymentController {
	private final PaymentService paymentService;
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String,String> request,Authentication authentication)
    throws RazorpayException {
    	String planType = request.get("planType");
    	if(!"premium".equalsIgnoreCase(planType)) {
    		return ResponseEntity.badRequest().body(Map.of("meassgae","invalid")); 
    	}
    	user user=(user)authentication.getPrincipal();
    	Payment payment = paymentService.createOrder(user,planType);
    	Map<String,Object> response =Map.of(
    			"orderID",payment.getRazorpayOderId(),
    			"amount",payment.getAmount(),
    			"currency",payment.getCurrency(),
    			"receipt",payment.getReceipt());
    	return ResponseEntity.ok(response);
    	
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestBody Map<String, String> request,
            Authentication authentication) {

        String razorpayOrderId =
                request.get("razorpay_order_id");

        String razorpayPaymentId =
                request.get("razorpay_payment_id");

        String razorpaySignature =
                request.get("razorpay_signature");

        // Check payment details first
        if (Objects.isNull(razorpayOrderId)
                || Objects.isNull(razorpayPaymentId)
                || Objects.isNull(razorpaySignature)) {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message",
                            "Missing Razorpay payment details"
                    ));
        }

        // Verify Razorpay payment
        boolean isValid = paymentService.verifyPayment(
                razorpayOrderId,
                razorpayPaymentId,
                razorpaySignature
        );

        if (isValid) {

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "Payment verified successfully",
                            "status",
                            "success"
                    )
            );

        } else {

            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "message",
                            "Payment verification failed",
                            "status",
                            "failed"
                    ));
        }
    }
    @GetMapping("/history")
    public ResponseEntity<?> getPaymentHistory(Authentication authentication){
    	user user=(user)authentication.getPrincipal();
    	List<Payment> Payments = paymentService.getUserPayments(user);
    	return ResponseEntity.ok(Payments);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String id){
    	Payment paymentDetails=paymentService.getPaymentdetails(id);
    	return ResponseEntity.ok(paymentDetails);
    }
}
