package com.popjun.server.domain.sso;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "blog_user_role")
@Data
public class BlogUserRole implements Serializable {
    private static final long serialVersionUID = 5579418898197011513L;
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

}