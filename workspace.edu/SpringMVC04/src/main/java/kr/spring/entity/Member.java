package kr.spring.entity;

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
	
	
	
}
