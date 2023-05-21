package com.hcmute.MobilePhoneShop.services.order;

import com.hcmute.MobilePhoneShop.dtos.request.RequestCheckoutDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;

public interface OrderService {
    BaseResponse checkout(RequestCheckoutDTO requestCheckoutDTO);
}
