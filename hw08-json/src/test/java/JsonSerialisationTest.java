import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw08.traversed.object.ArraysContainer;
import ru.otus.hw08.traversed.object.Nested;
import ru.otus.hw08.traversed.object.PrimitiveTypesAndStrings;
import ru.otus.hw08.traversed.object.Root;
import ru.otus.hw08.traversed.type.TraversedObject;
import ru.otus.hw08.visitor.JsonObjectVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonSerialisationTest {
    @Test
    @DisplayName("Test correct serialisation object with primitives")
    void PrimitiveTypesAndStringsObjectTest() throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();

        PrimitiveTypesAndStrings primitiveTypesAndStrings = new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', true, "currency");

        new TraversedObject(primitiveTypesAndStrings).accept(jsonObjectVisitor);

        String json = jsonObjectVisitor.getJsonObject().toString();

        assertThat(primitiveTypesAndStrings).isEqualTo(new Gson().fromJson(json, PrimitiveTypesAndStrings.class));
    }

    @Test
    @DisplayName("Test correct serialisation object with nested objects")
    void ObjectWithNestedObjectsTest() throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        new TraversedObject(root).accept(jsonObjectVisitor);

        String json = jsonObjectVisitor.getJsonObject().toString();

        assertThat(root).isEqualTo(new Gson().fromJson(json, Root.class));
    }

    @Test
    @DisplayName("Test correct serialisation object with arrays and collections")
    void ObjectWithArraysAndCollectionsTest() throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();

        int[] ints = {1, 2, 3, 4};
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        List<String> strings = new ArrayList<>();
        strings.add("One");
        strings.add("Two");
        strings.add(null);
        ArraysContainer arraysContainer = new ArraysContainer(1, ints, chars, strings);

        new TraversedObject(arraysContainer).accept(jsonObjectVisitor);

        String json = jsonObjectVisitor.getJsonObject().toString();

        ArraysContainer expected = new Gson().fromJson(json, ArraysContainer.class);
        assertThat(arraysContainer).isEqualTo(expected);
    }
}
