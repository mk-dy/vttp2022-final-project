// package vttp.csf.server.controllers;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.google.gson.Gson;
// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.checkout.Session;
// import com.stripe.param.checkout.SessionCreateParams;

// import vttp.csf.server.models.CheckoutPayment;

// @RestController
// @RequestMapping(path="/api")
// public class PaymentRESTController {
    
//     @Value("${stripe.public.key}")
//     private String stripePublicKey;
    
//     @Value("${stripe.secret.key}")
//     private String stripeSecretKey;

//     private static Gson gson = new Gson();

// 	@PostMapping(path="/payment")
// 	public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
// 		// We initilize stripe object with the api key
// 		// init();
//         Stripe.apiKey = stripeSecretKey;
// 		// We create a  stripe session parameters
// 		SessionCreateParams params = SessionCreateParams.builder()
// 				// We will use the credit card payment method 
// 				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
// 				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
// 				.setCancelUrl(
//                     payment.getCancelUrl())
// 				.addLineItem(
// 						SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
// 								.setPriceData(
// 										SessionCreateParams.LineItem.PriceData.builder()
// 												.setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
// 												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
// 														.builder().setName(payment.getName()).build())
// 												.build())
// 								.build())
// 				.build();
//   // create a stripe session
// 		Session session = Session.create(params);
// 		Map<String, String> responseData = new HashMap<>();
//     // We get the sessionId and we putted inside the response data you can get more info from the session object
// 		responseData.put("id", session.getId());
//       // We can return only the sessionId as a String
//         System.out.println(">>>>>> session: " + session.getId());
// 		return gson.toJson(responseData);
// 	}

//     // insert this into above post method after deploying to heroku to remove stripe invalid url issue
// 	public String replaceUrlsForDeployment(String previousUrl) {
//         String identifier = "http://localhost:4200";

//         // replace "http://localhost:4200" with heroku link
//         String url = previousUrl.replace(identifier, "http://localhost:4200");
//         return url;
//     }

// }
