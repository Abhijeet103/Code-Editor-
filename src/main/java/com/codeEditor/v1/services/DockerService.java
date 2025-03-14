package com.codeEditor.v1.services;

import com.codeEditor.v1.entity.Languages;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DockerService {

    private static final String TEMPLATE_DIR = "docker";

    private String readDockerTemplate(Languages language) throws IOException {
        Path templatePath = Path.of(TEMPLATE_DIR, language.name().toLowerCase() + ".Dockerfile");
        return Files.readString(templatePath, StandardCharsets.UTF_8);
    }

    public String runInDocker(Languages language, String code) throws IOException, InterruptedException {
        String tempDir = System.getProperty("java.io.tmpdir");
        String fileName = "Main." + language.getExtension();
        Path filePath = Path.of(tempDir, fileName);
        Path dockerFilePath = Path.of(tempDir, "Dockerfile");

        Files.writeString(filePath, code);
        Files.writeString(dockerFilePath, readDockerTemplate(language));

        String imageName = "runner-" + language.name().toLowerCase();

        // Build Docker Image
        ProcessBuilder buildProcess = new ProcessBuilder(
                "docker", "build", "-f", dockerFilePath.toString(), "-t", imageName, tempDir
        );
        Process build = buildProcess.start();
        build.waitFor();

        // Debug build errors
        BufferedReader buildErrorReader = new BufferedReader(new InputStreamReader(build.getErrorStream()));
        StringBuilder buildErrorOutput = new StringBuilder();
        String line;
        while ((line = buildErrorReader.readLine()) != null) {
            buildErrorOutput.append(line).append("\n");
        }
        System.out.println("Docker Build Errors: \n" + buildErrorOutput.toString());

        // Run Docker Container
        ProcessBuilder runProcess = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tempDir + ":/usr/src/app",
                imageName
        );

        Process process = runProcess.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Capture Errors
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder errorOutput = new StringBuilder();
        while ((line = errorReader.readLine()) != null) {
            errorOutput.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            return "Execution Error:\n" + errorOutput.toString();
        }
        return output.toString();
    }
}
