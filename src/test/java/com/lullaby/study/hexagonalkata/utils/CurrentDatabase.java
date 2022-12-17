package com.lullaby.study.hexagonalkata.utils;

public enum CurrentDatabase {

    MYSQL("SET foreign_key_checks = 0","SET foreign_key_checks = 1")
    , MARIADB("SET foreign_key_checks = 0","SET foreign_key_checks = 1")
    , H2("SET REFERENTIAL_INTEGRITY FALSE", "SET REFERENTIAL_INTEGRITY TRUE");

    private final String disableConstraints;
    private final String enableConstraints;

    CurrentDatabase(String disableConstraints, String enableConstraints) {
        this.disableConstraints = disableConstraints;
        this.enableConstraints = enableConstraints;
    }

    public String getDisableConstraints() {
        return disableConstraints;
    }

    public String getEnableConstraints() {
        return enableConstraints;
    }
}
