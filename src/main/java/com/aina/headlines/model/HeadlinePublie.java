package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import com.aina.headlines.model.common.HeadlineBody;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity (name = "v_headline_publie")
@Getter
@Setter
public class HeadlinePublie extends HeadlineBody {
}
