package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "headline_type")
@Getter
@Setter
public class HeadlineType extends HasId {
    private String name;
}
