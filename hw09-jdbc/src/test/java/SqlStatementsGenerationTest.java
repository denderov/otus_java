import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.api.model.User;
import ru.otus.traverse.builder.*;
import ru.otus.traverse.type.TraversedClass;
import ru.otus.traverse.visitor.ContextClassVisitor;

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
    ClassContext getClassContext() throws NoSuchFieldException, IllegalAccessException {
        TraversedClass userClass = new TraversedClass(User.class);
        ContextClassVisitor visitor = new ContextClassVisitor();
        userClass.accept(visitor);
        return visitor.getClassContext();
    }
    @Test
    @DisplayName("Test correct building select statement")
    void selectStatementBuildingTest() throws NoSuchFieldException, IllegalAccessException {
        ClassContext context = getClassContext();
        String selectStatement = context.setStrategy(new SelectBuilder()).build();
        assertThat(selectStatement).isEqualTo("select id, age, name from user where id = ?");
    }

    @Test
    @DisplayName("Test correct building insert statement")
    void selectInsertBuildingTest() throws NoSuchFieldException, IllegalAccessException {
        ClassContext context = getClassContext();
        String insertStatement = context.setStrategy(new InsertBuilder()).build();
        assertThat(insertStatement).isEqualTo("insert into user(age,name) values (?,?)");
    }

    @Test
    @DisplayName("Test correct building update statement")
    void updateStatementBuildingTest() throws NoSuchFieldException, IllegalAccessException {
        ClassContext context = getClassContext();
        String selectStatement = context.setStrategy(new UpdateBuilder()).build();
        assertThat(selectStatement).isEqualTo("update user set age = ?, name = ? where id = ?");
    }

}
