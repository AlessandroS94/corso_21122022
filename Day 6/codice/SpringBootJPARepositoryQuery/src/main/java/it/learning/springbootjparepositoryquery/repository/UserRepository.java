package it.learning.springbootjparepositoryquery.repository;

import it.learning.springbootjparepositoryquery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name not like concat('%', ?1, '%') order by u.name")
    Optional<User> findByNameNotContainsOrderByNameAsc(String name);

    @Query("select u from User u where u.name like concat(?1, '%')")
    Optional<User> findByNameStartsWith(@NonNull String name);

   // Optional<User> findUserByIdAndNameContaining(@NonNull String name);



}