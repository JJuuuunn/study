package study.querydsl.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {
        //given
        Team teamA = new Team("teamA");
        em.persist(teamA);
        Team teamB = new Team("teamB");
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        em.persist(member1);
        em.persist(member2);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();

        //when
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        //then
        for (Member m : members) {
            System.out.println("member = " + m);
            System.out.println(" -> member.team = " + m.getTeam());
        }
    }
}