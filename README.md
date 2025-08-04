API 명세서 (README.md)
이 문서는 To-Do List(일정) 관리 API의 명세서이다.

인증 및 인가
일정 수정 및 삭제 시에만 비밀번호를 통해 권한을 확인한다.

API Endpoints
1. 일정 생성
API 명: POST /todolists

설명: 새로운 일정을 생성

요청 형식: application/json

요청 값 (Request Body):

title (string): 일정 제목

content (string): 일정 내용

author (string): 작성자명

password (string): 비밀번호

응답 형식: application/json

반환 값 (Response Body):

id (number): 자동 생성된 고유 식별자

title (string): 일정 제목

content (string): 일정 내용

author (string): 작성자명

createdAt (string): 일정 생성일 (ISO 8601)

modifiedAt (string): 일정 수정일 (최초 생성 시 createdAt과 동일)

요청 예시 (JSON):

{
  "title": "Spring Boot 프로젝트 시작",
  "content": "API 명세서 작성",
  "author": "김코딩",
  "password": "1234"
}

응답 예시 (JSON):

{
  "id": 1,
  "title": "Spring Boot 프로젝트 시작",
  "content": "API 명세서 작성",
  "author": "김코딩",
  "createdAt": "2025-08-01T14:00:00",
  "modifiedAt": "2025-08-01T14:00:00"
}

2. 일정 조회
API 명: GET /todolists

설명: 등록된 전체 일정 목록을 조회. author 쿼리 파라미터로 작성자별 필터링 가능

요청 형식: query parameter

요청 값 (Query Parameter):

author (string, optional): 작성자명

응답 형식: application/json

반환 값 (Response Body): 수정일 기준 내림차순으로 정렬된 일정 목록

id (number)

title (string)

content (string)

author (string)

createdAt (string)

modifiedAt (string)

요청 예시:

전체 조회: GET /todolists

작성자별 조회: GET /todolists?author=김코딩

응답 예시 (JSON):

[
  {
    "id": 1,
    "title": "Spring Boot 프로젝트 시작",
    "content": "API 명세서 작성",
    "author": "김코딩",
    "createdAt": "2025-08-01T14:00:00",
    "modifiedAt": "2025-08-01T14:00:00"
  },
  {
    "id": 2,
    "title": "TIL 작성",
    "content": "오늘 배운 내용 정리",
    "author": "김코딩",
    "createdAt": "2025-08-01T10:00:00",
    "modifiedAt": "2025-08-01T11:00:00"
  }
]

3. 선택한 일정 수정
API 명: PUT /todolists/{todolistsId}

설명: 특정 ID를 가진 일정을 수정. 일정 제목과 작성자명만 수정 가능하며, 비밀번호 검증이 필요.

요청 형식: application/json

요청 값 (Path Variable):

todolistsId (number): 수정할 일정의 고유 ID

요청 값 (Request Body):

title (string): 변경할 일정 제목

author (string): 변경할 작성자명

password (string): 비밀번호 (수정 권한 검증용)

응답 형식: application/json

반환 값 (Response Body):

id (number)

title (string)

content (string)

author (string)

createdAt (string)

modifiedAt (string)

요청 예시 (JSON):

PUT /todolists/1

{
  "title": "수정된 제목",
  "author": "변경된 작성자",
  "password": "1234"
}

응답 예시 (JSON):

{
  "id": 1,
  "title": "수정된 제목",
  "content": "API 명세서 작성",
  "author": "변경된 작성자",
  "createdAt": "2025-08-01T14:00:00",
  "modifiedAt": "2025-08-01T15:00:00"
}

4. 선택한 일정 삭제
API 명: DELETE /todolists/{todolistsId}

설명: 특정 ID를 가진 일정을 삭제합니다. 비밀번호 검증이 필요합니다.

요청 형식: application/json

요청 값 (Path Variable):

todolistsId (number): 삭제할 일정의 고유 ID

요청 값 (Request Body):

password (string): 비밀번호 (삭제 권한 검증용)

응답 형식: 별도의 응답 본문 없음 (No Content)

반환 값: 204 No Content

요청 예시 (JSON):

DELETE /todolists/1

{
  "password": "1234"
}
