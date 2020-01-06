import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.model.User;
import ru.otus.traverse.builder.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlStatementsGenerationTest {

    ClassContext getContext() throws NoSuchFieldException {
        ClassContext context = new ClassContextImpl();
        context.addTableName("user")
                .addIdField(User.class.getDeclaredField("id"))
                .addField(User.class.getDeclaredField("name"))
                .addField(User.class.getDeclaredField("age"));
        return context;
    }
    @Test
    @DisplayName("Test correct building select statement")
    void selectStatementBuildingTest() throws NoSuchFieldException {
        ClassContext context = getContext();
        String selectStatement = context.setStrategy(new SelectBuilder()).build();
        assertThat(selectStatement).isEqualTo("select id, name, age from user where id = ?");
    }

    @Test
    @DisplayName("Test correct building insert statement")
    void selectInsertBuildingTest() throws NoSuchFieldException {
        ClassContext context = getContext();
        String insertStatement = context.setStrategy(new InsertBuilder()).build();
        assertThat(insertStatement).isEqualTo("insert into user(name,age) values (?,?)");
    }

    @Test
    @DisplayName("Test correct building update statement")
    void updateStatementBuildingTest() throws NoSuchFieldException {
        ClassContext context = getContext();
        String selectStatement = context.setStrategy(new UpdateBuilder()).build();
        assertThat(selectStatement).isEqualTo("update user set name = ?, age = ? where id = ?");
    }

}
