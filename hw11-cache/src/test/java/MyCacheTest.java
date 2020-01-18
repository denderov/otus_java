import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.cachehw.MyCache;

import static org.assertj.core.api.Assertions.assertThat;

public class MyCacheTest {

    private MyCache<String, String> myCache;

    @BeforeEach
    void initCache() {
        myCache = new MyCache<>();
    }

    @Test
    @DisplayName("Is working correct for one element")
    void isWorkingForOneElement() {
        myCache.put("1", "One");
        assertThat(myCache.get("1")).isEqualTo("One");
    }

}
