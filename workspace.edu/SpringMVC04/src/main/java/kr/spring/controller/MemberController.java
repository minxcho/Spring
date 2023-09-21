package kr.spring.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.spring.entity.Member;
import kr.spring.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/joinForm.do")
	public String joinForm() {
		
		return "member/joinForm";
	}
	
	@RequestMapping("/registerCheck.do")
	public @ResponseBody int registerCheck(@RequestParam("memID") String memID) {
		
		Member m = mapper.registerCheck(memID);
		// m == null -> 아이디 사용가능
		// m != null -> 아이디 사용불가능
		if(m != null || memID.equals("")) {
			return 0;
		} else {
			return 1;
		}
		
	}
	
	
	@RequestMapping("/join.do")
	public String join(Member m, RedirectAttributes rttr, HttpSession session) {
		
		System.out.println("회원가입 기능 요청");
		
		// 유효성 검사 (back에서해도되고 front에서 해도되는데, 둘다 해주는게 좋다)
		if(m.getMemID() == null || m.getMemID().equals("") 
				|| m.getMemPassword() == null || m.getMemPassword().equals("")
				|| m.getMemName() == null || m.getMemName().equals("") 
				|| m.getMemAge() == 0 || m.getMemEmail() == null || m.getMemEmail().equals("")) {
			// 회원가입을 할 수 없다. 하나라도 누락되어 있기 때문
			
			// 실패시 joinForm.do로 msgType과 msg내용을 보내야함
			// msgType : 실페메세지, msg : 모든내용을 입력하세요
			// forward방식은 model을 써서 보내지만, redirect는 model 못씀
			// RedirectAttributes - 리다이렉트 방식으로 이동할 때 보낼 데이터를 저장하는 객체
			// 일회성으로 사용... 
			
			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			
			return "redirect:/joinForm.do";
			
		} else {
			// 회원가입을 시도할 수 있는 부분
			
			m.setMemProfile("");
			int cnt = mapper.join(m);
			
			if(cnt == 1) {
				System.out.println("회원가입 성공!");
				rttr.addFlashAttribute("msgType", "성공메세지");
				rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
				// 회원가입 성공 시 로그인처리까지 시키기
				
				session.setAttribute("mvo", m);
				
				return "redirect:/";
			} else {
				System.out.println("회원가입 실패!");
				rttr.addFlashAttribute("msgType", "실패메세지");
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다.");
				// 회원가입 후 index.jsp로 이동시키세요
				return "redirect:/joinForm.do";
				
			}
		}
		
	}
	
	@RequestMapping("/loginForm.do")
	public String loginForm() {
		return "member/loginForm";
	}
	
	@RequestMapping("/login.do")
	public String login(Member m, RedirectAttributes rttr, HttpSession session) {
		
		// 문제 .
		// mapper에 login이라는 메서드 이름으로 로그인 기능을 수행하시오
		// 단, 로그인성공시 -> index.jsp로 이동 후 로그인에 성공했습니다 modal창 띄우기
		//    로그인실패시 -> login.jsp로 이동 후 로그인에 실패했습니다 modal창 띄우기
		
		Member mvo = mapper.login(m);
		
		if(mvo != null) {
			System.out.println("로그인 성공!");
			rttr.addFlashAttribute("msgType", "성공메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다.");
			
			session.setAttribute("mvo", mvo);  // header에서 mvo로 세션에 저장된값 꺼내기로 했으니까 동일하게 mvo로
			return "redirect:/";
		} else {
			System.out.println("로그인 실패!");
			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "로그인에 실패했습니다.");
			
			
			return "redirect:/loginForm.do";
			
		}
	
	}
	
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 성공");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/updateForm.do")
	public String updateForm() {
		return "member/updateForm";
	}
	
	@RequestMapping("/update.do")
	public String update(Member m, RedirectAttributes rttr, HttpSession session) {
		
		// 문제.
		// mapper의 update메서드를 통해 해당회원의 정보를 수정하시오

		// 조건1. 하나라도 입력안한 데이터가 있으면 updateForm.jsp로 다시 돌려보내면서
		//		 updateForm.jsp에서는 "모든 내용을 입력하세요."라는 모달창을 띄우세요
		// 조건2. 회원수정에 실패했을 때 에는 updateForm.jsp로 다시 돌려보내면서
		//     	 updateForm.jsp에서는 "회원수정이 실패했습니다." 라는 모달창을 띄우세요
		// 조건3. 회원수정에 성공했을 때에는 index.jsp로 다시 돌려보내면서
		// 		 index.jsp에서는 "회원정보 수정에 성공했습니다."라는 모달창을 띄우세요
		
		
		// 1.
		if(m.getMemPassword() == null || m.getMemPassword().equals("")
				|| m.getMemName() == null || m.getMemName().equals("") 
				|| m.getMemAge() == 0 || m.getMemEmail() == null || m.getMemEmail().equals("")) {
			
			rttr.addFlashAttribute("msgType", "실패메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/updateForm.do";
		}
		else {
			// 2.
			// 이 쉬운걸 몰랐넹..... 다시 공부하기
			Member mvo = (Member)session.getAttribute("mvo");
			m.setMemProfile(mvo.getMemProfile());
			
			int cnt = mapper.update(m);

			if (cnt != 0) {
				System.out.println("수정 성공!");
				rttr.addFlashAttribute("msgType", "성공메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 성공했습니다.");
				
				
				session.setAttribute("mvo", m); // header에서 mvo로 세션에 저장된값 꺼내기로 했으니까 동일하게 mvo로
				System.out.println(m.toString());
				return "redirect:/";
			} else {
				System.out.println("수정 실패!");
				rttr.addFlashAttribute("msgType", "실패메세지");
				rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");

				return "redirect:/updateForm.do";

			}
		}

	}
	
	
	@RequestMapping("/imageForm.do")
	public String imageForm() {
		return "member/imageForm";
	}
	
	
	@RequestMapping("/imageUpdate.do")
	public String imageUpdate(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		
		// 파일업로드를 할 수 있게 도와주는 객체 (cos.jar)
		// 파일 업로드를 할 수 있게 도와주는 MultipartRequest 객체를 생성하기 위해서는
		// 5개의 정보가 필요하다.
		// 요청데이터, 저장경로, 최대크기, 인코딩(방식), 파일명 중복제거
		MultipartRequest multi = null;
		
		String savePath = request.getRealPath("resources/upload");
		// 이미지 최대크기
		int fileMaxSize = 10 * 1024 * 1024;
		
		// 기존 해당 프로필 이미지 삭제
		// - 로그인 한 사람의 프로필 값을 가져와야함
		String memID = ((Member)session.getAttribute("mvo")).getMemID(); 
		
		// getMember 메서드는 memID와 일치하는 회원의 정보 (Member)를 가져온다
		String oldImg = mapper.getMember(memID).getMemProfile();

		// 기존의 프로필 사진 삭제
		File oldFile = new File(savePath + "/" + oldImg);
		if(oldFile.exists()) {
			oldFile.delete();
		}
		
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 내가 업로드한 파일 가져오기
		File file = multi.getFile("memProfile");
		if(file != null) { // 업로드가 된 상태
			// System.out.println(file.getName());
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			ext = ext.toUpperCase();
			System.out.println(ext);
			
			if( !(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG")) ) {
				if(file.exists()) {
					file.delete();
					
					System.out.println("파일 에러..");
					rttr.addFlashAttribute("msgType", "실패메세지");
					rttr.addFlashAttribute("msg", "이미지 파일만 가능합니다. (PNG, JPG, GIF)");

					return "redirect:/imageForm.do";
				}
			}
			
		}
		  
		// 업로드한 파일의 이름을 가져오는 코드
		String newProfile = multi.getFilesystemName("memProfile");

		Member mvo = new Member();
		mvo.setMemID(memID);
		mvo.setMemProfile(newProfile);
		
		mapper.profileUpdate(mvo);
		//System.out.println(memID + "/" + newProfile);
		
		// 사진업데이트 후 수정된 회원정보를 다시 가져와서 세션에 담기
		Member m = mapper.getMember(memID);
		session.setAttribute("mvo", m);
		
		System.out.println("프로필 이미지변경 성공!");
		rttr.addFlashAttribute("msgType", "성공메세지");
		rttr.addFlashAttribute("msg", "프로필 이미지 변경에 성공했습니다.");
		
		
		return "redirect:/";
	}
	
	
	
	
	
}
