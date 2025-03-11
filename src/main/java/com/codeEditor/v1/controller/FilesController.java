package com.codeEditor.v1.controller;

import com.codeEditor.v1.entity.Files;

import com.codeEditor.v1.services.FilesService;
import com.codeEditor.v1.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FilesController {

    private final FilesService filesService;
    private final JwtUtils jwtUtils;

    public FilesController(FilesService filesService, JwtUtils jwtUtils) {
        this.filesService = filesService;
        this.jwtUtils = jwtUtils;
    }

    // Extract userId from JWT token
    private Long getUserIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);  // Remove "Bearer "
        return jwtUtils.extractUserId(token);
    }

    @PostMapping
    public ResponseEntity<Files> createFile(@RequestBody Files file, HttpServletRequest request) {
        Long userId = getUserIdFromToken(request);
        return ResponseEntity.ok(filesService.createFile(file, userId));
    }

    @GetMapping
    public ResponseEntity<List<Files>> getUserFiles(HttpServletRequest request) {
        Long userId = getUserIdFromToken(request);
        return ResponseEntity.ok(filesService.getUserFiles(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Files> updateFile(@RequestBody Files file) {

        return ResponseEntity.ok(filesService.updateFile(file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        filesService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
