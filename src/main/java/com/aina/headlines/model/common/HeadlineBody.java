package com.aina.headlines.model.common;

import com.aina.headlines.model.HeadlineStatus;
import com.aina.headlines.model.HeadlineType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class HeadlineBody extends  HasId {
    @ManyToOne
    @JoinColumn(name = "headline_type_id")
    private HeadlineType headlineType;

    @Column(name = "headline_status_id")
    private Integer headlineStatusId;

    private String title;
    private String picture;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date1;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date2;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "date_publication")
    private Date datePublication;

    @Transient
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime  datePubHtml;

    private String place;
    private String body;
}
