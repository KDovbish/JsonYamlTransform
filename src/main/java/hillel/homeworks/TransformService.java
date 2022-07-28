package hillel.homeworks;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

public class TransformService {

    private boolean parsingError;
    private boolean ioError;
    private boolean emptyInputContent;

    private String inputFileName;
    private String outputFileName;
    private long inputFileSize;
    private long outputFileSize;

    private long duration;

    void JsonToYaml(File jsonFile, File yamlFile) {
        parsingError = false;
        ioError = false;
        emptyInputContent = false;
        inputFileName = jsonFile.getName();
        outputFileName = yamlFile.getName();
        inputFileSize = jsonFile.length();
        outputFileSize = yamlFile.length();
        long startTime = System.currentTimeMillis();
        try {
            JsonNode tree = new ObjectMapper().readTree(jsonFile);
            if (tree != null) {
                new YAMLMapper().writeValue(yamlFile, tree);
            } else {
                emptyInputContent = true;
            }
            outputFileSize = yamlFile.length();
        } catch (JsonProcessingException e) {
            parsingError = true;
        } catch (IOException e) {
            ioError = true;
        }
        duration = System.currentTimeMillis() - startTime;
    }

    void YamlToJson(File yamlFile, File jsonFile) {
        parsingError = false;
        ioError = false;
        emptyInputContent = false;
        inputFileName = yamlFile.getName();
        outputFileName = jsonFile.getName();
        inputFileSize = yamlFile.length();
        outputFileSize = jsonFile.length();
        long startTime = System.currentTimeMillis();
        try {
            JsonNode tree = new YAMLMapper().readTree(yamlFile);
            if (tree != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                mapper.writeValue(jsonFile, tree);
            } else {
                emptyInputContent = true;
            }
            outputFileSize = jsonFile.length();
        } catch (JsonProcessingException e) {
            parsingError = true;
        } catch (IOException e) {
            ioError = true;
        }
        duration = System.currentTimeMillis() - startTime;
    }


    String getLastTransformStateAsText() {
        String s = inputFileName + "(" + inputFileSize + ") -> " + outputFileName + "(" + outputFileSize + ")  duration(ms): " + duration;
        if (parsingError) s = s + "  Parsing ERROR!";
        if (ioError) s = s + " IO ERROR!";
        if (emptyInputContent) s = s + " Empty input content ERROR!";
        return s;
    }

}
