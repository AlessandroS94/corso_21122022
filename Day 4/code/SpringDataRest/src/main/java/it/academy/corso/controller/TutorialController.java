package it.academy.corso.controller;

import it.academy.corso.business.interfaces.TutorialBO;
import it.academy.corso.model.Tutorial;
import it.academy.corso.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TutorialController {

  @Autowired
  private TutorialRepository tutorialRepository;

  @Autowired
  private TutorialBO tutorialBO;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    List<Tutorial> tutorials = new ArrayList<Tutorial>();
    tutorials = tutorialBO.allTutorials(title);
    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    return new ResponseEntity<>(tutorialBO.tutorialById(id), HttpStatus.OK);
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    return new ResponseEntity<>(tutorialBO.create(tutorial), HttpStatus.CREATED);
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    return new ResponseEntity<>(tutorialBO.update(id,tutorial), HttpStatus.OK);
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    tutorialBO.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    return new ResponseEntity<>(tutorialBO.published(), HttpStatus.OK);
  }
}
