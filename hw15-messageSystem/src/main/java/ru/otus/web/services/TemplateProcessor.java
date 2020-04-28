package ru.otus.web.services;

import java.util.Map;

public interface TemplateProcessor {
    String getPage(String filename, Map<String, Object> data);
}
