package com.vket.api.controller;

import com.vket.api.request.SessionPostReq;
import com.vket.api.request.StorePostReq;
import com.vket.api.response.SessionGetRes;
import com.vket.api.response.StoreInfoRes;
import com.vket.api.service.SessionService;
import com.vket.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Session API", tags = {"Session"})
@RequestMapping("/session")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    //session 등록
    @PostMapping("/regist")
    @ApiOperation(value = "세션 등록", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addSession(@RequestBody SessionPostReq sessionPostReq) {

        if (sessionService.addSession(sessionPostReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }

    @GetMapping("/getlist/{userId}")
    @ApiOperation(value = "판매자 기준 세션 목록", notes = "")
    public ResponseEntity<List<SessionGetRes>> sesssionList(@PathVariable("userId") String userId) {
        List<SessionGetRes> sessionGetResList = sessionService.sessionList(userId);
        return new ResponseEntity<List<SessionGetRes>>(sessionGetResList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{sessionName}")
    @ApiOperation(value = "세션이름으로 세션 지우기", notes = "")
    public ResponseEntity<? extends BaseResponseBody> deleteSession(@PathVariable("sessionName") String sessionName){

        sessionService.deleteSession(sessionName);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));

    }

}
