package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRepository(DataSource dataSource) { // required dataSource
        jdbcTemplate = new JdbcTemplate(dataSource); // create jdbcTemplate instance with dataSource
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // return created PK
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper(),id)
                .stream()
                .findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM member WHERE name = ?", memberRowMapper(),name)
                .stream()
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member",memberRowMapper());
    }

    // for row parsing
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
