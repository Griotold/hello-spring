package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        // id값을 만들어준다
        member.setId(++sequence);

        // store에 아이디값과 멤버객체를 저장한다.
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 가져오면 되는데 null일 수 있다.
        // null이면 Optional로 감싸준다. orElseThrow도 있다.
        return Optional.ofNullable(store.get(id));

    }

    @Override
    public Optional<Member> findByName(String name) {
        // 찾아보는데 null일 수 있으니 Optional로 감싸줘야하지만 알아서 감싸짐.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        // store의 키는 id고, 값은 멤버들이었잖아.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
