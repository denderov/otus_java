package ru.otus.hw08.visitor;

import ru.otus.hw08.helper.Condition;
import ru.otus.hw08.traversed.type.TraversedArray;
import ru.otus.hw08.traversed.type.TraversedElement;
import ru.otus.hw08.traversed.type.TraversedObject;

import javax.json.Json;
import javax.json.JsonValue;
import java.util.Collection;

public class JsonElementVisitor implements ElementVisitor {
    private JsonValue jsonValue;
    @Override
    public void visit(TraversedElement traversedElement) throws IllegalAccessException {
        Object element = traversedElement.getElement();
        if (Condition.isNull(element)) {
            jsonValue = JsonValue.NULL;
        } else if (Condition.isInteger(element)) {
            Number number = (Number) element;
            jsonValue = Json.createValue(number.longValue());
        } else if (Condition.isFloat(element)) {
            Number number = (Number) element;
            jsonValue = Json.createValue(number.doubleValue());
        } else if (Condition.isString(element)) {
            jsonValue = Json.createValue(element.toString());
        } else if (Condition.isBoolean(element)) {
            Boolean bool = (Boolean) element;
            jsonValue = bool?JsonValue.TRUE:JsonValue.FALSE;
        } else if (Condition.isArray(element)) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            new TraversedArray(element).accept(jsonArrayVisitor);
            jsonValue = jsonArrayVisitor.getJsonArrayBuilder().build();
        } else if (Condition.isCollection(element)) {
            JsonArrayVisitor jsonArrayVisitor = new JsonArrayVisitor();
            Collection<Object> currentCollection = (Collection<Object>) element;
            Object[] array = currentCollection.toArray();
            new TraversedArray(array).accept(jsonArrayVisitor);
            jsonValue = jsonArrayVisitor.getJsonArrayBuilder().build();
        } else {
            JsonObjectVisitor fieldObjectVisitor = new JsonObjectVisitor();
            new TraversedObject(element).accept(fieldObjectVisitor);
            jsonValue = fieldObjectVisitor.getJsonObjectBuilder().build();
        }
    }

    JsonValue getJsonValue() {
        return jsonValue;
    }

    public String getJson() {
        return jsonValue.toString();
    }
}
