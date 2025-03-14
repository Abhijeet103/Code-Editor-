package com.codeEditor.v1.controller;
import com.codeEditor.v1.dto.CodeDto;
import com.codeEditor.v1.entity.Languages;
import com.codeEditor.v1.services.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/execute")
public class DockerController {

    @Autowired
    private  DockerService dockerService;
    @PostMapping
    public ResponseEntity<String> executeCode(@RequestBody  CodeDto dto) throws IOException, InterruptedException {

        String output = dockerService.runInDocker(dto.getLanguage(), dto.getCode());
        return ResponseEntity.ok(output);
    }
}

