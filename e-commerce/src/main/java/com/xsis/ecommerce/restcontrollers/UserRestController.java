package com.xsis.ecommerce.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.ecommerce.entities.Token;
import com.xsis.ecommerce.entities.User;
import com.xsis.ecommerce.services.UserService;
import com.xsis.ecommerce.utils.CustomException;
import com.xsis.ecommerce.utils.Resp;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private UserService us;

    @RequestMapping("/validate/phone_number")
    public Resp<Boolean> isPhoneNumberExists(@RequestParam("phone_number") String phone_number) {
        return new Resp<>(!us.isPhoneNumberExists(phone_number));
    }

    @RequestMapping("/validate/username")
    public Resp<Boolean> isUsername(@RequestParam("username") String username) {
        return new Resp<>(!us.isUsernameExists(username));
    }

    @RequestMapping("/create")
    public Resp<User> createUser(User user) {
        try {
            return new Resp<>(us.createUser(user));
        } catch (CustomException e) {
            return new Resp<>(e);
        }
    }

    @RequestMapping("/login")
    public Resp<Token> login(String username, String password) {
        try {
            return new Resp<>(us.login(username, password));
        } catch (CustomException e) {
            return new Resp<>(e);
        }
    }
}
