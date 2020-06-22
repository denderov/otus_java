package ru.otus.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.otus.socket.SocketServer;

@Component
public class AppRunner {

  private final SocketServer socketServer;

  public AppRunner(SocketServer socketServer) {
    this.socketServer = socketServer;
  }

  public void runAll()  {
    try {
      File foo = new File("foo");
      System.out.println(foo.getAbsolutePath());
      runWrapper(
          new File("./hw16-frontend/target/hw16-frontend.jar"),
          "8086", "8085", "frontendService1", "databaseService1");
      runWrapper(
          new File("./hw16-dbServer/target/hw16-dbServer.jar"),
          "8087", "8085", "frontendService1", "databaseService1");
      socketServer.go();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void runWrapper(File jarFile, String... jarArgs) throws IOException {
    List<String> processCommands = new ArrayList<>();
    processCommands.add("java");
    processCommands.add("-jar");
    processCommands.add(jarFile.getCanonicalPath());
    processCommands.addAll(Arrays.asList(jarArgs));
    new ProcessBuilder(processCommands)
        .inheritIO()
        .directory(jarFile.getParentFile())
        .start();
  }
}
