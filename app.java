import cn.edu.tsinghua.cs.dbgroup.gtreeJNI.*;

public class app {
  static {
    try { System.loadLibrary("gtree"); }
    catch (UnsatisfiedLinkError e) {
      System.err.println("Native code library failed to load: "+e);
      System.exit(1);
    }
  }

  static long t0, t1;

  public static void main(String argv[]) {

    t0 = System.currentTimeMillis();
    gtreeJNI.load("cd1.gtree");
    t1 = System.currentTimeMillis();
    System.out.println("Load Gtree: "+(t1 - t0)+" ms");

    t0 = System.currentTimeMillis();
    G_Tree gt = gtreeJNI.get();
    t1 = System.currentTimeMillis();
    System.out.println("Get Gtree: "+(t1 - t0)+" ms");

    IntVector route = new IntVector();
    t0 = System.currentTimeMillis();
    gt.find_path(82, 88, route);
    t1 = System.currentTimeMillis();
    System.out.println("Gtree.find_path: "+(t1 - t0)+" ms");

    System.out.print("82 ~> 88:");
    for (Integer v : route)
      System.out.print(" "+v);
    System.out.print("\n");
  }
}

