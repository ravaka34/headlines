package com.aina.headlines.service;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.model.Headline;
import com.aina.headlines.model.HeadlineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.List;

@Service
public class HeadlineService {

    @Autowired
    private HibernateDao dao;

    public void create(Headline headline, String dirPath) throws IOException {
        System.out.println(headline);
        headline.setPicture(uploadImage(dirPath, headline.getFile()));
        HeadlineType type = new HeadlineType();
        type.setId(headline.getType());
        headline.setHeadlineType(type);
        dao.create(headline);
    }

    private String uploadImage(String path, CommonsMultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        System.out.println(path+"/"+filename);
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                new File(path + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();
        return filename;
    }

    public List<Headline> getAll() {
        return dao.findAll(Headline.class);
    }

    public List<Headline> search(String word, int offset, int size){
        return dao.personalFind(Headline.class, offset, size, word);
    }
    
    public Long count (String word){
        return dao.personalCount(Headline.class, word);
    }
}
