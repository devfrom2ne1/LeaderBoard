package com.example.LeaderBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankingService {

    private static final String LEADERBOARD_KEY = "leaderBoard";

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 특정 유저의 점수를 입력하는 서비스
     * @param userId
     * @param score
     * @return
     */
    public boolean setUserScore(String userId, int score){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

    /**
     * 특정유저의 랭킹을 조회하는 서비스
     * @param userId
     * @return
     */
    public long getUserRank(String userId){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY, userId);

        return rank;
    }

    /**
     * 범위 기반 조회
     * @param limit
     * @return
     */
    public List<String> getTopRanks(int limit){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit-1);

        return new ArrayList<>(rangeSet);
    }
}
