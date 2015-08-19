package com.jei.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jei.spring.domain.user.Authorities;
import com.jei.spring.domain.user.Member;
import com.jei.spring.repository.AuthoritiesRepository;

@Service
public class AuthoritiesService {
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public Member findMemberById(String memberId){
		return authoritiesRepository.findMemberById(memberId);
	}
	
	public List<Authorities> findPermissionById(String memberId){
		return authoritiesRepository.findPermissionById(memberId);
	}
	
}
