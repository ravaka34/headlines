package com.aina.headlines.service;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.model.Headline;
import com.aina.headlines.model.HeadlinePublie;
import com.aina.headlines.model.HeadlineStatus;
import com.aina.headlines.model.HeadlineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class HeadlineService {

    @Autowired
    private HibernateDao dao;

    public void create(Headline headline, String dirPath) throws IOException {
        headline.setPicture(uploadImage(dirPath, headline.getFile()));
        HeadlineType type = new HeadlineType();
        type.setId(headline.getType());
        headline.setDateCreation(new Date(System.currentTimeMillis()));
        headline.setHeadlineStatusId(1);
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

    public List<Headline> search(String word, Integer status, int offset, int size){
        Headline headline = new Headline();
        if(status != 0){
            headline.setHeadlineStatusId(status);
        }
        return dao.personalFind(headline, offset, size, word);
    }
    
    public Long count (String word, Integer status){
        Headline headline = new Headline();
        if(status != 0){
            headline.setHeadlineStatusId(status);
        }
        return dao.personalCount(headline, word);
    }

    public boolean publish(Integer id) {
        Headline headline = dao.findById(Headline.class, id);
        headline.setHeadlineStatusId(2);
        dao.create(headline);
        return true;
    }

    public List<HeadlinePublie> searchFo(String word, int offset, int size) {
        return dao.personalFind(HeadlinePublie.class, offset, size, word);
    }

    public Long countFo(String word) {
        return dao.personalCount(HeadlinePublie.class, word);
    }

    public boolean setDatePublication(Integer id, LocalDateTime datePublication) {
        Headline headline = dao.findById(Headline.class, id);
        Instant instant = datePublication.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        headline.setDatePublication(date);
        dao.create(headline);
        return true;
    }
}
