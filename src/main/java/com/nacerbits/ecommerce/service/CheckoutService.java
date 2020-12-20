package com.nacerbits.ecommerce.service;

import com.nacerbits.ecommerce.dto.Purchase;
import com.nacerbits.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
