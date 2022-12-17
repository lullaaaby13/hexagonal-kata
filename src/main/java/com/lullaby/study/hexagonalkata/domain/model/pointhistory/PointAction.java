package com.lullaby.study.hexagonalkata.domain.model.pointhistory;

public enum PointAction {
    WRITE_ARTICLE(3)
    , WRITE_COMMENT(1)
    , PURCHASE_ICON(-10);
    private Integer value;

    PointAction(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
