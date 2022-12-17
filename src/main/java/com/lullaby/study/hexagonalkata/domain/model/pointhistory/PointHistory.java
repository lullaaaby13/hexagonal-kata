package com.lullaby.study.hexagonalkata.domain.model.pointhistory;

import com.lullaby.study.hexagonalkata.domain.model.member.Member;

import java.time.LocalDateTime;

public class PointHistory {

    private Long id;
    private Member member;
    private Integer amountOfChange;
    private PointAction pointAction;

    private LocalDateTime createdAt;

    private PointHistory() {}

    public static PointHistory create(Member member, PointAction pointAction) {
        PointHistory pointHistory = new PointHistory();
        pointHistory.member = member;
        pointHistory.amountOfChange = pointAction.getValue();
        pointHistory.pointAction = pointAction;
        pointHistory.createdAt = LocalDateTime.now();
        return pointHistory;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Integer getAmountOfChange() {
        return amountOfChange;
    }

    public PointAction getPointAction() {
        return pointAction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // TODO 테스트 끝나면 지워야 됨
    public void setId(Long id) {
        this.id = id;
    }
}
