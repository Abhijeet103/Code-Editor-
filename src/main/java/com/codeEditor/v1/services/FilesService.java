package com.codeEditor.v1.services;

import com.codeEditor.v1.entity.Files;
import com.codeEditor.v1.repository.FilesRepository;
import com.codeEditor.v1.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    @Autowired
    private  FilesRepository filesRepository;

    @Autowired
    private UserRepository userRepository ;

    public Files createFile(Files file , Long userId) {
        file.setUser(userRepository.findById(userId).get());
        filesRepository.save(file);
        return  file ;
    }

    public Files getFileById(Long id) {
        return filesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("File not found with ID: " + id));
    }

    public List<Files> getAllFiles() {
        return filesRepository.findAll();
    }

    public Files updateFile(Files updatedFile) {

        return filesRepository.save(updatedFile);
    }

    public void deleteFile(Long id) {
        Files existingFile = getFileById(id);
        filesRepository.delete(existingFile);
    }

    public List<Files> getUserFiles(Long userId) {
        return filesRepository.findByUserId(userId);
    }
}
