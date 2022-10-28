package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
// 어디서 갖다 쓸 게 아니기 때문에 public이 필요없다.
class MemoryMemberRepositoryTest {

    // 리스코프 치환원칙 : 상위 타입에 하위 타입의 인스턴스로 바꿀 수 있다.
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각각의 테스트 후 실행되는 콜백 메소드
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save() {
        // 준비
        Member member = new Member();
        member.setName("spring");

        // 실제
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 비교
        //assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void find_이름 (){
        // 준비 : 멤버1과 멤버 2를 만든다
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 실제
        // Optional<Member> result = repository.findByName("spring1"); 도 가능
        Member result = repository.findByName("spring1").get();

        // 비교
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // 준비 : 멤버1, 멤버2
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);
        // 실제
        List<Member> all =  repository.findAll();

        // 비교
        assertThat(all.size()).isEqualTo(2);

    }
}