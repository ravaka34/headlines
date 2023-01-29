package com.aina.headlines.service;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.exception.LoginNotFound;
import com.aina.headlines.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class LoginService {
    @Autowired
    HibernateDao dao;

    public boolean login(Login login) throws LoginNotFound {
        if (dao.findWhere(login).size() == 0){
            throw  new LoginNotFound();
        }
        return true;
    }
}
