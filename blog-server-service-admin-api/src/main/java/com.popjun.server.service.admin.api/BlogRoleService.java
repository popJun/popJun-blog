package com.popjun.server.service.admin.api;


import java.util.List;

public interface BlogRoleService {
    Boolean checkRoleByUserId(Long userId);
    List<String> getRoleNameByUserId(Long userId);
}
