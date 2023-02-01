package com.aina.headlines.service;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.model.HeadlineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadlineTypeService {

    @Autowired
    HibernateDao dao;

    public List<HeadlineType> getAll(){
        return dao.findAll(HeadlineType.class);
    }
}
