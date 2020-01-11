import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.cachehw.MyCache;

import static org.assertj.core.api.Assertions.assertThat;

public class MyCacheTest {

    @Test
    @DisplayName("Is working limit for cache")
    void isWorkingLimitTest() {
        int limit = 10;
        MyCache<Integer, String> myCache = new MyCache<>(limit);
        for (int i = 0; i < limit * 2; i++) {
            myCache.put(i, "value = " + i);
        }
        assertThat(myCache.getActualSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("Is working correct for one element")
    void isWorkingForOneElement() {
        MyCache<Integer, String> myCache = new MyCache<>();
        myCache.put(1, "One");
        assertThat(myCache.get(1)).isEqualTo("One");
    }
}
