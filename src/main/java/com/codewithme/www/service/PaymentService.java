package com.codewithme.www.service;

import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.model.Payment;
import com.codewithme.www.model.user;
import com.codewithme.www.repostiory.PaymentRepository;
import com.codewithme.www.repostiory.UserRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final UserRepo userRepo;
    private final AuthService authService;
    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    @Value("${razorpay.key-secret}")
    private String razorpayKeySecret;
    
    public Payment createOrder(user user,String plantype) throws RazorpayException{
    	// intilaize the razorpay client
    	RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId,razorpayKeySecret);
    	// prepare the json object to pass the razorpay
    	int amount =99900;
    	String currency="INR";
    	String receipt="premium"+"_"+UUID.randomUUID().toString().substring(0,8);
    	JSONObject orderRequest = new JSONObject();
    	  orderRequest.put("amount", amount);
    	  orderRequest.put("currency", currency);
    	  orderRequest.put("receipt", receipt);
    	  
    	// call the razorpay api to create order
    	  Order razorpayOrder=razorpayClient.orders.create(orderRequest);
    	  String razorpayOrderId = razorpayOrder.get("id");
    	// save the detials 
    		Payment newPayment=Payment.builder()
    		       .userId(user.getId())
    		       .razorpayOderId(razorpayOrderId)
    		       .amount(amount)
    		       .currency(currency)
    		       .planType(plantype)
    		       .Status("created")
    		       .receipt(receipt)
    		       .build();
    		
    	// return the result 
    	return paymentRepo.save(newPayment);
    	
    }
    public boolean verifyPayment(String razorpayOderId,String razorpayPaymentId,String razorpaySignature)
    {
    	try {

            JSONObject attributes = new JSONObject();

            attributes.put("razorpay_order_id", razorpayOderId);
            attributes.put("razorpay_payment_id", razorpayPaymentId);
            attributes.put("razorpay_signature", razorpaySignature);

            boolean isValidSignature =
                    Utils.verifyPaymentSignature(
                            attributes,
                            razorpayKeySecret
                    );

            if (isValidSignature) {

                Payment payment = paymentRepo
                        .findByRazorpayOderId(razorpayOderId)
                        .orElseThrow(() ->
                                new RuntimeException("Payment not found"));

                payment.setRazorpayPaymentId(razorpayPaymentId);
                payment.setRazorpaySignature(razorpaySignature);
                payment.setStatus("paid");

                paymentRepo.save(payment);
                upgradeUserSubscription(payment.getUserId(),payment.getPlanType());

                return true;
            }

            return false;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
    	
    	private void upgradeUserSubscription(int userId, String planType) {

    	    user existingUser = userRepo.findById(userId)
    	            .orElseThrow(() ->
    	                    new UsernameNotFoundException("User not found"));

    	    existingUser.setSubscriptionPlan(planType);

    	    userRepo.save(existingUser);

    	    log.info("User {} upgraded to {} plan", userId, planType);
    	
    }
		public List<Payment> getUserPayments(user user) {
			 Authresponse authResponse=authService.getProfile(user);
			     return paymentRepo.findByUserIdOrderByCreatedAtDesc(authResponse.getId());
			 
		}
		public Payment getPaymentdetails(String id) {
			// TODO Auto-generated method stub
			return paymentRepo
                    .findByRazorpayOderId(id)
                    .orElseThrow(() ->
                            new RuntimeException("Payment not found"));
		}
    
}
