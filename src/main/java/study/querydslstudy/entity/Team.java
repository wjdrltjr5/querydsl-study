package study.querydslstudy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;


    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();


    public Collection<Member> getMembers() {
        return this.members;
    }

    public Team(String name) {
        this.name = name;
    }
}
