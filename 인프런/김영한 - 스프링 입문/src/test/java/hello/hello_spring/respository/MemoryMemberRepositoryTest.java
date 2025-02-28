package hello.hello_spring.respository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {


    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //then
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        for (int i = 0; i < result.size(); i++) {
            System.out.println("member" + (i + 1) + " = id : " + result.get(i).getId() + ", name : " + result.get(i).getName());
        }
    }
}