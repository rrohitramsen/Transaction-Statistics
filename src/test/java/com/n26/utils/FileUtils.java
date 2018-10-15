package com.n26.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * @implNote This class provide utils method for reading data from json file and converting json data into provided object type.
 * @author rohitkumar
 */
public class FileUtils {

    private static final String BASE_PATH = "src/test/resources/";

    /**
     * Convert input file into json of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return JSON representation of the object data from file.
     * @throws IOException
     */
    public static <T> String readFileIntoJson(Class<T> type, String fileName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(objectMapper.readValue(new File(BASE_PATH + fileName),  type));

    }


    /**
     * Convert Json to Object of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return object of type T
     * @throws IOException
     */
    public static <T> T readObjectFromJsonFile(Class<T> type, String fileName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(BASE_PATH + fileName), type);

    }
}
