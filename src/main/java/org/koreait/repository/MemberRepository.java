package org.koreait.repository;

import org.koreait.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member,Long>, QuerydslPredicateExecutor { //Q형태의  클래스가 생성된다.

}
