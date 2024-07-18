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

### Node.js 설정 ( 프론트 라이브러리 )

#### npm 설치


#### Node.js 초기화 및 package.json 생성
```
$ npm init
```
![image](https://github.com/rudqls007/Study_group_service/assets/111556581/af3eaa2b-3687-437a-9fa2-80c4b4769c6d)


#### Bootstrap 및 jquery, Maven Wrappper 빌드
```
$ npm install bootstrap
$ npm install jquery --save
$ ./mvnw test
```
![image](https://github.com/rudqls007/Study_group_service/assets/111556581/a2cf065b-6ba9-478f-a1cb-d584c356c669)

#### Maven 빌드를 해야 하는 이유는 ?

- 메이븐 pom.xml을 빌드할 때 static 디렉토리 아래에 있는 package.json도 빌드하도록 설정해야 한다.
- 빌드를 안하면 프론트엔드 라이브러리를 받아오지 않아서 뷰에서 필요한 참조가 끊어지고 화면이 제대로 보이지 않는다.

#### pom.xml

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

#### cropper ( 프로필 이미지 )
```
$ npm install cropper
$ npm install jquery-cropper

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

### 인증된 사용자를 제공할 커스텀 애노테이션 @WithAccount 을 통한 테스트 코드 작성


### 코드 ( @WithAccount )

```java
package com.study;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithAccountSecurityContextFactory.class)
public @interface WithAccount {

    String value();

}
```

- 테스트에서 인증된 사용자를 설정하는 역할을 지닌 어노테이션
- 런타임 시점에 어노테이션이 실행되며, WithAccountSecurityContextFactory 클래스를 통해 사용자를 생성하고 인증 정보를 설정함.

### 코드 ( WithAccountSecurityContextFactory )

```java
package com.study;

import com.study.account.AccountService;
import com.study.account.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {
        String nickname = withAccount.value();

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setNickname(nickname);
        signUpForm.setEmail(nickname + "@example.com");
        signUpForm.setPassword("12345678");
        accountService.processNewAccount(signUpForm);

        UserDetails principal = accountService.loadUserByUsername(nickname);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }
}
```

- createSecurityContext 메소드는 WithSecurityContextFactory 인터페이스에서 정의된 메소드로, 인자로 받은 WithAccount 어노테이션에서 지정한 닉네임을 사용하여 사용자 계정을 생성함.
- SignUpForm ( 회원가입 ) 객체를 생성하고, 해당 객체에 대한 닉네임, 이메일, 비밀번호를 설정
- accountService.processNewAccount(signUpForm) 을 호출하여 영속성 컨텍스트에 저장이 되면서 새로운 사용자 계정을 등록함.
- 이 후 UserDetail 인터페이스 타입 객체인 principal에 loadUserByUsername ( 사용자 조회 ) 를 사용하여 조회된 사용자 정보로부터 인증 객체를 생성하여 저장함.
- SecurityContextHolder.createEmptyContext() 를 호출하여 SecurityContext를 생성해 인증 객체를 설정 및 반환함.

### 코드 ( SettingsControllerTest )
```java
@SpringBootTest
@AutoConfigureMockMvc
class SettingsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    AccountRepository accountRepository;


    @AfterEach
    void afterEach() throws Exception {
        accountRepository.deleteAll();
    }

    @WithAccount("kyungbin")
    @DisplayName("프로필 수정하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {


        String bio = "짧은 소개를 수정하는 경우.";

        mockMvc.perform(post(SettingsController.SETTINGS_PROFILE_URL)
                        .param("bio", bio)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SettingsController.SETTINGS_PROFILE_URL))
                .andExpect(flash().attributeExists("message"));

        Account kyungbin = accountRepository.findByNickname("kyungbin");
        assertEquals(bio, kyungbin.getBio());


    }

