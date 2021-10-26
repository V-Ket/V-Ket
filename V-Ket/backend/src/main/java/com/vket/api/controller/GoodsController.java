package com.vket.api.controller;

import com.vket.api.response.GoodsRes;
import com.vket.api.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "상품 API", tags = {"Goods"})
@RequestMapping("/goods")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    // 전체 상품 리스트
    @GetMapping("/allGoods")
    @ApiOperation(value = "상품 전체 가져오기", notes = "")
    public ResponseEntity<List<GoodsRes>> findAllGoods(){

        List<GoodsRes> goodsList = goodsService.findAllGoods();

        return new ResponseEntity<List<GoodsRes>>(goodsList, HttpStatus.OK);
    }
}
