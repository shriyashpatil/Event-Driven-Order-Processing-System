package com.event_driven.inventory_service.util;

import java.util.Objects;

public class KeyUtil {

    public static String convertToKey(Long productId,Long customerId,Long orderId){
        int hash = Objects.hash(productId,customerId,orderId);
        return String.valueOf(hash);
    }


}
