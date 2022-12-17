package com.lullaby.study.hexagonalkata.infrastructure.inmemory;

import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistory;
import com.lullaby.study.hexagonalkata.domain.model.pointhistory.PointHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class PointHistoryInmemoryRepository implements PointHistoryRepository {

    private final Map<Long, PointHistory> map = new HashMap<>();

    @Override
    public List<PointHistory> findAllByMember(Long memberId) {
        return this.map.values().stream()
                .filter(it -> it.getMember().getId().equals(memberId))
                .toList();
    }

    @Override
    public PointHistory save(PointHistory pointHistory) {
        if (pointHistory.getId() == null ) {
            pointHistory.setId(new Random().nextLong(1L, Integer.MAX_VALUE));
        }
        this.map.put(pointHistory.getId(), pointHistory);
        return pointHistory;
    }
}
