package org.koreait.repository;

import org.koreait.entities.BoardData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData,Long>, QuerydslPredicateExecutor {

    //엔티티 Fetch Join
    @Query("SELECT b FROM BoardData b LEFT JOIN FETCH b.member") //즉시 로딩
    List<BoardData> findBoardDatas();
}
