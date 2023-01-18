package com.diemme.repository.mysql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.FileLayout;

@Repository("FileLayoutRepository")
public interface FileLayoutRepository extends JpaRepository<FileLayout, Long> {

	@Query(value = "SELECT f FROM FileLayout f WHERE f.id IN :idFiles")
	Page<FileLayout> getFileLayoutsByLayoutId(Pageable pageable, @Param("idFiles") List<Long> idFiles);

}
