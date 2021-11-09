package com.vket.api.service;

import com.vket.api.request.SessionPostReq;
import com.vket.api.response.SessionGetRes;

import java.util.List;

public interface SessionService {

    boolean addSession(SessionPostReq sessionPostReq);

    List<SessionGetRes> sessionList(String userId);

    boolean deleteSession(String sessionName);
}
