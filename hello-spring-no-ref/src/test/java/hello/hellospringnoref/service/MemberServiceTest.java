package hello.hellospringnoref.service;

import hello.hellospringnoref.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Member savedMember = memberService.join(member.getName());

        //then
        assertThat(savedMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void join_중복조회() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member.getName());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2.getName()));

        //then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("이미 존재하는 이름입니다. name: " + member2.getName());
    }

    @Test
    void findOneById() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member savedMember = memberService.join(member.getName());


        //when
        Member selectedMember = memberService.findOneById(savedMember.getId());

        //then
        assertThat(selectedMember.getName()).isEqualTo("spring");
    }

    @Test
    void findOneById_NotExist() {
        //given
        Long givenId = 999L;

        //when
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> memberService.findOneById(givenId));

        //then
        assertThat(e.getMessage()).isEqualTo("맴버를 찾을 수 없습니다. id: " + givenId);
    }

    @Test
    void findOneByName() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member savedMember = memberService.join(member.getName());

        //when
        Member selectedMember = memberService.findOneByName(savedMember.getName());

        //then
        assertThat(selectedMember.getName()).isEqualTo("spring");
    }

    @Test
    void findOneByName_NotExist(){
        //given
        String givenName = "foobar";

        //when
        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> memberService.findOneByName(givenName));

        //then
        assertThat(e.getMessage()).isEqualTo("멤버를 찾을 수 없습니다. name: " + givenName);
    }

    @Test
    void findAll() {
        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring2");

        memberService.join(member.getName());
        memberService.join(member2.getName());

        //when
        List<Member> members = memberService.findAll();

        //then
        assertThat(members.size()).isEqualTo(2);
        members
                .forEach(m -> System.out.println(m.getName()));
    }
}