```
- 위 @WithAccount 어노테이션을 사용하여 WithAccountSecurityContextFactory 클래스가 설정한 인증 정보를 기반으로 한 사용자가 생성됨.
- kyungbin 닉네임의 사용자가 인증된 상태로 Spring Security와 함께 동작하는 테스트를 수행할 수 있음.


### 전체적인 로직의 흐름

1. 테스트 메소드 실행
   - @WithAccount("kyungbin") 어노테이션이 붙은 테스트 메서다그 실행되면서, kyungbin 닉네임을 가진 사용자를 생성하고, 해당 사용자를 인증된 상태로 설정
3. WithAccountSecurityContextFactory 클래스 동작
   - createSecurityContext 메소드를 통해 주어진 닉네임을 가진 사용자를 생성하고, 해당 사용자를 데이터베이스에 등록함.
   - 이 후 생성된 사용자 정보를 이용하여 Spring Security의 인증 객체를 생성하고, 이를 SecurityContext에 설정하여 반환함.
5. 테스트 수행
   - 생성된 인증 정보를 기반으로한 사용자를 Spring Security는 해당 인증 정보를 기반으로 인증 및 권한 검사를 수행 후 인증이 필요한 테스트 로직의 결과를 반환\7함.


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



⛔ 프로필 수정 문제

1. 문제 발생


![20240714_211756-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/e11d7f39-c268-4c02-9e69-25e19d3e9cd7)


- 프프로필 수정 기능이 제대로 동작하지 않아 사용자가 프로필을 업데이트하려고 해도 변경 사항이 저장되지 않음.

2. 문제 원인

![image](https://github.com/user-attachments/assets/2934a918-8878-49bd-8a11-67acb4ba9bfe)


![image](https://github.com/user-attachments/assets/2cd68592-2088-416e-aa82-9405a12d7f57)


- 프로필 수정이 제대로 되지 않는 핵심 원인은 `Account` 객체의 영속성 문제였음.
- 컨트롤러에서 `Account` 객체는 로그인 시점에 한 번 영속 상태였다가 이후에 detached 상태가 되었기 때문에, 서비스 클래스의 `updateProfile` 메서드에서 `Account` 객체가 영속성 컨텍스트에 관리되지 않아 변경 사항이 데이터베이스에 반영되지 않음.

JPA 영속성 설명

JPA에서 엔티티의 생명 주기는 다음과 같습니다:
1. **Transient**: 영속성 컨텍스트와 전혀 관계가 없는 상태.
2. **Persistent**: 영속성 컨텍스트에 의해 관리되는 상태.
3. **Detached**: 영속성 컨텍스트에서 분리된 상태.
4. **Removed**: 삭제된 상태.

- 영속성 컨텍스트는 엔티티를 영속 상태로 유지하고, 변경 사항을 추적하여 데이터베이스에 자동으로 반영합니다. 엔티티가 detached 상태가 되면, 영속성 컨텍스트는 해당 엔티티의 변경 사항을 추적하지 않음. 
- 따라서 detached 상태의 엔티티에 대한 변경 사항은 데이터베이스에 반영되지 않음.

3. 문제 해결 시도

- 변경된 객체 저장: `updateProfile` 메서드에서 변경된 `Account` 객체를 저장소(repository)에 저장하도록 시도.
- 세션 업데이트: 세션에 저장된 `Account` 객체를 업데이트하도록 시도.

4. 해결 방법

![image](https://github.com/user-attachments/assets/6fe18594-2f69-40ee-a228-7de97735ebe3)


![20240714_211501-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/4ce447f0-7004-43e4-9d7b-908df9a2dc61)


- 영속성 컨텍스트에 다시 연결: 서비스 메서드에서 `Account` 객체를 영속성 컨텍스트에 다시 연결하여 변경 사항을 반영할 수 있도록 함.



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

  - @Retnention(RetentionPolicy.RUNTIME)
      - 런타임까지 유지된다는 것을 의미하며, 런타임 시에 리플렉션을 사용하여 이 어노테이션을 읽을 수 있음.
  - @Target(ElementType.PARAMETER)
      - 인증된 사용자 정보를 컨트롤러 메서드의 파라미터로 바인딩하기 위해 사용할 목적으로 메서드의 파라미터에서만 적용할 수 있다는 것을 의미함.
  - @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
      - 인증된 사용자 객체를 메서드 파라미터로 바인딩하는 데 사용함.
      - #this = 현재 인증 객체 즉 Account 객체를 나타냄.
      - 현재 인증 객체가 anonymousUser( 익명 사용자 )라면, null을 그렇지 않다면, 인증된 사용자 객체의 account 필드를 반환함.
        
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
        - 이메일 인증이 되지 않은 사용자는 위 로직을 통해 이메일 인증 알림창이 뜸. <br />
          
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

### 로그인 기억하기 ( Spring Security - rememberMe )

![login](https://github.com/user-attachments/assets/ea91a789-ab16-48a3-9127-e366cd0f665f)

![remeber](https://github.com/user-attachments/assets/620293b6-b8e3-42b2-9f94-23cb04fafcba)

- JSESSIONID 이 만료되거나 쿠키가 없을 지라도 어플리케이션이 사용자를 기억하는 기능이다. 자동 로그인 기능을 떠올리면 쉽다.
- Remember-Me 토큰 쿠키를 이용한다. 서버는 이 토큰의 유효성을 검사하고, 검증되면 사용자는 로그인된다.


- SecurityConfig

    - ```
       @Override
        protected void configure(HttpSecurity http) throws Exception {
           http.rememberMe()
                .userDetailsService(accountService)
                .tokenRepository(tokenRepository());
        }

      @Bean
        public PersistentTokenRepository tokenRepository() {
            JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
            tokenRepository.setDataSource(dataSource);
    
            return tokenRepository;
        }
      ```
      - rememberMe() 활성화
      - userDetailService 인터페이스를 구현한 accountService를 통해 사용자 인증 정보를 가져옴.
      - tokenRepository 메서드를 호출하여 토큰 저장소를 설정함.
      - Spring Context에 이 메서드의 반환값을 빈으로 등록하여 다른 곳에서 이 빈을 주입받아 사용할 수 있도록 함.
      - JdbcTokenRepositoryImpl 클래스는 PersistentTokenRepository 인터페이스의 구현체 중 하나로 JDBC를 사용하여 토큰 정보를 데이터베이스에 저장함.
      - 데이터베이스에 접근하기 위해 setDataSource(dataSource)를 설정함. dataSource는 애플리케이션에서 데이터베이스 연결을 관리하는 빈임.

- PersistentLogins
    - ```
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
      ```
      - User를 상속 받은 Entity 객체(Account)를 찾아서 jdbc 클래스에 있는 스키마에 해당하는 스키마가 생성될 수 있도록 매핑을 하는 역할을 함. ( 즉 쿠키를 사용하기 위한 엔티티 )

### 프로필 이미지 변경

![20240715_081900-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/a9045da8-7fc0-41c4-a6a9-08f304e434e8)

- cropper 라이브러리를 활용하여 프로필 이미지 변경하기 ( npm으로 라이브러리 설치 )
- ```java
   // 프로필 이미지 파일을 선택하면 ( 값이 바뀌면 시작 함 )
        $("#profile-image-file").change(function(e) {
            if (e.target.files.length === 1) {
                // FileReader를 만듦
                const reader = new FileReader();
                // 파일을 읽어 옴으로써 온 로드 상태일 때
                reader.onload = e => {
                    if (e.target.result) {
                        let img = document.createElement("img");
                        img.id = 'new-profile';
                        img.src = e.target.result;
                        img.width = 250;

                        // 새로운 이미지 태그를 생성
                        $newProfileImage.html(img);
                        // 새로운 이미지 파일을 보여줌
                        $newProfileImage.show();
                        // 현재 이미지 파일을 숨겨줌 ( 새로 로딩한 것을 보여줌 )
                        $currentProfileImage.hide();

                        // 크로퍼 적용 ! 새로운 이미지 파일을 읽어 옴
                        let $newImage = $(img);
                        // 이미지 파일을 잘라낼 영역 표시
                        $newImage.cropper({aspectRatio: 1});
                        cropper = $newImage.data('cropper');

                        // 잘라내기 버튼 보여주고
                        $cutBtn.show();
                        // 확인 버튼 숨기고
                        $confirmBtn.hide();
                        // 리셋 버튼 보여줌
                        $resetBtn.show();
                    }
                };

                // 파일을 읽어 옴
                reader.readAsDataURL(e.target.files[0]);
            }
        });
  ```
- ```java
     <svg th:if="${#strings.isEmpty(account?.profileImage)}" th:data-jdenticon-value="${#authentication.name}"
                         width="24" height="24" class="rounded border bg-light"></svg>
     <img th:if="${!#strings.isEmpty(account?.profileImage)}" th:src="${account.profileImage}"
                         width="24" height="24" class="rounded border"/>
  ```
  - account 객체에 프로필 이미지 값이 비었다면, jdenticon을 활용하여 기본 이미지를 생성함.
  - 프로필 이미지 값이 있다면 해당 사용자의 프로필 이미지를 보여줌. ( dataURL 사용 )
  - DataURL (https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Data_URLs)
    - data URI 체계는 외부 리소스인 것처럼 웹 페이지에 인라인 데이터를 포함하는 방법을 제공하는 URI(Uniform Resource Identifier) 체계임
    - data: 라는 접두어를 가진 URL로 파일을 문서에 내장 시킬때 사용할 수 있다.
    - 이미지를 DataURL로 저장할 수 있고,  이미지, 스타일 시트 같은 별도 요소를 단일 HTTP 요청으로 가져올 수 있음.


### 패스워드 수정

- ```java
  package com.study.settings;

    import lombok.Data;
    import org.hibernate.validator.constraints.Length;
    
    @Data
    public class PasswordForm {

    @Length(min = 8, max = 50)
    private String newPassword;

    @Length(min = 8, max = 50)
    private String newPasswordConfirm;
    }
  ```
  - 클라이언트에서 받아 올 PasswordForm 데이터

- ```java
    @Service
    @Transactional
    @RequiredArgsConstructor
    public class AccountService implements UserDetailsService {
         public void updatePassword(Account account, String newPassword) {
            account.setPassword(passwordEncoder.encode(newPassword));
            accountRepository.save(account); // merge
        }
  }
  ```
  -  Detached 상태의 객체를 변경한 다음 Repositoiry의 save를 호출해서 상태 변경 내역을 적용 해야함. (Merge)
- ```java
    package com.study.settings;

    import org.springframework.validation.Errors;
    import org.springframework.validation.Validator;
    
    public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passwordForm = (PasswordForm) target;
        if (!passwordForm.getNewPassword().equals(passwordForm.getNewPasswordConfirm())) {
            errors.rejectValue("newPassword",
                    "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
        }
    }
    }

  ```
  - supports 메서드는 이 Validator가 PasswordForm 객체를 지원하는지 확인하고, PasswordForm 클래스 또는 그 하위 클래스의 인스턴스를 검증할 수 있음.
  - validate 메서드는 두 패스워드가 일치하지 않으면 Errors 객체에 에러를 추가함 이는 Spring의 Validator 인터페이스를 구현한 것임.

- ```java
    @InitBinder("passwordForm")
    public void initPasswordBinder(WebDataBinder binder) {
        binder.addValidators(new PasswordFormValidator());
    }
        @PostMapping(SETTINGS_PASSWORD_URL)
        public String updatePassword(@CurrentUser Account account,
                                     @Valid PasswordForm passwordForm, Errors errors,
                                     Model model, RedirectAttributes attributes) {

    if (errors.hasErrors()) {
        model.addAttribute(account);
        return SETTINGS_PASSWORD_VIEW_NAME;
    }

    accountService.updatePassword(account, passwordForm.getNewPassword());
    attributes.addFlashAttribute("message", "패스워드를 변경했습니다.");
    return "redirect:" + SETTINGS_PASSWORD_URL;
    }
  ```
  - @InitBinder("passwordForm") 를 통해 PasswordForm 객체에 데이터 바인딩과 유효성을 검사함.
  - @CurrentUser 어노테이션을 통해 해당 Account 객체 즉, 현재 사용자 정보를 가져옴.
  - @Valid 어노테이션을 통해 PassowrdForm 객체를 검증하고, PasswordFormValidator에서 설정한 규칙에 따라 검증이 수행됨.
  - RedirectAttributes를 통해 일회성 메세지를 함께 반환함.
