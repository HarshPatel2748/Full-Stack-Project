package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.CreatePaymentDTO;
import com.marketplace.marketplace_backend.dto.PaymentResponseDTO;
import com.marketplace.marketplace_backend.dto.PaymentVerificationDTO;
import com.marketplace.marketplace_backend.entity.Order;
import com.marketplace.marketplace_backend.entity.OrderStatus;
import com.marketplace.marketplace_backend.entity.Payment;
import com.marketplace.marketplace_backend.entity.PaymentStatus;
import com.marketplace.marketplace_backend.repository.OrderRepository;
import com.marketplace.marketplace_backend.repository.PaymentRepository;
import com.marketplace.marketplace_backend.service.PaymentService;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;


    @Override
    public PaymentResponseDTO createPayment(CreatePaymentDTO dto) throws Exception {
        Order order = orderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        JSONObject options = new JSONObject();
        options.put("amount", order.getTotalAmount() * 100);
        options.put("currency", "INR");
        options.put("receipt", "order_" + order.getId());

        com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);

        Payment payment = Payment.builder()
                .order(order)
                .amount(order.getTotalAmount())
                .razorpayOrderId(razorpayOrder.get("id").toString())
                .status(PaymentStatus.CREATED)
                .build();

        paymentRepository.save(payment);

        return PaymentResponseDTO.builder()
                .razorpayOrderId(payment.getRazorpayOrderId())
                .amount(payment.getAmount())
                .currency("INR")
                .build();
    }


    @Override
    @Transactional
    public void verifyPayment(PaymentVerificationDTO dto) {
        Payment payment = paymentRepository
                .findAll()
                .stream()
                .filter(p -> p.getRazorpayOrderId().equals(dto.getRazorpayOrderId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String payLoad = dto.getRazorpayOrderId() + '|' + dto.getRazorpayPaymentId();

        try{
            boolean isValid = Utils.verifySignature(
                    payLoad,
                    dto.getRazorpaySignature(),
                    razorpayClient.getSecret()
            );

            if(!isValid){
                throw new RuntimeException("Invalid payment signature");
            }

            payment.setRazorpayPaymentId(dto.getRazorpayPaymentId());
            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);

            Order order = payment.getOrder();
            order.setOrderStatus(OrderStatus.PAID);

        } catch (Exception e) {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            throw new RuntimeException("Payment verification failed");
        }
    }
}
