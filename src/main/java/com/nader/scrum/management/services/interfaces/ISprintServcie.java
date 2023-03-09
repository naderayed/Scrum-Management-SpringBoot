package com.nader.scrum.management.services.interfaces;

import com.nader.scrum.management.entities.Sprint;

public interface ISprintServcie {
     void addSprintAndAssignToProject(Sprint sprint, int idProject);
}
