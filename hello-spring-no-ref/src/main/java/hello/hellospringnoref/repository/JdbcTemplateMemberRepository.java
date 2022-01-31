package hello.hellospringnoref.repository;

import hello.hellospringnoref.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) { // required dataSource
        jdbcTemplate = new JdbcTemplate(dataSource); // create jdbcTemplate instance with dataSource
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name", member.getName());

        member.setId(jdbcInsert.executeAndReturnKey(parameter).longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM member WHERE id = ?", rowMapper(), id)
                .stream()
                .findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM member WHERE name = ?", rowMapper(), name)
                .stream()
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member",rowMapper());
    }

    private RowMapper<Member> rowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
