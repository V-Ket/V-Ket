package com.vket.api.controller;

import com.vket.api.request.DealAddReq;
import com.vket.api.request.SessionPostReq;
import com.vket.api.response.MyBuyListRes;
import com.vket.api.response.MySellListRes;
import com.vket.api.response.SessionGetRes;
import com.vket.api.service.DealService;
import com.vket.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Deal API", tags = {"Deal"})
@RequestMapping("/deal")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class DealController {

    @Autowired
    private DealService dealService;

    //Deal 등록
    @PostMapping("/regist")
    @ApiOperation(value = "Deal 등록", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addDeal(@RequestBody DealAddReq dealAddReq) {

        if (dealService.addDeal(dealAddReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(202).body(BaseResponseBody.of(202, "돈부족"));
        }
    }

    @PutMapping("/updateDeal/{dealId}")
    @ApiOperation(value = "Deal Update", notes = "")
    public ResponseEntity<Long> updateDeal(@PathVariable("dealId") Long dealId) {
        Long purchaseStatus = dealService.updateDeal(dealId);
        return new ResponseEntity<Long>(purchaseStatus, HttpStatus.OK);
    }

    @PutMapping("/movecredit/{dealId}")
    @ApiOperation(value = "구매확정", notes = "")
    public ResponseEntity<? extends BaseResponseBody> moveCredit(@PathVariable("dealId") Long dealId) {
        if (dealService.moveCredit(dealId)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }

    @PutMapping("/cancelDeal/{dealId}")
    @ApiOperation(value = "cancel Deal", notes = "")
    public ResponseEntity<Long> cancelDeal(@PathVariable("dealId") Long dealId) {
        Long purchaseStatus = dealService.cancelDeal(dealId);
        return new ResponseEntity<Long>(purchaseStatus, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDeal/{dealId}")
    @ApiOperation(value = "delete Deal", notes = "")
    public ResponseEntity<? extends BaseResponseBody> deleteDeal(@PathVariable("dealId") Long dealId) {
        if (dealService.deleteDeal(dealId)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        }else{
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }

    @GetMapping("/selllist/{userId}")
    @ApiOperation(value = "판매 목록", notes = "")
    public ResponseEntity<List<MySellListRes>> sellList(@PathVariable("userId") String userId) {
        List<MySellListRes> mySellListResList = dealService.mySellList(userId);
        return new ResponseEntity<>(mySellListResList, HttpStatus.OK);
    }

    @GetMapping("/buylist/{userId}")
    @ApiOperation(value = "구매 목록", notes = "")
    public ResponseEntity<List<MyBuyListRes>> buyList(@PathVariable("userId") String userId) {
        List<MyBuyListRes> myBuyListResList = dealService.myBuyList(userId);
        return new ResponseEntity<>(myBuyListResList, HttpStatus.OK);
    }
}
