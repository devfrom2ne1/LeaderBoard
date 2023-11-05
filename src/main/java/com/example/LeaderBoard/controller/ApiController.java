package com.example.LeaderBoard.controller;

import com.example.LeaderBoard.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/setUserScore")
    public Boolean setUserScore(
            @RequestParam String userId,
            @RequestParam int score
    ){
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/getUserRank")
    public long getUserRank(
            @RequestParam String userId
    ){
        return rankingService.getUserRank(userId);
    }
    @GetMapping("/getTopRanks")
    public List<String> getTopRanks(
            @RequestParam int limit
    ){
        return rankingService.getTopRanks(limit);
    }

}
