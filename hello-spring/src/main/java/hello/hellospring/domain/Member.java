package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 ID를 자동생성하는걸 IDENTITY 라 부름.
    private Long id;

    @Column(name="name")//DB의 컬럼명이 다를때 기술
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
