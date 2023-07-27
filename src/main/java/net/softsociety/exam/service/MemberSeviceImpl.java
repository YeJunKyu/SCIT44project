package net.softsociety.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.dao.MemberDAO;
import net.softsociety.exam.domain.Member;

@Slf4j
@Service
public class MemberSeviceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public int join(Member member) {
		String pw = passwordEncoder.encode(member.getMemberpw());
		
		log.debug("암호화 전 : {}", member.getMemberpw());
		log.debug("암호화 후 : {}", pw);
		
		member.setMemberpw(pw);
		
		int n = dao.join(member);
		return n;
	}

   

}
