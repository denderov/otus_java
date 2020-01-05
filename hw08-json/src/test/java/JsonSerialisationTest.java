import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private String toJsonString(Object o) throws IllegalAccessException {
        JsonObjectVisitor jsonObjectVisitor = new JsonObjectVisitor();
        new TraversedObject(o).accept(jsonObjectVisitor);
        return jsonObjectVisitor.getJsonObject().toString();
    }

    @Test
    @DisplayName("Test correct serialisation object")
    void customTest() throws IllegalAccessException {
        Gson gson = new GsonBuilder().serializeNulls().create();

        assertThat(gson.toJson(null)).isEqualTo(gson.toJson(null));

        assertThat(toJsonString(null)).isEqualTo(gson.toJson(null));
        assertThat(toJsonString((byte)1)).isEqualTo(gson.toJson((byte)1));
        assertThat(toJsonString((short)2f)).isEqualTo(gson.toJson((short)2f));
        assertThat(toJsonString(3)).isEqualTo(gson.toJson(3));
        assertThat(toJsonString(4L)).isEqualTo(gson.toJson(4L));
        assertThat(toJsonString(5f)).isEqualTo(gson.toJson(5f));
        assertThat(toJsonString(6d)).isEqualTo(gson.toJson(6d));
        assertThat(toJsonString("aaa")).isEqualTo(gson.toJson("aaa"));
        assertThat(toJsonString('a')).isEqualTo(gson.toJson('a'));
        assertThat(toJsonString(new int[] {1, 2, 3})).isEqualTo(gson.toJson(new int[] {1, 2, 3}));
        assertThat(toJsonString(List.of(4, 5, 6))).isEqualTo(gson.toJson(List.of(4, 5, 6)));
        assertThat(toJsonString(Collections.singletonList(1))).isEqualTo(gson.toJson(Collections.singletonList(1)));
    }

    @Test
    @DisplayName("Test correct serialisation object with primitives")
    void PrimitiveTypesAndStringsObjectTest() throws IllegalAccessException {
        Gson gson = new GsonBuilder().serializeNulls().create();

        assertThat(toJsonString(new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', true, "currency")))
                .isEqualTo(gson.toJson(new PrimitiveTypesAndStrings((byte) 1, 2, 3, 4.5f,6.7, '3', true, "currency")));
    }

    @Test
    @DisplayName("Test correct serialisation object with nested objects")
    void ObjectWithNestedObjectsTest() throws IllegalAccessException {
        Gson gson = new GsonBuilder().serializeNulls().create();

        Nested nestedInNested = new Nested(789, null);
        Nested nested = new Nested(456, nestedInNested);
        Root root = new Root(123, "Root", nested);

        assertThat(toJsonString(root))
                .isEqualTo(gson.toJson(root));
    }

    @Test
    @DisplayName("Test correct serialisation object with arrays and collections")
    void ObjectWithArraysAndCollectionsTest() throws IllegalAccessException {
        Gson gson = new GsonBuilder().serializeNulls().create();

        int[] ints = {1, 2, 3, 4};
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
        List<String> strings = new ArrayList<>();
        strings.add("One");
        strings.add("Two");
        strings.add(null);
        ArraysContainer arraysContainer = new ArraysContainer(1, ints, chars, strings);

        assertThat(toJsonString(arraysContainer))
                .isEqualTo(gson.toJson(arraysContainer));
    }
}
