package helllo.hellospring.service;

import helllo.hellospring.domain.Member;
import helllo.hellospring.repository.MemberRepository;
import helllo.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 실제로 스프링 컨테이너를 띄워서 테스트를 진행
@Transactional // (테스트케이스에 이 어노테이션이 붙으면) 테스트 시작 전에 트랜잭션을 시작하고, 테스트가 완료되면, DB를 롤백한다
public class MemberServiceIntegrationTest { // DB와 연동된 테스팅
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void join_duplicate() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
