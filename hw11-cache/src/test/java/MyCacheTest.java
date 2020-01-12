import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.cachehw.MyCache;

import static org.assertj.core.api.Assertions.assertThat;

public class MyCacheTest {

    int limit;
    MyCache<Integer, String> myCache;

    @BeforeEach
    void initCache() {
        limit = 100;
        myCache = new MyCache<>(limit);
    }

    @Test
    @DisplayName("Is working limit for cache")
    void isWorkingLimitTest() {
        for (int i = 0; i < limit * 2; i++) {
            myCache.put(i, "value = " + i);
        }
        assertThat(myCache.getActualSize()).isEqualTo(limit);
    }

    @Test
    @DisplayName("Is working correct for one element")
    void isWorkingForOneElement() {
        myCache.put(1, "One");
        assertThat(myCache.get(1)).isEqualTo("One");
    }

}