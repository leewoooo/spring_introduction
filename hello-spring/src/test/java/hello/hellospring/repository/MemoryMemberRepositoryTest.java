package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        System.out.println("after Each Hit");
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        memberRepository.save(member);

        //then
        Optional<Member> selectedMember = memberRepository.findByName(member.getName());

        assertThat("spring").isEqualTo(selectedMember.get().getName());
    }

    @Test
    void findById() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member saveMember = memberRepository.save(member);

        //when
        Optional<Member> selectedMember = memberRepository.findById(saveMember.getId());

        //then
        assertThat("spring").isEqualTo(selectedMember.get().getName());
    }

    @Test
    void findByName() {
        //given
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        //when
        Optional<Member> selectedMember = memberRepository.findByName("spring");

        //then
        assertThat("spring").isEqualTo(selectedMember.get().getName());
    }

    @Test
    void findAll() {
        //given
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");

        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(2).isEqualTo(members.size());
    }
}