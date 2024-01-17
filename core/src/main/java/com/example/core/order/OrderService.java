package com.example.core.order;

import com.example.core.member.Member;

public interface OrderService {
   Order createOrder(Long memberId, String itemName, int itemPrice);
}
