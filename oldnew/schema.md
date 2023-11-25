# Expected entities
1. User
    - userid (Auto-incrementing, primary key, long)
    - userid (String, length limit to 20)
    - username (String, length limit to 20)
    - password (String, length limit to 20)
    - room (Many-to-one w/ Room: Multiple users can join one room)
2. Room
    - roomid (Auto-incrementing, primary key, long)
    - roomcode (String, length set to 8)
    - users (One-to-many w/ User: One room can have multiple users)
    - posts (One-to-many w/ Post: One room can have multiple posts)
3. Post
    - postid (Auto-incrementing, primary key, long)
    - roomid (Many-to-one w/ Room: One post can have only one room))
    - author (One-to-many w/ User: One post can be written by single author)
    - title (String, length limit to 10)
    - sender (String, length limit to 400)
    - content (String, not limited)
    - uploadTime (CreationTimeStamp)
    - image (String)

# Expected HTTP requests
1. 회원 관련(`/user`)
    1. 회원가입(`POST /user`)
    2. 로그인(`GET /user`)
    3. 회원정보 변경(`PATCH /user`) - just in case. NOT NEEDED.
2. 글 관련(`/post`)
    2. 글 조회(`GET /post`)
    3. 글 등록(`POST /post`)
3. 방 관련(`/room`)
    1. 방 초대코드 생성(`POST /room`)
        - 방 생성 시 `POST` 이후 바로 `PATCH`, 이후 바로 `GET` 진행.
    2. 방 합류(`PATCH /room`)
        - 방 합류 시 `PATCH` 이후 바로 `GET` 진행.
    3. 방 조회(`GET /room`)