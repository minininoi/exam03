package org.koreait.entities;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberAddress extends BaseEntity{
    @Id @GeneratedValue
    private Long id;
    @Column(length = 10,nullable = false)
    private String zipCode;//우편번호
    @Column(length = 100, nullable = false)
    private String addr1;//주소
    @Column(length = 100)
    private String addr2; //나머지 주소
    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)//mappedBy = "address" , 관계의 주인 Member의 address;
    Member member;
}
