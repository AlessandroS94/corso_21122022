package it.academy.corso.business.interfaces;

import it.academy.corso.model.Tag;
import it.academy.corso.model.Tutorial;

import java.util.List;

public interface TagBO {
    List<Tag> getAllTags();

    List<Tag> getAllTagsByTutorialId(Long tutorialId);

    Tag getTagsById(Long id);


    List<Tutorial> getAllTutorialByTagId(Long tagId);

    Tag addTag(Long tutorialId, Tag tagRequest);

    void delete(Long id);

    void deleteTagFromTutorial(Long tutorialId, Long tagId);
}
