package com.nacerbits.ecommerce.dto;

import com.nacerbits.ecommerce.entity.Address;
import com.nacerbits.ecommerce.entity.Customer;
import com.nacerbits.ecommerce.entity.Order;
import com.nacerbits.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
