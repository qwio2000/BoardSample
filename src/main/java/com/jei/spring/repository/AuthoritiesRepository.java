package com.jei.spring.repository;

import java.util.List;

import com.jei.spring.domain.user.Authorities;
import com.jei.spring.domain.user.Member;

public interface AuthoritiesRepository {
	public Member findMemberById(String memberId);
	
	public List<Authorities> findPermissionById(String memberId);
}
