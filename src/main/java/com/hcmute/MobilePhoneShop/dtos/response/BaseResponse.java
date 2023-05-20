package com.hcmute.MobilePhoneShop.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {
    @NonNull
    private Boolean status;

    @NonNull
    private String message;

    public BaseResponse(){
        this.status = true;
        this.message = "";
    }
}
