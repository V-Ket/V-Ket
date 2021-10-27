package com.vket.api.controller;

import com.vket.api.service.DealService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Purchase API", tags = {"Purchase"})
@RequestMapping("/purchase")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class DealController {

    private DealService dealService;

    //구매시 상점 등록

}
