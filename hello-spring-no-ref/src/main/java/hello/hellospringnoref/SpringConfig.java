package hello.hellospringnoref;

import hello.hellospringnoref.repository.DataJpaMemberRepository;
import hello.hellospringnoref.repository.JpaMemberRepository;
import hello.hellospringnoref.repository.MemberRepository;
import hello.hellospringnoref.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager em;

//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private final EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final DataJpaMemberRepository memberRepository;

    public SpringConfig(DataJpaMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
        return memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
}
