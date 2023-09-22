package kr.spring.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor  //필수.. 기본생성자(default constructor)
@ToString
public class Member {

	// 이름이 대소문자는 다를수 있어도 같아야한다 그래야 갖고올수 있음
	private int memIdx;
	private String memID;
	private String memPassword;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile;
	
	// 자신의 권한 정보를 저장할 변수 
	// Why List<Auth>?
	// 권한은 여러개가 될 수도 있기 때문에
	private List<Auth> authList;
	
}
