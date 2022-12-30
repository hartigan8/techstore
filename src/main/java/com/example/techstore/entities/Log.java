package com.example.techstore.entities;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Log {
    private Date logTime;
    private String userName;
    private String databaseName;
    private String processId;
    private String connectionFrom;
    private String sessionId;
    private Long sessionLineNum;
    private String commandTag;
    private Date sessionStartTime;
    private String virtualTransactionId;
    private Long transactionId;
    private String errorSeverity;
    private String sqlStateCode;
    private String message;
    private String detail;
    private String hint;
    private String internalQuery;
    private String internalQueryPos;
    private String context;
    private String query;
    private String queryPos;
    private String location;
    private String applicationName;
    private String backendType;
    private String leaderPid;
    private String queryId;
}
