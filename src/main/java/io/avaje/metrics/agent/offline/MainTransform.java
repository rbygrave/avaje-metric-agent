package io.avaje.metrics.agent.offline;

import io.avaje.metrics.agent.AgentManifest;
import io.avaje.metrics.agent.Transformer;

import java.io.IOException;

/**
 * A utility object to run transformation from a main method.
 */
public class MainTransform {

  public static void main(String[] args) throws IOException {

    if (isHelp(args)) {
      printHelp();
      return;
    }

    String inDir = "./target/test-classes";
    String pkg = "org/test/app";

    if (args.length > 0) {
      inDir = args[0];
    }
    if (args.length > 1) {
      pkg = args[1];
    }

    ClassLoader cl = ClassLoader.getSystemClassLoader();
    AgentManifest agentManifest = AgentManifest.read(cl);

    Transformer t = new Transformer(agentManifest);

    OfflineFileTransform ft = new OfflineFileTransform(t, cl, inDir, inDir);

    ft.process(pkg);

  }

  private static void printHelp() {
    System.out.println("Usage: [inputDirectory] [packages] [transformArguments]");
  }

  private static boolean isHelp(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("help")) {
        return true;
      }
      if (args[i].equalsIgnoreCase("-h")) {
        return true;
      }
    }
    return false;
  }
}
