package it.academy.corso.business.impl;

import it.academy.corso.business.interfaces.TagBO;
import it.academy.corso.exception.ResourceNotFoundException;
import it.academy.corso.model.Tag;
import it.academy.corso.model.Tutorial;
import it.academy.corso.repository.TagRepository;
import it.academy.corso.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagBOImpl implements TagBO {
    private final TagRepository tagRepository;
    private final TutorialRepository tutorialRepository;

    public TagBOImpl(TagRepository tagRepository, TutorialRepository tutorialRepository) {
        this.tagRepository = tagRepository;
        this.tutorialRepository = tutorialRepository;
    }


    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getAllTagsByTutorialId(Long tutorialId){
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }
        return tagRepository.findTagsByTutorialsId(tutorialId);
    }

    @Override
    public Tag getTagsById(Long id){
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));
    }

    @Override
    public List<Tutorial> getAllTutorialByTagId(Long tagId){
        if (!tagRepository.existsById(tagId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
        }
        List<Tutorial> tutorials = tutorialRepository.findTutorialsByTagsId(tagId);
        return tutorials;
    }

    @Override
    public Tag addTag(Long tutorialId, Tag tagRequest){
        Tag tag = tutorialRepository.findById(tutorialId).map(tutorial -> {
            long tagId = tagRequest.getId();

            if (tagId != 0L) {
                Tag _tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                tutorial.addTag(_tag);
                tutorialRepository.save(tutorial);
                return _tag;
            }
            tagRepository.save(tagRequest);
            // add and create new Tag
            tutorial.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
        return tag;
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public void deleteTagFromTutorial(Long tutorialId, Long tagId){
        Tutorial tutorial = tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
        tutorial.removeTag(tagId);
        tutorialRepository.save(tutorial);
    }

}
