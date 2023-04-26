package com.smhrd.mapper;

import com.smhrd.domain.Member;

// @Mapper : 이 인터페이스를 mybatis mapper interface로 지정
@org.apache.ibatis.annotations.Mapper // import가 아닌 이유 class 이름과 동일하기 떄문에
public interface Mapper {
	// 회원가입
	public int memberJoin(Member m);
	// 로그인	
	public Member memberLogin(Member m);
}
