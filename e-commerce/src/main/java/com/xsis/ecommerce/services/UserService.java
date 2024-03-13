package com.xsis.ecommerce.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xsis.ecommerce.entities.Token;
import com.xsis.ecommerce.entities.User;
import com.xsis.ecommerce.repositories.TokenRepository;
import com.xsis.ecommerce.repositories.UserRepository;
import com.xsis.ecommerce.utils.CustomException;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private TokenRepository tr;

    public Boolean isPhoneNumberExists(String phone_number) {
        return ur.isPhoneNumberExists(phone_number);
    }

    public Boolean isUsernameExists(String username) {
        return ur.isUsernameExists(username);
    }

    public User createUser(User user) throws CustomException {
        // Validate username
        if (ur.isUsernameExists(user.getUsername())) {
            throw new CustomException(409, "username already exists");
        }

        // Validate phone number
        if (ur.isPhoneNumberExists(user.getPhone_number() + "")) {
            throw new CustomException(409, "phone number already exists");
        }

        // Finalize
        return ur.save(user);
    }

    public Token login(String username, String password) throws CustomException {
        User user = ur.login(username, password);

        if (user != null) {
            Token token = new Token();
            token.setId_user(user.getId_user());
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                String generatedToken = md.digest(user.getUsername().getBytes()).toString().substring(3);
                token.setToken(generatedToken);
            } catch (NoSuchAlgorithmException e) {
                token.setToken(Math.random() * 100 + user.getUsername());
            }
            tr.save(token);
            return token;
        } else {
            throw new CustomException(401, "username and password don't match");
        }
    }

    public User getUserByToken(String token) {
        return ur.getUserByToken(token);
    }
}
