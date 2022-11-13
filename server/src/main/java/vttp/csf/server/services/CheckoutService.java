package vttp.csf.server.services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import vttp.csf.server.models.PaymentInfo;
import vttp.csf.server.models.Purchase;
import vttp.csf.server.models.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
