## 간단소개 
<br/><br/> 환자 정보에 대한 기본적인 CRUD API 개발

## 개발기간 
<br/><br/> 2023/06/01 프로젝트 세팅 및 H2 설정 완료
<br/><br/> 2023/06/06 ~ 2023/06/08 Entity 클래스 생성 및 CRUD API 개발

## 개발 환경
<br/><br/> Project - Gradle - Groovy
<br/><br/> Language - Java11(OpenJdk version 11.0.18)
<br/><br/> Spring Boot 2.7.12

## 개발 내용
<br/><br/> 1.환자등록 - @PostMapping("/patient") 
<br/><br/> - CreatePatientRequest Dto로 환자의 정보를 받고, generatePatientNo 라는 메서드로 환자등록번호를 생성 후 환자를 등록합니다.
<br/><br/> 2.환자수정 - @PutMapping("/patient") 
<br/><br/> - EditPatientRequest Dto로 환자의 정보를 받아 수정합니다.
<br/><br/> 3.환자삭제 - @PostMapping("/patient") 
<br/><br/> - request로 환자의 정보를 받아 수정합니다.
<br/><br/> 4.환자조회 - @PostMapping("/patient") 
<br/><br/> - hospitalCounters는 병원 ID와 현재 발급된 번호에 대한 정보를 담는 자료 구조입니다. 이 구조체의 computeIfAbsent() 메서드는 해당 병원 ID에 대한 값을 찾아서 가져오고, 만약 해당 ID에 대한 값이 없다면 새로운 값을 AtomicInteger 객체로 만들고, 이를 해당 병원 ID의 데이터로 추가하도록 개발되었습니다.
<br/><br/> 5.환자목록조회 - @PostMapping("/patient") 
<br/><br/> - hospitalCounters는 병원 ID와 현재 발급된 번호에 대한 정보를 담는 자료 구조입니다. 이 구조체의 computeIfAbsent() 메서드는 해당 병원 ID에 대한 값을 찾아서 가져오고, 만약 해당 ID에 대한 값이 없다면 새로운 값을 AtomicInteger 객체로 만들고, 이를 해당 병원 ID의 데이터로 추가하도록 개발되었습니다.
<br/><br/> - hospitalCounters는 병원 ID와 현재 발급된 번호에 대한 정보를 담는 자료 구조입니다. 이 구조체의 computeIfAbsent() 메서드는 해당 병원 ID에 대한 값을 찾아서 가져오고, 만약 해당 ID에 대한 값이 없다면 새로운 값을 AtomicInteger 객체로 만들고, 이를 해당 병원 ID의 데이터로 추가하도록 개발되었습니다.

## Dependency
Spring Web
<br/><br/>JPA
<br/><br/>querydsl
<br/><br/>h2
<br/><br/>lombok


