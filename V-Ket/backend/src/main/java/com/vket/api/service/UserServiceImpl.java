package com.vket.api.service;

import com.vket.api.request.*;
import com.vket.api.response.UserFavortieGetRes;
import com.vket.api.response.UserLoginPostRes;
import com.vket.config.JwtTokenProvider;
import com.vket.db.entity.Favorite;
import com.vket.db.entity.Store;
import com.vket.db.entity.User;
import com.vket.db.repository.FavoriteRepository;
import com.vket.db.repository.StoreRepository;
import com.vket.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    StoreRepository storeRepository;

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

    @Override
    public List<UserFavortieGetRes> getFavoriteList(Long userSeq) {

        List<Favorite> favorites = favoriteRepository.findByUser_UserSeq(userSeq);
        // 즐겨찾기가 없으면 null 반환
        if(favorites.size() == 0){
            return null;
        }
        // 반환값
        List<UserFavortieGetRes> userFavortieGetRes = new ArrayList<>();
        // 즐겨찾기한 상점에 대해
        for(Favorite f : favorites) {
            Long storeId = f.getStoreId();

            Optional<Store> store = storeRepository.findByStoreId(storeId);
            // 상점이 아직 있다면 반환값들을 넣어준다.
            if (store.isPresent()) {
                userFavortieGetRes.add(UserFavortieGetRes.builder()
                        .storeId(store.get().getStoreId())
                        .storeName(store.get().getStoreName())
                        .islandId(store.get().getIsland().getIslandId())
                        .storeIslandNum(store.get().getStoreIslandNum())
                        .build());
            }
        }

        return userFavortieGetRes;
    }

    @Override
    public void editUserCharacter(UserCharactorEditReq userCharactorEditReq) {

        User user = userRepository.findByUserId(userCharactorEditReq.getUserId()).get();

        user.updateUserCharactor(userCharactorEditReq.getUserCharactor());

        userRepository.save(user);

    }

    @Override
    public void addFavorite(UserFavorateReq userFavorateReq) {

        User user = userRepository.findByUserId(userFavorateReq.getUserId()).get();

        Favorite favorite = Favorite.builder()
                .user(user)
                .storeId(userFavorateReq.getStoreId())
                .build();

        favoriteRepository.save(favorite);

    }

    @Override
    public void deleteFavorite(UserFavorateReq userFavorateReq) {

        User user = userRepository.findByUserId(userFavorateReq.getUserId()).get();

        favoriteRepository.deleteByStoreIdAndUser_UserSeq(userFavorateReq.getStoreId(), user.getUserSeq());

    }

    @Override
    public boolean addCredit(CreditAddReq creditAddReq) {
        User user = userRepository.findByUserId(creditAddReq.getUserId()).get();

        user.updateUserCredit(creditAddReq.getCredit());
        userRepository.save(user);

        return true;
    }

}
