package ru.otus.hw08.visitor;

import ru.otus.hw08.traversed.type.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonVisitor implements Visitor {

    private Object rootObject;
    private Map<Object,JsonObjectBuilder> builders = new HashMap<>();


    @Override
    public void visit(TraversedInteger traversed) throws IllegalAccessException {
        JsonObjectBuilder builder = builders.get(traversed.getParentObject());
        builder.add(traversed.getFieldName(), traversed.getValue());
    }

    @Override
    public void visit(TraversedFloatNumber traversed) throws IllegalAccessException {
        JsonObjectBuilder builder = builders.get(traversed.getParentObject());
        builder.add(traversed.getFieldName(), traversed.getValue());
    }

    @Override
    public void visit(TraversedString traversed) throws IllegalAccessException {
        JsonObjectBuilder builder = builders.get(traversed.getParentObject());
        builder.add(traversed.getFieldName(), traversed.getValue());
    }

    @Override
    public void visit(TraversedObjectHead traversed) {
        JsonObjectBuilder builder;
        Object object = traversed.getObject();
        boolean isParentObjectNull = Objects.isNull(traversed.getParentObject());
        builder = Json.createObjectBuilder();
        builders.put(object, builder);
        if (isParentObjectNull) {
            rootObject = object;
        }
    }

    @Override
    public void visit(TraversedObjectBottom traversed) {
        Object parentObject = traversed.getParentObject();
        boolean isParentObjectNotNull = !Objects.isNull(parentObject);
        if (isParentObjectNotNull) {
            JsonObjectBuilder builder = builders.get(traversed.getObject());
            builders.get(parentObject).add(traversed.getFieldName(), builder);
        }

    }


    @Override
    public void visit(TraversedNull traversed) {
        JsonObjectBuilder builder = builders.get(traversed.getParentObject());
        builder.addNull(traversed.getFieldName());
    }

    public JsonObject getJson() {
        JsonObjectBuilder builder = builders.get(rootObject);
        return builder.build();
    }

}
