package it.academy.corso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.academy.corso.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
  
  List<Tutorial> findTutorialsByTagsId(Long tagId);
}
