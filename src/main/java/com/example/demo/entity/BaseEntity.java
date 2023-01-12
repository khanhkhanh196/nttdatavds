package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass

public class BaseEntity {

//    @Column(name="created_date")
//    private LocalDateTime createdDate;
//    @Column(name="updated_date")
//    private LocalDateTime updatedDate;
//
//    @PrePersist
//    public void setCreatedDate(){
//        long date = new Date().getTime();
//        LocalDateTime createdDate = LocalDateTime(date);
//        this.createdDate = createdDate;
//    }
//
//    @PreUpdate
//    public void setUpdatedDate(){
//        long date = new Date().getTime();
//        LocalDateTime updatedDate = new LocalDateTime(date);
//        this.updatedDate = updatedDate;
//    }
}

 