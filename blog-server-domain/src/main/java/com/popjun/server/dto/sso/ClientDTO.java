package com.popjun.server.dto.sso;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientDTO implements Serializable{
    private static final long serialVersionUID = 5579418898197011514L;
    private Integer id;
    private String name;
    private String serivceId;

    public ClientDTO(Long id, String name, String serivceId) {
        this.id = id.intValue();
        this.name = name;
        this.serivceId = serivceId;
    }
}
