package com.codeEditor.v1.utils;

import com.codeEditor.v1.entity.Languages;

public class BoilerPlate {

    public static String getBoilerplate(Languages language) {
        switch (language) {
            case JAVA:
                return "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}";
            case PYTHON:
                return "if __name__ == '__main__':\n    print('Hello, World!')";
            case CPP:
                return "#include <iostream>\nusing namespace std;\nint main() {\n    cout << \"Hello, World!\" << endl;\n    return 0;\n}";
            default:
                return "";
        }
    }
}
