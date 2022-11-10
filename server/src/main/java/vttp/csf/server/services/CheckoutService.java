package vttp.csf.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.models.Order;
import vttp.csf.server.models.PaymentInfo;
import vttp.csf.server.models.Purchase;
import vttp.csf.server.models.PurchaseResponse;
import vttp.csf.server.models.User;

@Service
public class CheckoutService {
    
    @Value("${stripe.secret.key}") 
    String stripeSecretKey;

    // public PurchaseResponse placeOrder(Purchase purchase) {

    //     Stripe.apiKey = stripeSecretKey;
    //     // retrieve the order info from dto
    //     Order order = purchase.getOrder();

    //     // generate tracking number
    //     String orderTrackingNumber = generateOrderTrackingNumber();
    //     order.setOrderTrackingNumber(orderTrackingNumber);

    //     // populate order with orderItems
    //     Set<FinalProduct> orderItems = purchase.getOrderItems();
    //     orderItems.forEach(item -> order.add(item));

    //     // populate order with billingAddress and shippingAddress
    //     order.setBillingAddress(purchase.getBillingAddress());
    //     order.setShippingAddress(purchase.getShippingAddress());

    //     // populate users with order
    //     User user = purchase.getUser();

    //     // check if this is an existing customer
    //     String theEmail = customer.getEmail();

    //     Customer customerFromDB = customerRepository.findByEmail(theEmail);

    //     if (customerFromDB != null) {
    //         // we found them ... let's assign them accordingly
    //         customer = customerFromDB;
    //     }

    //     customer.add(order);

    //     // save to the database
    //     customerRepository.save(customer);

    //     // return a response
    //     return new PurchaseResponse(orderTrackingNumber);
    // }

    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        Stripe.apiKey = stripeSecretKey;
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "isaac purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }
}
