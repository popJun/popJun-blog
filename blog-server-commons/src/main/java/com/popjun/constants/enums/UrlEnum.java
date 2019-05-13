package com.popjun.constants.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum UrlEnum {
    INTERFACE_URL("http://cas.client1.com:9528"),
    CAS_SERVER_URL("https://cas.server.com:8443");
    private String url;
}
