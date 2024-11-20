package com.edmondsinc.wishlist.util;

import com.edmondsinc.wishlist.model.User;
import com.edmondsinc.wishlist.model.dto.CreateUserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class EncryptionUtil {

    public static void encyptPW(User user, CreateUserDto createUserDto){
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(createUserDto.getUnhashedPw());
        user.setHashedPw(encodedPassword);
    }
}
