package kr.spring.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	// 우리가 만든 회원정보 -> Member를
	// Spring Context Holder에 저장하기 위해서는
	// User 형태로 변환 하여서 넣어줘야한다
	// 그걸 해주는 클래스가 바로 CustomUser클래스
	
	
	private Member member;
	
	public CustomUser(Member member) {
		super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + member.getRole().toString()));
		//AuthorityUtils  클래스를 통해서 권한을 배열 형태로 생성하겠다.
		this.member = member;
	}
	
}