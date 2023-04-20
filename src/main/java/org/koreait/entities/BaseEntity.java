package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//공통엔티티 : 객체를 생성하지 않고 추상클래스로 만든다.
@Getter @Setter //추상클래스로 @Data X
@MappedSuperclass //공통 클래스를 사용할 때 상위 클래스로 지정 해줘야한다.
@EntityListeners(AuditingEntityListener.class) //이벤트 발생시 수행 => 설정 클래스에서 활성화 시켜줘야 한다.
public abstract class BaseEntity {
    @CreatedDate //엔티티가 처음에 값이 추가 될때 한번 , 수정(update)불가
    @Column(updatable = false) //처음들어간 값을 변경하지 못하게 updatable = false
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt;
    @LastModifiedDate //엔티티 상태에 따라 수정시 사용
    @Column(insertable = false) // 추가(insert) 불가
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modDt;
}
