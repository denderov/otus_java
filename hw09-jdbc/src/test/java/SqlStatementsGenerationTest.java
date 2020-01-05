import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.traverse.builder.InsertBuilder;
import ru.otus.traverse.builder.SelectBuilder;
import ru.otus.traverse.builder.SqlStatementBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlStatementsGenerationTest {

    @Test
    @DisplayName("Test correct building select statement")
    void selectStatementBuildingTest() {
        SqlStatementBuilder statementBuilder = new SelectBuilder();
        String selectStatement = statementBuilder.addTableName("user")
                .addIdColumnName("id")
                .addColumnName("name")
                .addColumnName("age")
                .build();
        assertThat(selectStatement).isEqualTo("select id, name, age from user where id = ?");
    }

    @Test
    @DisplayName("Test correct building insert statement")
    void selectInsertBuildingTest() {
        SqlStatementBuilder statementBuilder = new InsertBuilder();
        String insertStatement = statementBuilder.addTableName("user")
                .addIdColumnName("id")
                .addColumnName("name")
                .addColumnName("age")
                .build();
        assertThat(insertStatement).isEqualTo("insert into user(name,age) values (?,?)");
    }
}
