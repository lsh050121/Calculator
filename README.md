## JAVA 프로그래밍2 중간고사 계산기
___
### 인공지능소프트웨어학과
### 2021011866 이상혁
___

JAVA GUI 를 이용한 간단한 사칙연산을 할수 있는 계산기입니다.
___

## HISTORY

인터페이스 v1.0  
인터페이스 v1.1 (위 버전의 인터페이스에서 버튼숫자를 간소화 하고 행, 열 재 설정)  
인터페이스 V1.2 (숫자 버튼과 연산 버튼의 색상을 다르게 변경, 계산기 화면을 화면 중앙 배치, 사이즈 조절 불가 하게 설정)  
인터페이스 v1.3 (텍스트 필드의 사이즈를 설정, 기존 BorderLayout을 사용했으나 사이즈 조절 방안을 찾지 못하여   
배치 관리자를 null 로 설정하고 절대위치를 설정 참고 자료:https://blog.naver.com/sks6624/150165603219)  
인터페이스 v1.4 (텍스트 필드 내의 텍스트 위치 우측정렬, 사이즈, 폰트 설정, 텍스트 필드의 배경색 : WHITE, 테두리 없음 설정)

---

계산기 func v1.0 (각 버튼들을 누르면 그 버튼을 화면에 띄우는 기능, C버튼 시 clear 기능 설정)  
계산기 func v1.1 (각 버튼에 해당하는 연산 기능 추가)  
계산기 func v1.2 (25 + 3 연산을 할 경우 화면에 25 -> 3 -> 28.0 이런 식으로 표시되었는데 이를 25 -> 25 + -> 25 + 3 -> 28 과 같이 연산 과정을 화면에 표시 해주게 설정, 나누기 연산시 소수점 6자리 까지 표시하도록 설정)
