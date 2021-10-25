package com.vket.api.controller;


import com.vket.api.request.UserLoginPostReq;
import com.vket.api.request.UserNicknameEditReq;
import com.vket.api.request.UserPostReq;
import com.vket.api.response.UserLoginPostRes;
import com.vket.api.service.UserService;
import com.vket.common.response.BaseResponseBody;
import com.vket.config.JwtTokenProvider;
import com.vket.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 API", tags = {"User"})
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    // 회원가입
    @PostMapping("/signUp")
    @ApiOperation(value = "회원 가입", notes = "<strong>유저 정보</strong>를 통해 회원가입 한다.")
    public ResponseEntity<? extends BaseResponseBody> join(@RequestBody UserPostReq userPostReq) {

        userService.createUser(userPostReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @GetMapping("/userId/{userId}")
    @ApiOperation(value = "아이디 중복확인", notes = "<strong>아이디</strong>중복을 확인한다.")
    public ResponseEntity<? extends BaseResponseBody> userIdDuplicate(@PathVariable("userId") String userId){

        // 중복입니다.
        if(userService.userIdDuplicate(userId)){
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "아이디 중복입니다."));
        }else{
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }
    }

    @GetMapping("/userNickname/{userNickname}")
    @ApiOperation(value = "닉네임 중복확인", notes = "<strong>닉네임</strong>중복을 확인한다.")
    public ResponseEntity<? extends BaseResponseBody> userNicknameDuplicate(@PathVariable("userId") String userNickname){
        // 중복입니다.
        if(userService.userNicknameDuplicate(userNickname)){
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "아이디 중복입니다."));
        }else{
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }
    }

    @PostMapping("/longIn")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public ResponseEntity<UserLoginPostRes> userNicknameDuplicate(@RequestBody UserLoginPostReq userLoginPostReq){

        try{
            UserLoginPostRes userLoginPostRes = userService.userLogin(userLoginPostReq);
            return new ResponseEntity<UserLoginPostRes>(userLoginPostRes, HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            UserLoginPostRes userLoginPostRes = null;
            return new ResponseEntity<UserLoginPostRes>(userLoginPostRes, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/nickname")
    @ApiOperation(value = "닉네임 등록/수정", notes = "<strong>닉네임</strong>을 등록/수정 한다.")
    public ResponseEntity<? extends BaseResponseBody> editUserNickname(@RequestBody UserNicknameEditReq userNicknameEditReq){

        userService.editUserNickname(userNicknameEditReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "닉네임 변경완료"));
    }

}
