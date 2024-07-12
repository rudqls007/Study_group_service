스터디 모임 관리 서비스

## 🔧 Stacks

<div align="center">
<h3>Language</h3>
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<br>
<h3>Enviroment</h3>
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">  
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">    
<br>
<h3>Development</h3>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">  
<br>
<h3>DB</h3>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/postgreSQL-4169E1?style=for-the-badge&logo=postgreSQL&logoColor=white">
<br>
<h3>Communication</h3>
<br>
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

</div>

## ➤ Navigation
- [시작 가이드](#시작-가이드)
- [프로젝트 소개](#프로젝트-소개)
- [프로젝트 구성](#프로젝트_구성)
- [트러블 슈팅](#트러블-슈팅)

## 시작 가이드

### IDE에서 실행 방법

- IDE에서 프로젝트로 로딩한 다음에 메이븐으로 컴파일 빌드를 하고 App.java 클래스를 실행합니다.

### 메이븐으로 컴파일 빌드 하는 방법

- 메이븐이 설치되어 있지 않은 경우 메이븐 랩퍼(mvnw 또는 mvnw.cmd(윈도)를 사용해서 빌드하세요.

```
mvnw compile
```

- 메이븐으로 컴파일을 해야 프론트엔드 라이브러리를 받아오며 QueryDSL 관련 코드를 생성합니다.

### 콘솔에서 실행 방법

- JAR 패키징을 한 뒤 java -jar로 실행합니다.

```
mvnw clean compile package
```

```
java -jar target/*.jar
```

### DB 설정

- PostgreSQL 설치 후, psql로 접속해서 아래 명령어 사용하여 DB와 USER 생성하고 권한 설정.

```sql
CREATE DATABASE testdb;
CREATE USER testuser WITH ENCRYPTED PASSWORD 'testpass';
GRANT ALL PRIVILEGES ON DATABASE testdb TO testuser;
```

## 프로젝트 소개


## 프로젝트 구성

### Node.js 설정

### npm 설치


### Node.js 초기화 및 package.json 생성
```
$ npm init
```
![image](https://github.com/rudqls007/Study_group_service/assets/111556581/af3eaa2b-3687-437a-9fa2-80c4b4769c6d)


### Bootstrap 및 jquery, Maven Wrappper 빌드
```
$ npm install bootstrap
$ npm install jquery --save
$ ./mvnw test
```
![image](https://github.com/rudqls007/Study_group_service/assets/111556581/a2cf065b-6ba9-478f-a1cb-d584c356c669)

### Maven 빌드를 해야 하는 이유는 ?

- 메이븐 pom.xml을 빌드할 때 static 디렉토리 아래에 있는 package.json도 빌드하도록 설정해야 한다.
- 빌드를 안하면 프론트엔드 라이브러리를 받아오지 않아서 뷰에서 필요한 참조가 끊어지고 화면이 제대로 보이지 않는다.

### pom.xml

```
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>1.8.0</version>
    <configuration>
        <nodeVersion>v4.6.0</nodeVersion>
        <workingDirectory>src/main/resources/static</workingDirectory>
    </configuration>
    <executions>
        <execution>
            <id>install node and npm</id>
            <goals>
                <goal>install-node-and-npm</goal>
            </goals>
            <phase>generate-resources</phase>
        </execution>
        <execution>
            <id>npm install</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <phase>generate-resources</phase>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </execution>
    </executions>
</plugin>
```



### 스프링 시큐리티 PasswordEncoder 예제

`PasswordEncoderFactories.createDelegatingPasswordEncoder()`를 사용하여 여러 해시 알고리즘을 지원하며, 기본적으로 `bcrypt` 알고리즘을 사용함.

### 해싱 알고리즘(bcrypt)과 솔트(salt)

1. **해싱 알고리즘(bcrypt)**:
   - 비밀번호를 해시하여 저장하면, 비밀번호가 노출되더라도 원래 비밀번호를 알 수 없도록 함.
   - `bcrypt`는 현재 널리 사용되는 강력한 해싱 알고리즘 중 하나로, 해킹 공격에 대비하여 설계되었음.

2. **솔트(salt)**:
   - 솔트는 해시함수의 입력에 추가되는 랜덤 데이터
   - 솔트를 사용하면 같은 비밀번호라도 솔트가 다르면 다른 해시 값을 생성하므로, 사전 공격(Dictionary Attack) 및 무차별 대입 공격(Brute-Force Attack)을 방지할 수 있음.

### 코드

```java
package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}



```

### 스프링 시큐리티 UsernamePasswordAuthenticationToken 생성 예제

`UsernamePasswordAuthenticationToken` 를 사용해 사용자 권한 부여 및 인증 토큰을 생성하여 로그인 처리를 위해 사용함.

### UsernamePasswordAuthenticationToken 와 SecurityContextHolder

1. **UsernamePasswordAuthenticationToken**:

   - Spring Security에서 제공하는 클래스 중 하나로 사용자 이름(username), 비밀번호(password), 권한(authrities)을 담는 토큰임.
   - List.of(new SimpleGrantedAuthority("ROLE_USER")) 를 통해 사용자 권한을 설정함.

2. **SecurityContextHolder**:

   - SecurityContextHolder 는 Spring Security에서 현재 스레드의 보안 컨텍스트를 보유하는 클래스임.
   - 애플리케이션의 각 요청에 대해 SecurityContext 를 제공하여, 현재 사용자의 인증 상태를 저장하고 관리함.
   - SecurityContext 는 현재 인증된 사용자에 대한 정보를 포함하는 객체임.
   - 즉, SecurityContextHolder.getContext() 를 호출하여 현재 스레드의 SecurityContext 를 가져옴

### 코드

```java

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                account.getNickname(),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));


        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);

```


## 트러블 슈팅

⛔ 이메일을 체크 토큰 ( generateEmailCheckToken ) = NULL 오류

1. 문제 발생

- 이메일 인증 체크 로직에서 NullPointerException 오류
  
![@Transactional ( 트랜잭션 오류로 인한 null 처리 ) ](https://github.com/rudqls007/study_management_service/assets/111556581/d4c7f894-699f-4bda-a7c1-2df266449513)

- check-email-token 로직 정상적으로 실행되는 것을 볼 수 있음.

![check-email-token_로직이 정상적으로 실행되는 것을 볼 수 있음](https://github.com/rudqls007/study_management_service/assets/111556581/1f04002e-5f45-4b64-b039-8b46253757b5)


2. 문제 원인

- NullPointerException로 인해 이메일 인증이 정상적으로 처리되지 않음.

3. 문재 해결 시도

- 해당 비즈니스 로직에 디버깅 시도

- 디버깅 시 token, email, account 값이 정상적으로 들어온 것을 확인할 수 있음.

![디버깅 시 token, email, account 값이 정상적으로 들어온 것을 확인할 수 있음](https://github.com/rudqls007/study_management_service/assets/111556581/e6c74901-1906-4ad7-8dd4-77c38c33564f)

- 하지만 emailCheckToken이 Null로 뜨기에 이메일을 체크하는 토큰을 비교할 수 없기에 널포인트가 뜬 것임.

![하지만 emailCheckToken이 Null로 뜨기에 이메일을 체크하는 토큰을 비교할 수 없기에 널포인트가 뜬 것임](https://github.com/rudqls007/study_management_service/assets/111556581/57598df0-8056-4900-91f1-72d22517824c)

4. 해결 방법

- Before

        public void processNewAccount(SignUpForm signUpForm) {
            /* entity에 맞는 정보를 signUpForm 형태로 변환하여 데이터를 받아 리포지토리 save 메소드로
             *  회원 가입과 영속 상태를 만듦.   */
            Account newAccount = saveNewAccount(signUpForm);
    
            /* 이메일을 체크하는 토큰을 만듦 */
            newAccount.generateEmailCheckToken();
    
            /* 체크한 이메일에 인증 메시지를 보내는 로직 */
            sendSignUpConfirmEmail(newAccount);
        }
    
    
        private Account saveNewAccount(SignUpForm signUpForm) {
            // Account Entity에 있는 builder를 통해 회원 가입 폼에 입력한 signUpForm 데이터들을 account 변수에 저장
            Account account = Account.builder()
                    .email(signUpForm.getEmail())
                    .nickname(signUpForm.getNickname())
                    .password(passwordEncoder.encode(signUpForm.getPassword())) // TODO encoding 해야 함.
                    .studyCreatedByWeb(true)
                    .studyEnrollmentResultByWeb(true)
                    .studyUpdatedResultByWeb(true)
                    .build();
    
            /* accountRepository.save() 리포지토리 save 메소드를 통해 저장되었던 account 변수를 매개변수로 넣어줌으로써
               회원 가입 로직과 영속상태가 됨. */
            // signUpForm
            Account newAccount = accountRepository.save(account);
            return newAccount;
        }


  newAccount.generateEmailCheckToken(); 메소드가 정상적으로 실행이 되지 않으면서 DB에 저장이 되지 않았기에 토큰 값이 NULL.</br>
  이유는 saveNewAccount 메소드 안에서는 Account newAccount = accountRepository.save(account); 메소드로 인해 Entitiy LifeCycle에</br>
  해당하는 Persist 즉 영속 상태임.




- atfer
  
      @Transactional
      public void processNewAccount(SignUpForm signUpForm) {
          /* entity에 맞는 정보를 signUpForm 형태로 변환하여 데이터를 받아 리포지토리 save 메소드로
           *  회원 가입과 영속 상태를 만듦.   */
          Account newAccount = saveNewAccount(signUpForm);
  
          /* 이메일을 체크하는 토큰을 만듦 */
          newAccount.generateEmailCheckToken();
  
          /* 체크한 이메일에 인증 메시지를 보내는 로직 */
          sendSignUpConfirmEmail(newAccount);
      }


  그 다음 newAccount.generateEmailCheckToken();가 실행 되는데 트랜잭션 범위를 벗어났기에 Detached 상태가 됨.</br>
  이 메소드가 트랜잭션 범위 안에 있어야 정상적인 값이 DB에 저장이 되므로 메소드에 @Transactional 어노테이션을 붙여주어야 함.</br>



## 프로젝트 기능

### 로그인


- @CurrentUser

  ```
    import org.springframework.security.core.annotation.AuthenticationPrincipal;

    import java.lang.annotation.ElementType;
    import java.lang.annotation.Retention;
    import java.lang.annotation.RetentionPolicy;
    import java.lang.annotation.Target;
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
    public @interface CurrentUser {
    }
  ```

  - @CurrentUser 어노테이션은 컨트롤러 메서드의 파라미터에서 현재 인증된 사용자 정보를 쉽게 접근할 수 있도록 도와주는 역할을 함.
    ![image](https://github.com/user-attachments/assets/7882dba7-69dc-49c4-9219-6d03c496a581)
    -  ```
       <!-- account 정보가 있고 email 정보가 없으면 이메일 인증을 하게끔 만드는 알림창 -->
        <div class="alert alert-warning" role="alert" th:if="${account != null && !account.emailVerified}">
            스터디올레 가입을 완료하려면 <a href="#" th:href="@{/check-email}" class="alert-link">계정 인증 이메일을 확인</a>하세요.
        </div>
       ```
       
        - 익명 사용자인 경우 null을 반환
        - 인증된 사용자인 경우 해당 Account 도메인 객체를 반환함.
        - 이메일 인증이 되지 않은 사용자는 위 로직을 통해 이메일 인증 알림창이 뜸.
        - 
- UserAccount
     - ```
            @Getter @Setter
            public class UserAccount extends User {
            private final Account account;
    
            public UserAccount(Account account) {
                super(account.getNickname(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
                this.account = account;
            }
        }
        ```
        
          - UserAccount 클래스는 Spring Security의 User 클래스를 확장하여, 애플리케이션 사용자 정보를 포함하는 역할을 함.
          - Account 도메인 객체를 참조하여 User 클래스 생성자에 사용자 이름, 비밀번호, 권한을 초기화함.
        
- AccountSevice
     - ```
           public void login(Account account) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
    
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(token);
        }
       ```
       
        - Account 객체를 인자로 받아 사용자 인증 토큰을 생성함. ( UsernamePasswordAuthenticationToken )
        - 생성된 토큰을 SecurityContext에 설정하여 사용자 인증 상태를 유지함.
      
    - ```
         @Override
        public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
            Account account = accountRepository.findByEmail(emailOrNickname);
    
            if (account == null) {
                account = accountRepository.findByNickname(emailOrNickname);
            }
    
            if (account == null) {
                throw new UsernameNotFoundException(emailOrNickname);
            }
    
            return new UserAccount(account);
        }
        }
      ```
    
      - UserDetailService 인터페이스를 상속 받음.
      - 사용자의 email or nickname을 인자로 받아 데이터베이스에서 해당 사용자를 조회함.
      - 사용자를 찾지 못할 경우에 UsernameNotFoundException 예외를 발생 시킴.
      - 사용자를 찾으면 USerAccount 객체로 반환하여 Spring Security가 사용자 인증을 처리할 수 있도록 함.
