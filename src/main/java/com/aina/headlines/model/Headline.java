package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import com.aina.headlines.model.common.HeadlineBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;

@Entity(name = "headline")
@Getter
@Setter
public class Headline extends HeadlineBody {
    @Transient
    private CommonsMultipartFile file;

    @Transient
    private Integer type;

}
