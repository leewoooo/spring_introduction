package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
    }



    @Bean
    public MemberRepository memberRepository(){
//        return new JdbcTemplateRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(springDataJpaMemberRepository);
    }
}
