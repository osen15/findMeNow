package com.findMeNow.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class JsonToModel<T> {
    public T jsonToEntity(HttpServletRequest req, Class<T> tClass) {
        StringBuilder stb = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null)
                stb.append(line);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(stb.toString(), tClass);
    }
}

