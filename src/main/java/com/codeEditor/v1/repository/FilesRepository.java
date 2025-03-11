package com.codeEditor.v1.repository;

import com.codeEditor.v1.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files , Long> {

    List<Files> findByUserId(Long userId);
}
