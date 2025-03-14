package com.codeEditor.v1.entity;

public enum Languages {

    JAVA("my-java-executor", "java"),
    PYTHON("python-runner", "py"),
    CPP("my-cpp-executor", "cpp");

    private final String dockerImage;
    private final String extension;

    public String getDockerImage() {
        return dockerImage;
    }

    public String getExtension() {
        return extension;
    }

    Languages(String dockerImage, String extension) {
        this.dockerImage = dockerImage;
        this.extension = extension;
    }
}
