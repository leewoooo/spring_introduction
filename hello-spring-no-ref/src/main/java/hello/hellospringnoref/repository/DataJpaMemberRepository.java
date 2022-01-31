package hello.hellospringnoref.repository;

import hello.hellospringnoref.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository{
    Optional<Member> findByName(String name);
}
