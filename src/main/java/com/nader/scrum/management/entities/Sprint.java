package com.nader.scrum.management.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Sprint {
    /*note that mappedBy is always used in bidirectional relation and in the case of OneToMany or ManyToOne we mapped
    by the child, end for the ManyToMany Or OneToOne we check the logic or the description
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSprint;
    private String descriptionSprint;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @ManyToOne
    private Project project;
}
