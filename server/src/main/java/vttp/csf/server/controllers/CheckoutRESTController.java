package vttp.csf.server.controllers;

import vttp.csf.server.models.PaymentInfo;
import vttp.csf.server.models.Purchase;
import vttp.csf.server.models.PurchaseResponse;
import vttp.csf.server.services.CheckoutService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutRESTController {

    @Autowired
    private CheckoutService checkoutSvc;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        System.out.println(">>> check if purchase has entered" + purchase.toString());
        PurchaseResponse purchaseResponse = checkoutSvc.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        System.out.println(">>> check if payment info has entered" + paymentInfo.toString());
        Stripe.apiKey = stripeSecretKey;
        PaymentIntent paymentIntent = checkoutSvc.createPaymentIntent(paymentInfo);

        String paymentStr = paymentIntent.toJson();
        System.out.println(">>>> check paymentintent: " + paymentStr);

        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
    }
}









