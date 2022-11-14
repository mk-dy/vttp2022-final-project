package vttp.csf.server.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import vttp.csf.server.models.Address;
import vttp.csf.server.models.Order;
import vttp.csf.server.models.OrderItem;
import vttp.csf.server.models.PaymentInfo;
import vttp.csf.server.models.Purchase;
import vttp.csf.server.models.PurchaseResponse;
import vttp.csf.server.models.User;
import vttp.csf.server.repository.CheckoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepo;
    
    @Value("${stripe.secret.key}") 
    String stripeSecretKey;

    public CheckoutServiceImpl() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // ORDER
        // =====
        // 1) Address
        // 2) Order
        // 3) OrderItem

        // User
        User user = purchase.getUser();
        // Order
        Order order = purchase.getOrder();
        // Address
        Address shipAddress = new Address();
        shipAddress = purchase.getShippingAddress();
        // Store address into database
        boolean addressCheck = checkoutRepo.addAddress(shipAddress);
        
        // get address Id
        Integer addressId = checkoutRepo.getAddressId();
        shipAddress.setId(addressId);
        System.out.println(">>> address id check: " +addressId);

        // Store order into database
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        Date dateCreated = new Date();
        order.setDateCreated(dateCreated);
        order.setUser(user);
        order.setShippingAddress(shipAddress);
        boolean orderCheck = checkoutRepo.addOrder(order);

        // get order Id
        Integer orderId = checkoutRepo.getOrderId();
        order.setId(orderId);
        System.out.println(">>> check my order id: " +orderId);
        
        // order id needs to go into orderItems
        orderItems.forEach(item -> item.getOrder().setId(orderId));
        for (OrderItem item: orderItems) {
            System.out.println(item.getOrder().getId());
        }

        // Store order items into database
        // checkoutRepo.addOrderItem(orderItems); // KEEP GETTING LOCKOUT IF I USE BATCH UPDATES IN PREPARED STATEMENTS 
        checkoutRepo.insertOrderItems(orderItems);

        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "isaac shop purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}









