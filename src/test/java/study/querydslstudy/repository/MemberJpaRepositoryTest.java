package study.querydslstudy.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydslstudy.dto.MemberSearchCondition;
import study.querydslstudy.dto.MemberTeamDto;
import study.querydslstudy.entity.Member;
import study.querydslstudy.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class MemberJpaRepositoryTest {


    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;


    @Test
    void save(){
        Member member1 = new Member("member1", 10);
        log.info("member1 id = {}", member1.getId());
        memberJpaRepository.save(member1);
        log.info("member1 id = {}", member1.getId());
        Member findMember = memberJpaRepository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(findMember);

        List<Member> members = memberJpaRepository.findByUsername("member1");

        assertThat(members.get(0)).isEqualTo(member1);
    }

    @Test
    void querydsl(){
        Member member1 = new Member("member1", 10);
        log.info("member1 id = {}", member1.getId());
        memberJpaRepository.save(member1);
        log.info("member1 id = {}", member1.getId());
        Member findMember = memberJpaRepository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(findMember);

        List<Member> members = memberJpaRepository.findByUsername_Querydsl("member1");

        assertThat(members.get(0)).isEqualTo(member1);
    }

    @Test
    public void searchTest() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");
        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);
        assertThat(result).extracting("username").containsExactly("member4");
    }
}
