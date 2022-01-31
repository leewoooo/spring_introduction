package hello.hellospringnoref.service;

import hello.hellospringnoref.domain.Member;
import hello.hellospringnoref.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(String name){
        memberRepository.findByName(name)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 이름입니다. name: " + name);
                });

        Member member = new Member();
        member.setName(name);

        return memberRepository.save(member);
    }

    public Member findOneById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("맴버를 찾을 수 없습니다. id: "+id));
    }

    public Member findOneByName(String name){
        return memberRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다. name: " + name));
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
