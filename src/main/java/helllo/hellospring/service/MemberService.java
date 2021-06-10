package helllo.hellospring.service;

import helllo.hellospring.domain.Member;
import helllo.hellospring.repository.MemberRepository;
import helllo.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // jpa를 쓰려면 이 어노테이션을 필수로 붙여줘야함
public class MemberService {
    private final MemberRepository memberRepository;

    // Autowired : spring 컨테이너에 있는 memberRepository를 자동으로 연결시켜줌 (DI)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // something.ifPresent(item -> {}) : 값이 있으면 callback실행
        memberRepository.findByName((member.getName())).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
