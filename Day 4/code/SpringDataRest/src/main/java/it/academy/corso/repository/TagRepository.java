package it.academy.corso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.academy.corso.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
  List<Tag> findTagsByTutorialsId(Long tutorialId);
}
