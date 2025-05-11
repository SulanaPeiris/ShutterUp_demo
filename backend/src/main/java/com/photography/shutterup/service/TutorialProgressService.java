package com.photography.shutterup.service;

import com.photography.shutterup.model.Tutorial;
import com.photography.shutterup.model.User;
import com.photography.shutterup.model.TutorialProgress;

public interface TutorialProgressService {
    TutorialProgress enroll(User user, Tutorial tutorial);
    TutorialProgress markStepComplete(User user, Tutorial tutorial, Long stepId);
    TutorialProgress getProgress(User user, Tutorial tutorial);
}
