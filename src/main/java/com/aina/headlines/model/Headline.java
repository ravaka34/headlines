package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Headline extends HasId {
    @ManyToOne
    @JoinColumn(name = "headline_type_id")
    private HeadlineType headlineType;
    private String title;
    private String picture;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date1;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date2;

    private String place;
    private String body;

    @Transient
    private CommonsMultipartFile file;

    @Transient
    private Integer type;

    @Override
    public String toString() {
        return "title : "+title+"/date1 :"+date1+" /date2 :"+date2;
    }
}
