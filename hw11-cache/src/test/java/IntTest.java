import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntTest {

    @Test
    void intEquals() {
        Integer int1 = Integer.valueOf(1);
        Integer int2 = new Integer(1);
        Integer int3 = 1 ;
        assertThat(int1).isEqualTo(int2).isEqualTo(int3);
    }
}
