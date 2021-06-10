package helllo.hellospring.repository;

import helllo.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속 받고 있으면, 자동으로 spring bean에 등록이 됨
// interface가 interface를 상속받을 땐, extends를 사용
public interface SpringDataJapMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 아래와 같이 인수로 받는 값으로 쿼리문을 만들어줌
    // JPQL: select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
