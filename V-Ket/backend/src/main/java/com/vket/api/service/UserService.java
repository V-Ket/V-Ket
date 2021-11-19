package com.vket.api.service;

import com.vket.api.request.*;
import com.vket.api.response.UserFavortieGetRes;
import com.vket.api.response.UserLoginPostRes;

import java.util.List;

public interface UserService {
    void createUser(UserPostReq userPostReq);

    boolean userIdDuplicate(String userId);

    boolean userNicknameDuplicate(String userId);

    UserLoginPostRes userLogin(UserLoginPostReq userLoginPostReq);

    void editUserNickname(UserNicknameEditReq userNicknameEditReq);

    List<UserFavortieGetRes> getFavoriteList(Long userSeq);

    void editUserCharacter(UserCharactorEditReq userCharactorEditReq);

    void addFavorite(UserFavorateReq userFavoratePostReq);

    void deleteFavorite(UserFavorateReq userFavorateReq);

    Long addCredit(CreditAddReq creditAddReq);
}
