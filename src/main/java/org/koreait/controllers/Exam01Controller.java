package org.koreait.controllers;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.BoardData;
import org.koreait.entities.Member;
import org.koreait.entities.MemberAddress;
import org.koreait.entities.QBoardData;
import org.koreait.repository.BoardDataRepository;
import org.koreait.repository.MemberAddressRepository;
import org.koreait.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@Log //lombok
@RestController
@RequiredArgsConstructor
public class Exam01Controller {
    private final BoardDataRepository boardDataRepository;
    private final MemberRepository memberRepository;
    private  final EntityManager em;
    private final MemberAddressRepository memberAddressRepository;
    @GetMapping("/ex01")
    public void ex01(){
        Member member = Member.builder()
                .userId("user01")
                .userPw("123456")
                .userNm("사용자01")
                .mobile("01000000000")
                .email("user01@user.org")
                .build();
        List<BoardData> datas = new ArrayList<>(); //한번에 여러개 출력 List사용
        member = memberRepository.saveAndFlush(member); //영속성에 담긴 member
        for(int i=1; i<=10;i++){
            BoardData data = BoardData.builder()
                    .subject("제목" +i)
                    .content("내용" + i)
                    .member(member)
                    .build();
            datas.add(data);
        }
        boardDataRepository.saveAllAndFlush(datas);
    }

    @GetMapping("/ex02") //회원 목록 조회
    public void ex02(){
        BoardData data =boardDataRepository.findById(1L).orElse(null); //FetchType.LAZY 시 BoardData 엔티티만 조회
//        log.info(data.toString());
//        Member member = data.getMember();
//        log.info(member.toString());

        Member member = data.getMember(); //데이터 조회시에만
        String userId = member.getUserId(); //쿼리 실행
        log.info(userId);
    }
    @GetMapping("/ex03") //회원 목록 조회
    public void ex03(){
        Member member = memberRepository.findById(1L).orElse(null);
        List<BoardData> boardData = member.getBoardData();

        boardData.stream().forEach(System.out::println);
    }
    @GetMapping("/ex04")
    public void ex04(){
        List<BoardData> boardDatas = boardDataRepository.findAll(); //쿼리 한번
        for(BoardData boardData : boardDatas){
            Member member = boardData.getMember();
            String userId = member.getUserId(); //조회시 한번더 쿼리 실행 =?> 1+N 번 쿼리가 조회 된다.
            String userNm = member.getUserNm();
            log.info("userId" + userId + "userNm"+ userNm);
        }
    }
    @GetMapping("/ex05")//즉시 로딩으로 조인
    public void ex05(){
        List<BoardData> boardData = boardDataRepository.findBoardDatas();
    }

    @GetMapping("/ex06")
    @Transactional
    //Query를 이용한 검색
    public void ex06(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBoardData boardData = QBoardData.boardData;
        JPAQuery<BoardData> query = queryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .orderBy(boardData.regDt.desc())
                .fetchJoin();
        List<BoardData> datas = query.fetch();
        datas.stream().forEach(System.out::println);
    }
    @GetMapping("/ex07")
    public void ex07(){
        MemberAddress address = MemberAddress.builder()
                .addr1("주소1")
                .addr2("주소2")
                .zipCode("10000")
                .build();

        address = memberAddressRepository.saveAndFlush(address);

        Member member = Member.builder()
                .userId("user02")
                .userPw("123456")
                .userNm("사용자02")
                .address(address)
                .email("user02@user.org")
                .mobile("01000000000")
                .build();

        memberRepository.save(member);
    }
    //회원데이터 조회
    @GetMapping("/ex08")
    public void ex08(){
        Member member = memberRepository.findById(2L).orElse(null);
        MemberAddress address = member.getAddress();
        log.info(address.toString());
    }

    @GetMapping("/ex09")
    public void ex09(){
        MemberAddress address = memberAddressRepository.findById(1L).orElse(null);
        Member member = address.getMember();
        log.info(member.toString());
    }

    @GetMapping("/ex10")
    public void ex10(){
        Member member = memberRepository.findById(1L).orElse(null);
        memberRepository.delete(member); //연관된 자식 엔티티도 삭제
        memberRepository.flush();
    }
}
