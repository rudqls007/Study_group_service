package com.study.account;


import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
/* 현재 사용자가 만약에 익명 사용자다 로그인하지 않고 접근한 사용자다 로그인하지 않고
   어디에 접근했냐 모든 걸 다 허용하는 이런 페이지들을 접근할 때는 익명 사용자로 접근이 됨 */
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
public @interface CurrentUser {
}
