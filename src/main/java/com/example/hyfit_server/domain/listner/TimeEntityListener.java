package com.example.hyfit_server.domain.listner;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class TimeEntityListener {
    @PrePersist // insert 연산이 실행될때, 같이 실행.
    public void setCreatedAtAndUpdatedAt(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate // update 연산이 실행될때, 같이 실행.
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}