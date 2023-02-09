package com.aina.headlines.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class HasName extends HasId {
    private String name;
}
