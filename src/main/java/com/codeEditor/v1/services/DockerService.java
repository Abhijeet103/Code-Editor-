package com.codeEditor.v1.services;


import com.codeEditor.v1.entity.Languages;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DockerService {

    public String  runInDocker(Languages language , String code) throws IOException, InterruptedException {
        String  tempDir  =  System.getProperty("java.io.tmpdir");
        String fileName  =  "Main." + language.getExtension();
        Path filePath  =  Path.of(tempDir , fileName) ;
        Files.writeString(filePath, code);

        // rm is going to remove the docker container once done
        // i is for interactive
        ProcessBuilder builder = new ProcessBuilder(
                "docker", "run", "--rm", "-i", "-t",
                "-v", tempDir + ":/usr/src/app",
                language.getDockerImage()
        );

        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        System.out.println(line);

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder errorOutput = new StringBuilder();
        while ((line = errorReader.readLine()) != null) {
            errorOutput.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            return "Error:\n" + errorOutput.toString();
        }
        return output.toString();

    }
}