package it.academy.corso.business.interfaces;

import it.academy.corso.model.Tutorial;

import java.util.List;

public interface TutorialBO {
    List<Tutorial> allTutorials(String title);

    Tutorial tutorialById(Long id);

    Tutorial create(Tutorial tutorial);

    Tutorial update(Long id, Tutorial tutorial);

    void delete(Long id);

    List<Tutorial> published();
}
