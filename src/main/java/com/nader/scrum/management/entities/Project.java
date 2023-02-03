package com.nader.scrum.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Project {
    /*note that mappedBy is always used in bidirectional relation,
    in the case of OneToMany or ManyToOne we mapped
    by the child, and for the ManyToMany Or OneToOne we check the logic or the description
    * */

    /*all cascade and jsonIgnore.. from project description */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProject;
    private String titleProject;
    private String projectDescription;

    @ManyToMany(mappedBy = "developersProjects")
    @JsonIgnore
    @ToString.Exclude
    private List<AppUser> appUsers;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Sprint> sprints;
}
