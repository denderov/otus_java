package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.TraversedField;
import ru.otus.hw08.traversed.type.TraversedObject;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class JsonObjectVisitor implements ObjectVisitor {

    private JsonObjectBuilder jsonObjectBuilder;

    @Override
    public void visit(TraversedObject traversedObject) throws IllegalAccessException {
        Object parentObject = traversedObject.getObject();
        if (!Objects.isNull(parentObject)) {
            jsonObjectBuilder = Json.createObjectBuilder();
            Field[] fields = parentObject.getClass().getDeclaredFields();

            for (Field field:
                    fields) {
                if (isNotStatic(field)) {
                    new TraversedField(parentObject,field).accept(new JsonFieldVisitor(this));
                }
            }
        }
    }

    private boolean isNotStatic(Field field) {
        return !Modifier.isStatic(field.getModifiers());
    }

    JsonObjectBuilder getJsonObjectBuilder() {
        return jsonObjectBuilder;
    }

    public String getJsonObject() {
        return Objects.isNull(jsonObjectBuilder)?"null":getJsonObjectBuilder().build().toString();
    }
}
