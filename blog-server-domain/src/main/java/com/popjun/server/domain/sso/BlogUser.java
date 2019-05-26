
package com.popjun.server.domain.sso;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name = "blog_user")
@Data
public class BlogUser implements Serializable {
    private static final long serialVersionUID = 557941889819701152L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "login_name")
    private String loginName;
    private String pswd;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    private Long status;
}
