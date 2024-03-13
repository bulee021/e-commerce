package com.xsis.ecommerce.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.ecommerce.dto.LoginDTO;
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

    @PostMapping("/create")
    public Resp<User> createUser(@RequestBody User user) {
        System.out.println(user.getName() + " tries to register");
        try {
            return new Resp<>(us.createUser(user));
        } catch (CustomException e) {
            return new Resp<>(e);
        }
    }

    @PostMapping("/login")
    public Resp<Token> login(@RequestBody LoginDTO loginDTO) {
        try {
            return new Resp<>(us.login(loginDTO.getUsername(), loginDTO.getPassword()));
        } catch (CustomException e) {
            return new Resp<>(e);
        }
    }

    @RequestMapping("/getByToken")
    public Resp<User> getUserByToken(@RequestParam("token") String token) {
        return new Resp<>(us.getUserByToken(token));
    }
}
