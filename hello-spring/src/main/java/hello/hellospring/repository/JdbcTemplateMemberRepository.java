package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate; //Injection을 받을 수 있는 객체는 아니기 때문에 아래와 같은 방법으로 작성

   public JdbcTemplateMemberRepository(DataSource dataSource) {//생성자가 1개이므로 @Autowired 생략.
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);//SimpleJdbcInsert 를 사용하면 쿼리를 짤 필요가 없음.
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name",member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
       return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
         List<Member> result =jdbcTemplate.query("select * from member where id= ?", memberRowMapper(),id);
        return result.stream().findAny();//결과 값이 리스트로 나오기 때문에 Stream 사용.
    }
// 📁 stream().findAny();
//    이름에서 알 수 있듯이 findAny() 메서드를 사용하면 Stream 에서 모든 요소를 찾을 수 있습니다 . 발생 순서에 주의를 기울이지 않고 요소를 찾을 때 이를 사용합니다.
//    이 메서드는 Stream이 비어 있으면 비어 있는 Optional 인스턴스를 반환합니다 .
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =jdbcTemplate.query("select * from member where name= ?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
       return (rs, rowNum) -> { // 기존 return RowMapper<Member>(){ @Override public Member RowNum(Result set rs, int rowNum) throws SQLException {...} 형태를 람다식으로 축약
           Member member = new Member();
           member.setId(rs.getLong("id"));
           member.setName(rs.getString("name"));
           return member;
       };
    }
}
