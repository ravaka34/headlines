package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class Headline extends HasId {
    @ManyToOne
    private HeadlineType headlineType;
    private String title;
    private String picture;
    private Date date1;
    private Date date2;
    private String place;
    private String body;
}
