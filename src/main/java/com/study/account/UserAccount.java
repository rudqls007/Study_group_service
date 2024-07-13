package com.study.account;

import com.study.domain.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
@Getter @Setter
public class UserAccount extends User {

    /* anonymous가 아닌 account 프로퍼티가 나오면 account의 정보를 꺼내게 됨. */

    private Account account;

    public UserAccount(Account account) {
        /* super 키워드를 통해 부모 클래스인 User의 생성라를 호출하여 초기화함.
         *  이름, 패스워드, 사용자 권한(USER) */
        super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        /* 전달받은 Account 객체를 account 필드에 저장함. */
        this.account = account;

    }
}