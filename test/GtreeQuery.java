import com.github.jamjpan.gtree.GTree;
import com.github.jamjpan.gtree.gtreeJNI;
import com.github.jamjpan.gtree.IntVector;

public class GtreeQuery {
  static final Integer REQUIRED_ARGS = 3;
  static String  arg1 = "";  // *.gtree file
  static Integer arg2 = -1;  // from vertex
  static Integer arg3 = -1;  // to vertex
  static {
    try {
      System.loadLibrary("gtree");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load: "+e);
      System.exit(1);
    }
  }
  public static void print_help() {
    System.out.println("usage: GtreeQuery GTREE FROM TO");
  }
  public static void run() {
    gtreeJNI.setIndex_path(arg1);
    GTree gtree = new GTree();
    gtreeJNI.read_GTree(gtree);
    Integer dist = gtree.shortest_path_querying(arg2, arg3);
    IntVector path = new IntVector();
    gtree.path_recovery(arg2, arg3, path);
    System.out.printf("%d to %d shortest distance %d\t", arg2, arg3, dist);
    for (Integer i : path) {
      System.out.printf("%d ", i);
    }
    System.out.println();
  }
  public static void main(String argv[]) {
    System.out.println(argv.length);
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

