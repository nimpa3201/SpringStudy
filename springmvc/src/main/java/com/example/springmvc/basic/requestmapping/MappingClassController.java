package com.example.springmvc.basic.requestmapping;


import org.springframework.web.bind.annotation.*;

@RestController
public class MappingClassController {

    //회원 목록 조회
    @GetMapping("/mapping/users")
    public String user(){
        return "get user";
    }
     //회원 등록
    @PostMapping("/mapping/users")
    public String addUser(){
        return "post user";
    }
    //회원 조회
    @GetMapping("mapping/users/{userId}")
    public String findUser(@PathVariable String userId){
        return "get UserId =" + userId;
    }
    // 회원 수정
    @PatchMapping("/mapping/users/{userId}")
        public String updateUser(@PathVariable String userId){
            return "update userId ="+ userId;
    }

    //회원 삭제

    @DeleteMapping("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId =" + userId;
    }



//- GET /mapping/users: 모든 사용자 조회
//- POST /mapping/users: 사용자 추가
//- GET /mapping/users/{userId}: 사용자 ID로 사용자 조회
//- PATCH /mapping/users/{userId}: 사용자 ID로 사용자 수정
//- DELETE /mapping/users/{userId}: 사용자 ID로 사용자 삭제



}

