package com.example.techstore.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class LogKey implements Serializable {
    private String sessionId;
    private Long sessionLineNum;
}
