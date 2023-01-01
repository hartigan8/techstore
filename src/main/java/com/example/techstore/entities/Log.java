package com.example.techstore.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;

import lombok.Data;

@Entity
@Data
@Table(name = "pglog")
//@Subselect("select * from pglog")
public class Log {
    private Date logTime;
    private String userName;
    private String databaseName;
    private String processId;
    private String connectionFrom;

    @EmbeddedId
    private LogKey sessionLine;
    

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
