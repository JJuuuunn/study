package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() {
        //given
        Member member = new Member("memberA");
        Member saveMember = memberRepository.save(member);

        //when
        Member findMember = memberRepository.findById(saveMember.getId()).get();

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void testBasicCRUD() {
        //given
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        //when
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        //then
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 리스트 조회 검증
        // when
        List<Member> all = memberRepository.findAll();

        // then
        assertThat(all.size()).isEqualTo(2);

        // 삭제 검증
        // when
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        long count = memberRepository.count();

        // then
        assertThat(count).isEqualTo(0);
    }

    @Test
    public void tsetFindByUsernameAndAgeGreaterThen() {
        //given
        Member member1 = new Member("member", 10);
        Member member2 = new Member("member", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("member", 15);

        //then
        assertThat(result.get(0).getUsername()).isEqualTo("member");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testFindHelloBy() {
        List<Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testNamedQuery() {
        //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findByUsername("member1");

        //then
        assertThat(result.get(0)).isEqualTo(member1);
    }

    @Test
    public void testQueryInRepository() {
        //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findUser("member1", 10);

        //then
        assertThat(result.get(0)).isEqualTo(member1);
    }

    @Test
    public void testQueryToDTO() {
        //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<String> usernameList = memberRepository.findUsernameList();

        //then
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void testMemberDto() {
        //given
        Team team = new Team("team");
        teamRepository.save(team);

        Member member = new Member("member", 10);
        member.setTeam(team);
        memberRepository.save(member);

        //when
        List<MemberDto> memberDto = memberRepository.findMemberDto();

        //then
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void returnType() {
        //given
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> findMemberList = memberRepository.findListByUsername("member1");
        Member findMember = memberRepository.findMemberByUsername("member1");
        Optional<Member> optionalMember = memberRepository.findOptionalByUsername("member1");

        //then
        System.out.println("findMember = " + findMemberList.get(0));
        System.out.println("findMember = " + findMember);
        System.out.println("findMember = " + optionalMember.get());
    }

    @Test
    public void paging() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

        //then
        List<Member> content = page.getContent();

        assertThat(content.size()).isEqualTo(3); // 페이지당 Member 수
        assertThat(page.getTotalElements()).isEqualTo(5); // 총 Member 수
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); // 총 페이지 수
        assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인지 확인
        assertThat(page.hasNext()).isTrue(); // 다음 페이지 유무 확인
    }

    @Test
    public void pagingSlice() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Slice<Member> page = memberRepository.findSliceByAge(age, pageRequest);
        List<Member> content = page.getContent();

        //then
        assertThat(content.size()).isEqualTo(3); // 페이지당 Member 수
        assertThat(page.getNumber()).isEqualTo(0); // 현재 페이지 번호
        assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인지 확인
        assertThat(page.hasNext()).isTrue(); // 다음 페이지 유무 확인
    }

    @Test
    public void bulkUpdate() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 15));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 25));
        memberRepository.save(new Member("member5", 30));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);

        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("==================================");
        System.out.println("member5 = " + member5);
        System.out.println("==================================");

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() {
        //given
        // member1 -> teamA
        // member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = memberRepository.save(new Member("member1", 10, teamA));
        Member member2 = memberRepository.save(new Member("member2", 15, teamB));
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findMemberFetchJoin();

        System.out.println("=======================");
        for (Member m : members) {
            System.out.println("member = " + m.getUsername());
            System.out.println("member.team.class = " + m.getTeam().getClass());
            System.out.println("member.team.name = " + m.getTeam().getName());
        }
        System.out.println("=======================");
    }

    @Test
    public void queryHing() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);

        em.flush();
        em.clear();

        //when
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();
    }

    @Test
    public void lock() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);

        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
    }

    @Test
    public void callCustom() {
        List<Member> result = memberRepository.findMemberCustom();
    }

    @Test
    public void specBasic() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member memberA = new Member("memberA", 0, teamA);
        Member memberB = new Member("memberB", 0, teamA);
        em.persist(memberA);
        em.persist(memberB);

        em.flush();
        em.clear();

        //when
        Specification<Member> spec = MemberSpec.username("m1").and(MemberSpec.teamName("teamA"));
        List<Member> result = memberRepository.findAll(spec);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    /**
     * inner join 은 되나
     * Outer join 은 안되서 비추
     */
    @Test
    public void queryByExample() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member memberA = new Member("memberA", 0, teamA);
        Member memberB = new Member("memberB", 0, teamA);
        em.persist(memberA);
        em.persist(memberB);

        em.flush();
        em.clear();

        //when
        //Probe
        Member member = new Member("memberA");
        Team team = new Team("teamA"); // 내부 조인으로 teamA 가능
        member.setTeam(team);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age");

        Example<Member> example = Example.of(member, matcher);


        List<Member> result = memberRepository.findAll(example);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void projections() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member memberA = new Member("memberA", 0, teamA);
        Member memberB = new Member("memberB", 0, teamA);
        em.persist(memberA);
        em.persist(memberB);

        em.flush();
        em.clear();

        //when
//        List<UsernameOnlyDto> result = memberRepository.findProjectionsByUsername("memberA", UsernameOnlyDto.class);
        List<NestedClosedProjection> result = memberRepository.findProjectionsByUsername("memberA", NestedClosedProjection.class);

        //then
        for (NestedClosedProjection nestedClosedProjection : result) {
            System.out.println("usernameOnly : " + nestedClosedProjection.getUsername());
        }
    }

    /**
     * 네이티브쿼리
     * 가장 마지막 최후의 수단으로 사용할 것을 생각
     */
    @Test
    public void nativeQuery() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member memberA = new Member("memberA", 0, teamA);
        Member memberB = new Member("memberB", 0, teamA);
        em.persist(memberA);
        em.persist(memberB);

        em.flush();
        em.clear();

        //when
//        Member result = memberRepository.findByNativeQuery("memberA");
        Page<MemberProjection> result = memberRepository.findByNativeProjection(PageRequest.of(0, 10));
        List<MemberProjection> content = result.getContent();

        //then
//        System.out.println("result = " + result);
        for (MemberProjection memberProjection : content) {
            System.out.println("memberProjection = " + memberProjection.getUsername());
            System.out.println("memberProjection = " + memberProjection.getTeamName());
        }

    }
}