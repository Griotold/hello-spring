package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // 테스트 파일 생성 단축키 : 컨트롤 + 쉬프트 + T

    // 서비스를 검증하므로 가져와야겠지
    MemberService memberService;

    MemberRepository memberRepository;

    @BeforeEach // 각각의 테스트 실행전 DI
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 각각의 테스트 후 실행되는 콜백 메소드
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test // 영어권 사람과 협업하지 않으면 테스트는 한글로도 많이 쓴다.
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long memberId = memberService.join(member);
        Member actual = memberService.findOne(memberId).get(); // Optional에서 객체를 꺼낼때 get()

        // then
        Assertions.assertThat(actual.getName()).isEqualTo(member.getName());
    }
    @Test
    void 중복_회원_검증() {
        // given : 똑같은 회원 이름을 가진 회원 2명 준비
        Member member1 = new Member();
        member1.setName("spring");
        // 리네임 단축키 : 쉬프트 + f6
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1); // 이건 잘 작동하겠다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        memberService.join(member2); // 여기서 예외가 발생할 것이다.
        // try-catch 감싸기 단축키 : 컨트롤 + 알트 + T
//        try {
//            memberService.join(member2);
//            fail(); // 예외가 발생해야 합니다.
//        } catch (IllegalStateException e) {
//            // then
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}