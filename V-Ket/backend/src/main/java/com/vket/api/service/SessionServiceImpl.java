package com.vket.api.service;

import com.vket.api.request.SessionPostReq;
import com.vket.api.response.SessionGetRes;
import com.vket.db.entity.Session;
import com.vket.db.repository.SessionRepository;
import com.vket.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean addSession(SessionPostReq sessionPostReq) {

        String newSessionName = sessionPostReq.getBuyerId() + sessionPostReq.getSellerId();
        Optional<Session> session = sessionRepository.findBySessionName(newSessionName);

        if (session.isPresent()){
            return false;
        }else{

            sessionRepository.save(Session.builder()
                    .buyerId(sessionPostReq.getBuyerId())
                    .user(userRepository.findByUserId(sessionPostReq.getSellerId()).get())
                    .sessionName(newSessionName)
                    .build());
            return true;
        }

    }

    @Override
    public List<SessionGetRes> sessionList(String userId) { // 판매자 id로 찾아온다.
        List<SessionGetRes> sessionGetResList = new ArrayList<>();
        List<Session> sessions = sessionRepository.findByUser_UserId(userId);
        for (Session s : sessions){
            sessionGetResList.add(SessionGetRes.builder()
                    .sessionName(s.getSessionName())
                    .buyerId(s.getBuyerId())
                    .sellerId(s.getUser().getUserId())
                    .build());
        }
        return sessionGetResList;
    }

    @Override
    public boolean deleteSession(String sessionName) {
        sessionRepository.deleteBySessionName(sessionName);
        return true;
    }
}
