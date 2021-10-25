package com.vket.api.service;

import com.vket.api.request.UserLoginPostReq;
import com.vket.api.request.UserNicknameEditReq;
import com.vket.api.request.UserPostReq;
import com.vket.api.response.UserLoginPostRes;
import com.vket.config.JwtTokenProvider;
import com.vket.db.entity.User;
import com.vket.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public void createUser(UserPostReq userPostReq) {

        userRepository.save(User.builder()
        .userId(userPostReq.getUserId())
        .userPassword(passwordEncoder.encode(userPostReq.getUserPassword()))
        .userNickname(userPostReq.getUserNickname())
        .userPhone(userPostReq.getUserPhone())
        .userCharacter(-1L)
        .build());

        return;
    }

    @Override
    public boolean userIdDuplicate(String userId) {

        if(userRepository.findByUserId(userId).isPresent()){
            // 아이디 중복입니다.
            return true;
        }
        // 유니크 합니다.
        return false;
    }

    @Override
    public boolean userNicknameDuplicate(String userNickname) {

        if(userRepository.findByUserNickname(userNickname).isPresent()){
            // 중복입니다.
            return true;
        }
        // 유니크합니다.
        return false;
    }

    @Override
    public UserLoginPostRes userLogin(UserLoginPostReq userLoginPostReq) {

        User user = userRepository.findByUserId(userLoginPostReq.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 ID 입니다."));
        if(!passwordEncoder.matches(userLoginPostReq.getUserPassword(), user.getUserPassword())){
            throw  new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        UserLoginPostRes userLoginPostRes = UserLoginPostRes.builder()
                .accessToken(jwtTokenProvider.createToken(user.getUserId()))
                .message("로그인 완료")
                .statusCode(200)
                .userSeq(user.getUserSeq())
                .userId(user.getUserId())
                .userPhone(user.getUserPhone())
                .userNickname(user.getUserNickname())
                .userCharacter(user.getUserCharacter())
                .build();

        return userLoginPostRes;
    }

    @Override
    public void editUserNickname(UserNicknameEditReq userNicknameEditReq) {

        User user = userRepository.findByUserId(userNicknameEditReq.getUserId()).get();

        user.updateUserNickname(userNicknameEditReq.getUserNickname());

        userRepository.save(user);

    }
}
