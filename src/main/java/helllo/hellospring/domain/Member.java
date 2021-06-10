package helllo.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY strategy: DB에서 알아서 id를 생성해주는 전략
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
