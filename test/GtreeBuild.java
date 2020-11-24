import com.jargors.libgtree_jni.GTree;
import com.jargors.libgtree_jni.gtreeJNI;

public class GtreeBuild {
  static final Integer REQUIRED_ARGS = 1;
  static String arg1 = "";  // edges *.edges or *.gr file
  static String opt_o = "out.gtree";  // output
  static Integer opt_I = 0;  // 0-indexed or 1-indexed
  static {
    try {
      System.loadLibrary("gtree");
    } catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load: "+e);
      System.exit(1);
    }
  }
  public static void print_help() {
    System.out.println("usage: GtreeBuild [-I INDEXING] [-o OUTPUT] EDGES");
  }
  public static void run() {
    gtreeJNI.setGraph_path(arg1);
    gtreeJNI.setIndex_path(opt_o);
    gtreeJNI.setVEX_ID_START_WITH_ZERO((opt_I == 0));
    GTree gtree = new GTree();
    gtree.buildTree();
    gtreeJNI.write_GTree(gtree);
  }
  public static void main(String argv[]) {
    if (argv.length < REQUIRED_ARGS) {
      print_help();
    } else {
      // Required arguments
      int j = (argv.length - REQUIRED_ARGS);
      arg1 = argv[(j + 0)];

      // Optional arguments
      int i = 0;
      while (i < j) {
      	  if (argv[i].equals("-o")) {
	      opt_o = argv[(i + 1)];
      	  } else if (argv[i].equals("-I")) {
	      opt_I = Integer.parseInt(argv[(i + 1)]);
	  }
	  i++;
      }

      // Launch app
      System.out.printf("%s '%s'\n%s '%s'\n%s '%s'\n",
        "set edges file", arg1,
        "  opt. -o (output)", opt_o,
        "  opt. -I (indexing)", opt_I);
      run();
    }
    return;
  }
}

