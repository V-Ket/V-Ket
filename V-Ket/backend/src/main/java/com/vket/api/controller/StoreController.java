package com.vket.api.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.vket.api.request.*;
import com.vket.api.response.StoreInfoRes;
import com.vket.api.service.StoreService;
import com.vket.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Store API", tags = {"Store"})
@RequestMapping("/store")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class StoreController {

    @Autowired
    private StoreService storeService;

    //상점 등록
    @PostMapping("/regist")
    @ApiOperation(value = "상점 등록", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addStore(@RequestBody StorePostReq storePostReq) {
        System.out.println();System.out.println();System.out.println();
        System.out.println();
        System.out.println(storePostReq.getIslandId());
        System.out.println();
        System.out.println();System.out.println();System.out.println();
        if (storeService.addStore(storePostReq)){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }

    //상점 정보 수정 (한번에)
    @PutMapping("/storeUpdateAll")
    @ApiOperation(value = "상점 정보 수정 (한번에)", notes = "")
    public ResponseEntity<? extends BaseResponseBody> updateStoreAll(@RequestBody StoreUpdateAllReq storeUpdateAllReq) {

        if (storeService.updateStoreAll(storeUpdateAllReq)){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }
    //상점 이름 수정
    @PutMapping("/storeUpdateName")
    @ApiOperation(value = "상점 이름 수정", notes = "")
    public ResponseEntity<? extends BaseResponseBody> updateStoreName(@RequestBody StoreUpdateNameReq storeUpdateNameReq) {

        if (storeService.updateStoreName(storeUpdateNameReq)){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }
    //상점 설명 수정
    @PutMapping("/storeUpdateContent")
    @ApiOperation(value = "상점 설명 수정", notes = "")
    public ResponseEntity<? extends BaseResponseBody> updateStoreContent(@RequestBody StoreUpdateContentReq storeUpdateContentReq) {

        if (storeService.updateStoreContent(storeUpdateContentReq)){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }
    //상점 URL 수정
    @PutMapping("/storeUpdateUrl")
    @ApiOperation(value = "상점 URL 수정", notes = "")
    public ResponseEntity<? extends BaseResponseBody> updateStoreUrl(@RequestBody StoreUpdateUrlReq storeUpdateUrlReq) {

        if (storeService.updateStoreUrl(storeUpdateUrlReq)){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }

    }
    //모든 상점 정보 보기 + 나의 상점 위치
    @GetMapping("/info")
    @ApiOperation(value = "모든 상점 정보 보기 + 나의 상점 위치", notes = "")
    public ResponseEntity<List<StoreInfoRes>> storeInfos(){
        return new ResponseEntity<List<StoreInfoRes>>(storeService.allStoreInfo(), HttpStatus.OK);
    }

    //오픈, 클로스 선택 ( 오픈 1 클로즈 0 )
    @GetMapping("/status/{storeId}")
    @ApiOperation(value = "오픈/클로즈 선택", notes = "")
    public ResponseEntity<Integer> status(@PathVariable("storeId") Long storeId){
        return new ResponseEntity<Integer>(storeService.openClose(storeId), HttpStatus.OK);
    }

    //상점 닫기(삭제)
    @DeleteMapping("/delete/{storeId}")
    @ApiOperation(value = "상점 닫기(삭제)", notes = "")
    public ResponseEntity<? extends BaseResponseBody> deleteStore(@PathVariable("storeId") Long storeId){

        storeService.deleteStore(storeId);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));

    }
}
