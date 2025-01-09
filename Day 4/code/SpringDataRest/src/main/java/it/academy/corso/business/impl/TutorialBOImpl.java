package it.academy.corso.business.impl;

import it.academy.corso.business.interfaces.TutorialBO;
import it.academy.corso.exception.ResourceNotFoundException;
import it.academy.corso.model.Tutorial;
import it.academy.corso.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialBOImpl implements TutorialBO {
    private final TutorialRepository tutorialRepository;

    public TutorialBOImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public List<Tutorial> allTutorials(String title){
        List<Tutorial> tutorials;
        if (title == null)
            tutorials = this.tutorialRepository.findAll();
        else
            tutorials = this.tutorialRepository.findByTitleContaining(title);
        return tutorials;
    }

    @Override
    public Tutorial tutorialById(Long id){
        return this.tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    }

    @Override
    public Tutorial create(Tutorial tutorial){
        return this.tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
    }

    @Override
    public Tutorial update(Long id, Tutorial tutorial){
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());
       return this.tutorialRepository.save(_tutorial);
    }

    @Override
    public void delete(Long id){
        this.tutorialRepository.deleteById(id);
    }
    @Override
    public List<Tutorial> published(){
        return this.tutorialRepository.findByPublished(true);
    }
}
