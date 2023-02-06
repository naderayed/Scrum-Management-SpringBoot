package com.nader.scrum.management.services;

import com.nader.scrum.management.entities.Sprint;

public interface ISprintServcie {
     void addSprintAndAssignToProject(Sprint sprint, int idProject);
}
