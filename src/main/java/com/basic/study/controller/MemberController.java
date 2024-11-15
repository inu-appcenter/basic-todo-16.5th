package com.basic.study.controller;

import com.basic.study.dto.MemberReq;
import com.basic.study.dto.MemberRes;
import com.basic.study.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
@Tag(name = "Member", description = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(description = "회원가입")
    @ApiResponse(responseCode = "200", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = MemberRes.class)))
    @ApiResponse(responseCode = "500", description = "이미 존재하는 아이디")
    public ResponseEntity<MemberRes> signup(@RequestBody MemberReq memberReq) {
        return ResponseEntity.ok(memberService.signup(memberReq));
    }

    @PostMapping("/login")
    @Operation(description = "로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = MemberRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 아이디 / 비밀번호 불일치")
    public ResponseEntity<MemberRes> login(@RequestBody MemberReq memberReq) {
        return ResponseEntity.ok(memberService.login(memberReq));
    }

    @GetMapping("/{memberId}")
    @Operation(description = "memberId의 회원 조회")
    @ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = MemberRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 회원")
    public ResponseEntity<MemberRes> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMember(memberId));
    }

    @DeleteMapping("/{memberId}")
    @Operation(description = "회원 탈퇴")
    @ApiResponse(responseCode = "200", description = "탈퇴 성공")
    @ApiResponse(responseCode = "500", description = "존재하지 않는 회원")
    public ResponseEntity<String> delete(@PathVariable Long memberId) {
        return memberService.deleteMember(memberId)
                ? ResponseEntity.ok("탈퇴 성공")
                : ResponseEntity.ok("탈퇴 실패, 회원이 존재하지 않습니다");
    }
}
