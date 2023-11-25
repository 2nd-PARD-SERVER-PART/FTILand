# `OldNew`: 디지털 세대와 아날로그 세대 간의 연결 서비스

## 사용한 Entity/Table
1. `User`

Key | Description | Type | Property
--- | --- | --- | ---
`id` | 사용자 고유 번호 | `Long` | **Primary-key**, *Auto-incrementing*
`userid` | 사용자 ID | `String` | *최대 길이 20자*
`username` | 사용자 이름 | `String` | *최대 길이 20자*
`password` | 사용자 비밀번호 | `String` | *최대 길이 20자*
`room` | 들어가 있는 방 | `Room` | *Many-to-one*

2. `Room`

Key | Description | Type | Property
--- | --- | --- | ---
`id` | 공간 고유 번호 | `Long` | **Primary-key**, *Auto-incrementing*
`code` | 공간 초대 코드 | `String` | 8글자로 고정, 알파벳 - 숫자 조합 |
`users` | 공간에 있는 사용자들 | `List<User>` | *One-to-many* (한 방에 사용자 여러 명이 존재 가능)
`posts` | 공간에 들어간 편지들 | `List<Post>` | *One-to-many* (한 방에 편지 여러 개가 존재 가능)

3. `Post`

Key | Description | Type | Property
--- | --- | --- | ---
`id` | 편지 고유 번호 | `Long` | **Primary-key**, *Auto-incrementing*
`room` | 편지가 들어가 있는 방 | `Room` | *Many-to-one* (편지 여러 개가 한 방에 존재)
`author` | 편지 작성자 | `User` | *Many-to-one* (편지 여러 개가 한 사용자의 소유)
`title` | 편지 제목 | `String` | *최대 글자 제한 15*
`sender` | 편지 작성자(별칭) | `String` | *최대 글자 제한 10*
`content` | 편지 본문 | `String` | *최대 글자 제한 400*
`timeUploaded` | 편지 작성 시간 | `Timestamp` | `@CreationTimestamp`

## 사용한 HTTP 요청
1. 회원 관련(`/user`)
    1. 회원가입(`POST /user`)
    2. 로그인(`GET /user`)

2. 글 관련(`/post`)
    1. 글 조회(`GET /post`)
    2. 글 등록(`POST /post`)

3. 방 관련(`/room`)
    1. 방 초대코드 생성(`POST /room`)
        - 방 생성 시 `POST` 이후 바로 `PATCH`, 이후 바로 `GET` 진행.
    2. 방 합류(`PATCH /room`)
        - 방 합류 시 `PATCH` 이후 바로 `GET` 진행.
    3. 방 조회(`GET /room`)