package com.asdf.shoppingmall.User.dto;

import com.asdf.shoppingmall.Global.Address;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponseDto {

    private String username;
    private String name;

    @Embedded
    private Address address;
}
