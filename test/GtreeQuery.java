import com.github.jamjpan.libgtree_jni.GTree;
import com.github.jamjpan.libgtree_jni.gtreeJNI;
import com.github.jamjpan.libgtree_jni.IntVector;

public class GtreeQuery {
  static final Integer REQUIRED_ARGS = 3;
  static String  arg1 = "";  // *.gtree file
  static Integer arg2 = -1;  // from vertex
  static Integer arg3 = -1;  // to vertex
  static {
    try {
      System.loadLibrary("gtree_jni");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load: "+e);
      System.exit(1);
    }
  }
  public static void print_help() {
    System.out.println("usage: GtreeQuery GTREE FROM TO");
  }
  public static void run() {
      double startTime = System.nanoTime();
      gtreeJNI.setIndex_path(arg1);
      GTree gtree = new GTree();
      gtreeJNI.read_GTree(gtree);
      double elapsed = System.nanoTime() - startTime;
      System.out.printf("read index: %.0f ns\n", elapsed);

      startTime = System.nanoTime();
      Integer dist = gtree.shortest_path_querying(arg2, arg3);
      elapsed = System.nanoTime() - startTime;
      System.out.printf("shortest distance (%.0f ns): %d\n", elapsed, dist);

      startTime = System.nanoTime();
      IntVector path = new IntVector();
      gtree.path_recovery(arg2, arg3, path);
      elapsed = System.nanoTime() - startTime;
      System.out.printf("path recovery (%.0f ns): ", elapsed);
      for (int i = 0; i < path.size(); i++) {
	  System.out.printf("%d ", path.get(i));
      }
      System.out.println();
  }
  public static void main(String argv[]) {
    if (argv.length < REQUIRED_ARGS) {
      print_help();
    } else {
      // Required arguments
      int j = (argv.length - REQUIRED_ARGS);
      arg1 = argv[(j + 0)];
      arg2 = Integer.parseInt(argv[(j + 1)]);
      arg3 = Integer.parseInt(argv[(j + 2)]);

      // Optional arguments
      // ...

      // Launch app
      System.out.printf("%s '%s'\n%s '%s'\n%s '%s'\n",
        "set gtree file", arg1,
        "set from ", arg2,
        "set to ", arg3);
      run();
    }
    return;
  }
}

