package hello.hellospring.domain;


import javax.persistence.*;

// jpa orm 기술임
// 인스턴스랑 매핑이 되야함
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 디비가 알아서 값을 생성해주는거 오토 increse
    private Long id;

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
