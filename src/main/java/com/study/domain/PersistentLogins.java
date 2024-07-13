package com.study.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * User 를 상속 받은 Entity 객체를 찾아서
 * jdbc 클래스에 있는 스키마에 해당하는 스키마를 생성할 수 있도록 하는 역할을 함.
 *
 */
@Table(name = "persistent_logins")
@Entity
@Getter @Setter
public class PersistentLogins {


    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(name = "last_used", nullable = false, length = 64)
    private LocalDateTime lastUsed;
}
