package com.asdf.shoppingmall.User.dto;

import com.asdf.shoppingmall.Global.Address;
import lombok.Getter;

@Getter
public class SignupRequest {

    private String username;
    private String password;
    private String name;
    private Address address;

}
