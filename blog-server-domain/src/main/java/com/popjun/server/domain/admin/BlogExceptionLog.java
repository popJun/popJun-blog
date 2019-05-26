package com.popjun.server.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "blog_exception_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogExceptionLog implements Serializable {
    private static final long serialVersionUID = 5579418898197011512L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exception_json")
    private String exceptionJson;

    @Column(name = "exception_message")
    private String exceptionMessage;

    @Column(name = "create_time")
    private Date createTime;


}