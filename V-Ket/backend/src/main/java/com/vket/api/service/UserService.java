package com.vket.api.service;

import com.vket.api.request.UserNicknameEditReq;
import com.vket.api.request.UserLoginPostReq;
import com.vket.api.request.UserPostReq;
import com.vket.api.response.UserLoginPostRes;

public interface UserService {
    void createUser(UserPostReq userPostReq);

    boolean userIdDuplicate(String userId);

    boolean userNicknameDuplicate(String userId);

    UserLoginPostRes userLogin(UserLoginPostReq userLoginPostReq);

    void editUserNickname(UserNicknameEditReq userNicknameEditReq);
}
