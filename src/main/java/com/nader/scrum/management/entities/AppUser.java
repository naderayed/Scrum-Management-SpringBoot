package com.nader.scrum.management.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class AppUser {
    /*note that mappedBy is always used in bidirectional relation and in the case of OneToMany or ManyToOne we mapped
    by the child, end for the ManyToMany Or OneToOne we check the logic or the description
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String emailUser;
    private String password;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Project> developersProjects;
    @OneToMany
    List<Project> scrumProjects;

}
