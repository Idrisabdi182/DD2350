/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;
    int x;
    int y;
    int e;
    List<List<Integer>> edges;
    List<List<Integer>> matchededges;
    
    void readBipartiteGraph() {
	// Läs antal hörn och kanter
	x = io.getInt();
	y = io.getInt();
	e = io.getInt();
	edges = new ArrayList<>();
	// Läs in kanterna
	for (int i = 0; i < e; ++i) {
        List<Integer> currentedge = new ArrayList<>();
	    int a = io.getInt();
	    int b = io.getInt();
        currentedge.add(a);
        currentedge.add(b);
        edges.add(currentedge);
	}
    }
    
    
    void writeFlowGraph() {
	int v = x + y + 2, e = e, s = 0, t = x + y + 1;

	
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v);
	io.println(s + " " + t);
	io.println(e);

    // Hantera source noden
    for (int i = 1; i <= x; i++) {
        int a = s, b = i, c = 1;
        // Kant från a till b med kapacitet c
	    io.println(a + " " + b + " " + c);
    }
	for (int i = 0; i < e; ++i) {
	    currentedge = edges.get(i);
        int a = currentedge.get(0), b = currentedge.get(1), c = 1;
        io.println(a + " " + b + " " + c );
	}

    // Hantera sink noden 
    for (int i = x+1; i <= y; i++) {
        int a = i, b = t, c = 1;
        io.println(a + " " + b + " " + c );
    }
	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();
	
	// Debugutskrift
	System.err.println("Skickade iväg flödesgrafen");
    }
    
    
    void readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int totflow = io.getInt();
	int e = io.getInt();
    matchededges = new ArrayList<>();
	
	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    int a = io.getInt();
	    int b = io.getInt();
	    int f = io.getInt();
        if (f == 1) {
            List<Integer> matchededge = new ArrayList<>();
            matchededge.add(a);
            matchededge.add(b);
            matchededges.add(matchededge);
        }
	}
    }
    
    
    void writeBipMatchSolution() {
	int x = x, y = y, maxMatch = matchededges.size();
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(maxMatch);
	
	for (int i = 0; i < maxMatch; ++i) {
        List<Integer> currentedge = matchededges.get(i);
	    int a = currentedge.get(0), b = currentedge.get(1);
	    // Kant mellan a och b ingår i vår matchningslösning
	    io.println(a + " " + b);
	}
	
    }
    
    BipRed() {
	io = new Kattio(System.in, System.out);
	
	readBipartiteGraph();
	
	writeFlowGraph();
	
	readMaxFlowSolution();
	
	writeBipMatchSolution();

	// debugutskrift
	System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	new BipRed();
    }
}
