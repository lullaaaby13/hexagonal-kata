package com.lullaby.study.hexagonalkata;

import jakarta.persistence.*;

@Table(name = "members")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String account;

    private String password;

    private String nickname;
}
