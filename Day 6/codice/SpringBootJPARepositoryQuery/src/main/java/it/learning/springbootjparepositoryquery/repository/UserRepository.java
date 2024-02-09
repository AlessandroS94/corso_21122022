package it.learning.springbootjparepositoryquery.repository;

import it.learning.springbootjparepositoryquery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.name not like concat('%', ?1, '%') order by u.name")
    List<Optional<User>>  findByNameNotContainsOrderByNameAsc(String name);

    @Query("select u from User u where u.name like concat(?1, '%')")
    List<Optional<User>> findByNameStartsWith(@NonNull String name);

    List<User> findByBirthdayGreaterThanEqual(Date birthday);

}