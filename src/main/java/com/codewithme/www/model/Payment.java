package com.codewithme.www.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
	 @Id
	 @GeneratedValue(strategy = GenerationType.UUID)
	  private UUID id;
	  private int userId;
	  private String razorpayOderId;
	  private String razorpayPaymentId;
	  private String RazorpaySignature;
      private Integer amount;
      private String currency;
      private String planType;
      @Builder.Default
      private String Status = "created";
      private String receipt;
      @CreatedDate
      private LocalDateTime createdAt;
      @LastModifiedDate
      private LocalDateTime updatedat;
}
