package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//회원
@Entity
@Data @Builder //빌더 사용시 private 사용 못하기 때문에 사용하기 위해서 @NoArgsConstructor
@NoArgsConstructor @AllArgsConstructor //@NoArgsConstructor만 사용시 ....편법으로 @AllArgsConstructor 동시에 추가
public class Member extends BaseEntity{
    @Id @GeneratedValue //기본키 지정,
    private Long userNo;
    @Column(length = 45, nullable = false, unique = true)
    private String userId;
    @Column(length = 65, nullable = false)
    private String userPw;
    @Column(length = 45, nullable = false)
    private String userNm;
    @Column(length = 100)
    private String email;
    @Column(length = 11)
    private String mobile;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE) //일대다 //mappedBy = "member"관계의 주인 BoardData의 member;
    private List<BoardData> boardData = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)//일대일 , 지연전략
    @JoinColumn(name = "addressId") //관계의 주인(외래키)
    private MemberAddress address;

}
