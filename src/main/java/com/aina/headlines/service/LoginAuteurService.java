package com.aina.headlines.service;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.exception.LoginNotFound;
import com.aina.headlines.model.Login;
import com.aina.headlines.model.LoginAuteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAuteurService {

    @Autowired
    HibernateDao dao;

    public boolean login(LoginAuteur login) throws LoginNotFound {
        if (dao.findWhere(login).size() == 0){
            throw  new LoginNotFound();
        }
        return true;
    }
}
