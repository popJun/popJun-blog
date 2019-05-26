package com.popjun.server.domain.sso;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "blog_role")
@Data
public class BlogRole implements Serializable{
    private static final long serialVersionUID = 5579418898197011511L;
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色类型
     */
    @Column(name = "role_type")
    private String roleType;

}