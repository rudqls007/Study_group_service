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


### 스프링 시큐리티 PasswordEncoder 예제

`PasswordEncoderFactories.createDelegatingPasswordEncoder()`를 사용하여 여러 해시 알고리즘을 지원하며, 기본적으로 `bcrypt` 알고리즘을 사용함.

### 해싱 알고리즘(bcrypt)과 솔트(salt)

1. **해싱 알고리즘(bcrypt)**:
   - 비밀번호를 해시하여 저장하면, 비밀번호가 노출되더라도 원래 비밀번호를 알 수 없도록 함.
   - `bcrypt`는 현재 널리 사용되는 강력한 해싱 알고리즘 중 하나로, 해킹 공격에 대비하여 설계되었음.

2. **솔트(salt)**:
   - 솔트는 해시함수의 입력에 추가되는 랜덤 데이터
   - 솔트를 사용하면 같은 비밀번호라도 솔트가 다르면 다른 해시 값을 생성하므로, 사전 공격(Dictionary Attack) 및 무차별 대입 공격(Brute-Force Attack)을 방지할 수 있음.

### 예시 코드

```java
package com.example.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderExample {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String rawPassword = "mySecretPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Passwords match: " + matches);
    }
}
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
