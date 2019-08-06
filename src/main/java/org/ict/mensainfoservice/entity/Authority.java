package org.ict.mensainfoservice.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    public Authority(){}
    public Authority(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
