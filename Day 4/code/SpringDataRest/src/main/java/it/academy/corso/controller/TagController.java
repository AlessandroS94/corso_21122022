package it.academy.corso.controller;

import it.academy.corso.business.interfaces.TagBO;
import it.academy.corso.model.Tag;
import it.academy.corso.model.Tutorial;
import it.academy.corso.repository.TagRepository;
import it.academy.corso.repository.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TagController {

  private final TagBO tagBO;

  private final TagRepository tagRepository;

  public TagController(TagBO tagBO, TagRepository tagRepository) {
    this.tagBO = tagBO;
    this.tagRepository = tagRepository;
  }

  @GetMapping("/tags")
  public ResponseEntity<?> allTags() {
    List<Tag> tags = tagBO.getAllTags();
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }
  
  @GetMapping("/tutorials/{tutorialId}/tags")
  public ResponseEntity<List<Tag>> allTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
   List<Tag> tags = tagBO.getAllTagsByTutorialId(tutorialId);
    return new ResponseEntity<>(tags, HttpStatus.OK);
  }

  @GetMapping("/tags/{id}")
  public ResponseEntity<Tag> tagsById(@PathVariable(value = "id") Long id) {
    Tag tag = tagBO.getTagsById(id);
    return new ResponseEntity<>(tag, HttpStatus.OK);
  }
  
  @GetMapping("/tags/{tagId}/tutorials")
  public ResponseEntity<List<Tutorial>> allTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
      return new ResponseEntity<>(tagBO.getAllTutorialByTagId(tagId),HttpStatus.OK);
  }

  @PostMapping("/tutorials/{tutorialId}/tags")
  public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
    return new ResponseEntity<>(tagRepository.save(tagBO.addTag(tutorialId,tagRequest)), HttpStatus.OK);
  }
 
  @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
  public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @PathVariable(value = "tagId") Long tagId) {
    tagBO.deleteTagFromTutorial(tutorialId,tagId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/tags/{id}")
  public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") Long id) {
    tagBO.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
