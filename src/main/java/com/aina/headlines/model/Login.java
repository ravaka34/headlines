package com.aina.headlines.model;

import com.aina.headlines.model.common.HasId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Login extends HasId {
    private String email;
    private String mdp;
}
