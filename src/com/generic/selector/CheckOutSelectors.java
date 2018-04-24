package com.generic.selector;


public class CheckOutSelectors
{
	
	// Payment Type
	public static final String PaymentTypeSelection_CARD = "PaymentTypeSelection_CARD";
	public static final String PaymentTypeSelection_ACCOUNT = "PaymentTypeSelection_ACCOUNT";
	public static final String PurchaseOrderNumber = "text form-control";
	public static final String PaymentType_continue_button = "btn btn-primary btn-block checkout-next";
	public static final String PaymentTypeCostCenterSelect = "costCenterSelect";
	
	
	//Shipping Address
	//done
	public static final String addresses = "dwfrm_singleshipping_addressList";
	public static final String addAddressChekbx = "Add New Address";
	public static final String firstName = "dwfrm_singleshipping_shippingAddress_addressFields_firstName";
	public static final String lastName = "dwfrm_singleshipping_shippingAddress_addressFields_lastName";
	public static final String  address= "dwfrm_singleshipping_shippingAddress_addressFields_address1";
	public static final String city = "dwfrm_singleshipping_shippingAddress_addressFields_city";
	public static final String state = "dwfrm_singleshipping_shippingAddress_addressFields_states_state";
	public static final String zipcode = "dwfrm_singleshipping_shippingAddress_addressFields_postal";
	public static final String countery = "dwfrm_singleshipping_shippingAddress_addressFields_country";
	public static final String phone = "dwfrm_singleshipping_shippingAddress_addressFields_phone";
	
	public static final String emailAddress = "email required textfield textmargin form-control";
	public static final String addressBookBtn = "css,section>div>div>div>div>div>div>button";
	public static final String submitShippingAddressBtn = "btngreen1 btn-primary-green";
	public static final String CheckSaveAddress = "SaveAddress";
	public static final String title = "address.title";
	public static final String orderTotalShippingAddress = "test_cart_totalPrice_label_$";
	public static final String orderSubTotalShippingAddress = "test_Order_Totals_Subtotal_$";
	public static final String fnameError = "firstName.errors";
	public static final String lnameError = "lastName.errors";
	public static final String address1Error = "line1.errors";
	public static final String cityError = "townCity.errors";
	public static final String postcodeEerror = "postcode.errors";
	public static final String alertInfo = "alert alert-danger alert-dismissable";
	public static final String titleError = "titleCode.errors";
	public static final String countryError = "countryIso.errors";
	
	//shipping method
	//done
	public static final String shippingMethod = "shipping-method-1POS_";
	public static final String continueToPaymentMethodBtn = "dwfrm_singleshipping_shippingAddress_save";
	public static final String ShippingMail = "dwfrm_singleshipping_shippingAddress_addressFields_friendshipRewards_email";
	
	public static final String addshippingMethodBtn = "change_mode_button";
	public static final String submitShippingMethodbtn = "use_this_delivery_method";
	public static final String orderTotalShippingMethod = "test_cart_totalPrice_label_$";
	public static final String orderSubTotalShippingMethod = "test_Order_Totals_Subtotal_$";
	
	//Payment info
	//done 
	public static final String selectFirstPayment = "creditCardList";
	public static final String CVC = "new_dwfrm_billing_paymentMethods_creditCard_cvn";
	public static final String orderReviewBtn = "Continue to review Order";
	public static final String cardtype = "dwfrm_billing_paymentMethods_creditCard_type";
	public static final String cardHolder = "new_dwfrm_billing_paymentMethods_creditCard_owner";
	public static final String cardNumber = "new_dwfrm_billing_paymentMethods_creditCard_number";
	public static final String expireDay = "new_dwfrm_billing_paymentMethods_creditCard_expiration_month";
	public static final String expireYear = "new_dwfrm_billing_paymentMethods_creditCard_expiration_year";
	
	public static final String submitPayment = "lastButtonInTheForm";
	public static final String checkSavePayment = "SaveDetails";
	public static final String addPaymentBtn = "css,section>div>div>div>div>div>a.linkarrow";
	public static final String checkSame = "differentAddress";
	public static final String savedPaymentBtn = "btn btn-default btn-block js-saved-payments";
	public static final String orderTotalPymentInfo = "test_cart_totalPrice_label_$";
	public static final String orderShippingPymentInfo = "test_Order_Totals_Delivery_$";
	public static final String orderSubtotalPymentInfo = "test_Order_Totals_Subtotal_$";
	public static final String cardTypeError = "card_cardType.errors";
	public static final String accountNumberError = "card_accountNumber.errors";
	public static final String expirationMonthError = "card_expirationMonth.errors";
	public static final String expirationYearError = "card_expirationYear.errors";
	public static final String cvNumberError = "card_cvNumber.errors";
	public static final String billToCountryError = "billTo_country.errors";
		
	//summary - review / place order
	//done-cbk
	public static final String summaryTotal = "order-subtotal";
	public static final String placeOrderBtn = "submit";
	
	public static final String shippingCost = "test_Order_Totals_Delivery_$";
	public static final String acceptTerm = "Terms1";
	public static final String orderTotalOrderSumary = "test_cart_totalPrice_label_$";
	
	//Order confirmation
	//done-cbk
	public static final String orderConfirmationSubtotal = "order-subtotal";
	public static final String orderId = "order-number";
	
	public static final String orderConfirmationTotal = "test_orderTotal_totalPrice_label_$";
	public static final String orderConfirmationShippingCost = "test_orderTotal_devlieryCost_label_$";
	public static final String orderconfirmationBillingAddress = "col-sm-6 col-md-4 order-billing-address";
	public static final String orderconfirmationshippingAddress = "order-ship-to";
	
	//B2B Order confirmation
	public static final String B2BorderConfirmationTotal = "text-right totals";
	public static final String B2BorderconfirmationBillingAddress = "col-sm-6 order-billing-address";
	public static final String B2BorderconfirmationshippingAddress = "col-md-5 order-ship-to";
	
	//Guest order
	//done
	public static final String ShippingMethodType = "is-test";
	public static final String guestCheckoutButton = "dwfrm_login_unregistered";
	public static final String choseToLogin = "Login For Faster Checkout";
	public static final String returningcustomerUsername = "dwfrm_login_username";
	public static final String returningcustomerPassword = "dwfrm_login_password";
	public static final String returningcustomerloginBtn = "spc-login-btn";
	
	public static final String guestMail = "guest.email";
	public static final String guestConfirmationMail = "confirmGuestEmail form-control";
	public static final String guestCreateAccButton = "test_guest_Register_button_$";
	public static final String guestCreateAccOtpin = "consentForm.consentGiven1";
	public static final String guestCreateAccCPwd = "guest.checkPwd";
	public static final String guestCreateAccPwd = "password";
	
	
	
	
	
}
