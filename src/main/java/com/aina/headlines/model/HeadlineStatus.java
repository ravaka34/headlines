package com.aina.headlines.model;

import com.aina.headlines.model.common.HasName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "headline_status")
@Getter
@Setter
public class HeadlineStatus extends HasName {
}
