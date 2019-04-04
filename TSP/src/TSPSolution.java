import java.util.ArrayList;
import java.util.Collections;

public class TSPSolution
{
	private ArrayList<Integer> tspsol;

	public TSPSolution(ArrayList<Integer> s)
	{

		tspsol = s;

	}
	public static ArrayList<Integer> RandPerm(int n){
		ArrayList<Integer> p = initialise(n);
		ArrayList<Integer> t = new ArrayList<>();

		while (p.size() > 0){
			int i = Utils.UI(0, p.size() - 1);
			t.add(p.get(i));
			p.remove(i);
		}


		return t;
	}

	private static ArrayList<Integer> initialise(int n) {

		ArrayList<Integer> t = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			t.add(i);
		}
		return t;
	}
	public TSPSolution(int n) 
	{
		tspsol = RandPerm(n);	
	}
	
	public double TSPFitness(double[][] distance)
	{
		if (tspsol.size() != distance.length) return(-1);

		int n = tspsol.size();

		double s = 0;

		for (int i = 0; i < n - 1; i++) {
			int a = tspsol.get(i);
			int b = tspsol.get(i + 1);
			s = s + distance[a][b];
		}
		int endCity = tspsol.get(tspsol.size() -1);
		int startCity = tspsol.get(0);
		s = s + distance[endCity][startCity];

		return s;
	}

	public TSPSolution smallChange(TSPSolution s){

		int i = 0;
		int j = 0;

		ArrayList<Integer> tsp = s.getSol();

		while (i == j){
			i = Utils.UI(0, tspsol.size() - 1);
			j = Utils.UI(0, tspsol.size() - 1);
		}

		Collections.swap(tsp, i, j);		

		return new TSPSolution(tsp);



	}

	
	public void print()
	{
		System.out.print(tspsol.toString());
	}
	
	public void println()
	{
		print();
		System.out.println();
	}
	public ArrayList<Integer> getSol(){
		return tspsol;
	}

	@SuppressWarnings("unchecked")
	public TSPSolution clone(){
		return new TSPSolution((ArrayList<Integer>) tspsol.clone());
	}
}