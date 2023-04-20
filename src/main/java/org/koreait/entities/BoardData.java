package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

//게시판
@Data
@Entity @Builder
@NoArgsConstructor @AllArgsConstructor //기본 생성자가 필요
public class BoardData  extends BaseEntity{
    @Id@GeneratedValue
    private Long id; //게시글 번호
    @Column(nullable = false)
    private String subject;//제목
    @Lob
    @Column(nullable = false)
    private String content;//내용
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne관계 매핑
    @JoinColumn(name="userNo") // 관계의 주인!(외래키) 변경시, 회원 번호가 변경되어 다른 회원의 게시글이 된다
    private Member member;
}
