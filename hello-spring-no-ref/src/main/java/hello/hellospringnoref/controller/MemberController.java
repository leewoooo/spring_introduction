package hello.hellospringnoref.controller;

import hello.hellospringnoref.domain.Member;
import hello.hellospringnoref.dto.SaveMemberRequest;
import hello.hellospringnoref.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/v1/members")
    @ResponseBody
    public Member saveMember(@RequestBody SaveMemberRequest req){
        return memberService.join(req.getName());
    }

    @GetMapping("/api/v1/members/{id}")
    @ResponseBody
    public Member findOneById(@PathVariable("id") Long id){
        return memberService.findOneById(id);
    }

    @GetMapping("/api/v1/members")
    @ResponseBody
    public List<Member> findAllMembers(){
        return memberService.findAll();
    }
}
