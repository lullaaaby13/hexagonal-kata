package com.lullaby.study.hexagonalkata.domain.model.pointhistory;

import java.util.List;

public interface PointHistoryRepository {

    List<PointHistory> findAllByMember(Long memberId);
    PointHistory save(PointHistory pointHistory);

}
