package helllo.hellospring.repository;

import helllo.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public static long getSequence() {
        return sequence;
    }

    public static void setSequence(long sequence) {
        MemoryMemberRepository.sequence = sequence;
    }


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null일 가능성이 있을 경우 Optional.ofNullable()로 감싸주는 게 좋다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals((name))).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        this.setSequence(0L);
        store.clear();
    }
}
