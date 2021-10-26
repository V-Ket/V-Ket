package com.vket.api.controller;

import com.vket.api.request.GoodsAddReq;
import com.vket.api.request.GoodsUpdateReq;
import com.vket.api.response.GoodsRes;
import com.vket.api.service.GoodsService;
import com.vket.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Api(value = "상품 API", tags = {"Goods"})
@RequestMapping("/goods")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    // 상품 전체 정보
    @GetMapping("/allGoods")
    @ApiOperation(value = "상품 전체 가져오기", notes = "")
    public ResponseEntity<List<GoodsRes>> findAllGoods(){

        List<GoodsRes> goodsList = goodsService.findAllGoods();

        return new ResponseEntity<List<GoodsRes>>(goodsList, HttpStatus.OK);
    }

    // 상점 별 전체 상품 정보
    @GetMapping("/store/{storeId}")
    @ApiOperation(value = "상점별 상품 전체 가져오기", notes = "")
    public ResponseEntity<List<GoodsRes>> findGoodsByStoreId(@PathVariable("storeId") Long storeId){

        List<GoodsRes> goodsListByStoreId = goodsService.findGoodsByStoreId(storeId);

        return new ResponseEntity<List<GoodsRes>>(goodsListByStoreId, HttpStatus.OK);
    }

    // 선택된 상품의 상세 정보
    @GetMapping("/{goodsId}")
    @ApiOperation(value = "선택된 상품 정보 조회오기", notes = "")
    public ResponseEntity<GoodsRes> findGoodsByGoodsId(@PathVariable("goodsId") Long goodsId){
        GoodsRes goodsRes = goodsService.findGoodsByGoodsId(goodsId);
        return new ResponseEntity<GoodsRes>(goodsRes, HttpStatus.OK);
    }

    // 상품 정보 수정하기
    @Transactional
    @PutMapping("")
    @ApiOperation(value = "상품 정보 한꺼번에 수정하기", notes = "")
    public ResponseEntity<? extends BaseResponseBody> updateGoods(@RequestBody @ApiParam(value = "상품 정보", required = true) GoodsUpdateReq goodsUpdateReq){
        if (goodsService.updateGoodsInfo(goodsUpdateReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }

    // 상품 등록하기
    @PostMapping("")
    @ApiOperation(value = "새 상품 등록", notes = "")
    public ResponseEntity<? extends BaseResponseBody> addGoods(@RequestBody @ApiParam(value = "새 상품 정보", required = true) GoodsAddReq goodsAddReq){
        if (goodsService.addGoods(goodsAddReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }

    // 상품 삭제하기
    @DeleteMapping("/{goodsId}")
    @ApiOperation(value = "특정 ID 상품 삭제하기", notes = "")
    public ResponseEntity<? extends BaseResponseBody> delteGoodsById(@PathVariable("goodsId") Long goodsId){
        if (goodsService.deleteByGoodsId(goodsId)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } else {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "Fail"));
        }
    }
}
