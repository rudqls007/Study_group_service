스터디 모임 관리 서비스

# 트러블 슈팅

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
