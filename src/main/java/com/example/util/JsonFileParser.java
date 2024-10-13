package com.example.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileParser {

    private static final String BASE_PATH = "src/main/java/com/example/";
    private static final Logger logger = Logger.getLogger(JsonFileParser.class.getName());

    public void parseAndCreateFiles(String jsonFilePath) {
        try {
            File file = new File(java.net.URLDecoder.decode(jsonFilePath, StandardCharsets.UTF_8));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(file);
            JsonNode filesNode = rootNode.get("files");
    
            if (filesNode.isArray()) {
                for (JsonNode fileNode : filesNode) {
                    String content = fileNode.get("content").asText();
                    String fileName = extractFileName(content);
                    String folder = determineFolder(fileName, content);
                    String filePath = BASE_PATH + folder + "/" + fileName;
                    createFile(filePath, content);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating file: " + jsonFilePath, e);
        }
    }

    private String extractFileName(String content) {
        Pattern pattern = Pattern.compile("public (?:class|interface) (\\w+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1) + ".java";
        }
        return "UnknownClass.java";
    }

    private String determineFolder(String fileName, String content) {
        if (fileName.endsWith("Entity.java")) {
            return "model";
        } else if (fileName.endsWith("Repository.java")) {
            return "dao";
        } else if (fileName.endsWith("Controller.java")) {
            return "rest";
        } else if (fileName.endsWith("Service.java")) {
            if (content.contains("interface")) {
                return "service/interface";
            } else {
                return "service";
            }
        } else if (fileName.endsWith("ServiceImpl.java")) {
            return "service";
        } else {
            return "other";
        }
    }

    private void createFile(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());
            Files.writeString(path, content);
            System.out.println("File created successfully: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating file: " + filePath, e);
        }
    }

    public static void main(String[] args) {
        try {
            String jsonFilePath;
            ClassLoader classLoader = JsonFileParser.class.getClassLoader();
            java.net.URL resource = classLoader.getResource("file_structure.json");
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                jsonFilePath = resource.getFile();
                JsonFileParser parser = new JsonFileParser();
                parser.parseAndCreateFiles(jsonFilePath);
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "File not found: " + e.getMessage(), e);
        }
    }
}