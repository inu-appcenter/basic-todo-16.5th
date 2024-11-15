package com.basic.study.service;

import com.basic.study.domain.Member;
import com.basic.study.dto.MemberReq;
import com.basic.study.dto.MemberRes;
import com.basic.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberRes signup(MemberReq memberReq) {
        // 이미 존재하는 회원
        if (memberRepository.existsByEmail(memberReq.getEmail())) throw new NullPointerException();

        Member member = Member.builder()
                .email(memberReq.getEmail())
                .password(memberReq.getPassword())
                .build();
        memberRepository.save(member);
        return MemberRes.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .build();
    }

    public MemberRes login(MemberReq memberReq) {
        Member member = memberRepository.findByEmail(memberReq.getEmail()).orElseThrow(
                // 해당 회원 존재 X
                NullPointerException::new
        );
        // 비밀번호 불일치
        if (!memberReq.getPassword().equals(member.getPassword())) throw new NullPointerException();
        return MemberRes.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .build();
    }

    public MemberRes getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("해당 회원을 찾을 수 없습니다."));

        return MemberRes.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .build();
    }

    public boolean deleteMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) throw new NullPointerException();
        memberRepository.deleteById(memberId);
        return true;
    }

}