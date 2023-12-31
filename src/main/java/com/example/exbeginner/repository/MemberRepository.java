package com.example.exbeginner.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.exbeginner.domain.Member;

@Repository
public class MemberRepository {
  @Autowired
  NamedParameterJdbcTemplate template;

  private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
    Member member = new Member();
    member.setId(rs.getInt("id"));
    member.setName(rs.getString("name"));
    member.setAge(rs.getInt("age"));
    member.setDep_id(rs.getInt("dep_id"));
    return member;
  };

  public List<Member> search(String name) {
    String sql = "select id, name, age, dep_id from members where name like :name;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("name", '%' + name + '%');

    List<Member> memberList = template.query(sql, param, MEMBER_ROW_MAPPER);

    return memberList;
  }
}
