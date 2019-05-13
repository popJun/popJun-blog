package com.popjun.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {
  SUCCESS(20000),ERROR(500);
  private Integer code;

}
