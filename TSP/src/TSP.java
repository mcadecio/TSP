import java.util.ArrayList;

public class TSP 
{

	public static ArrayList<Integer> RunTSP(double[][] d, int iterations){

		if (d == null || d.length < 1) return null;
		if (iterations < 1) return null;

		double temp = calculateTemp(d);

		double coolingRate = calcCR(temp, iterations);

		TSPSolution s = new TSPSolution(d.length);


		s = SA(d, s,temp, iterations, coolingRate);
		return (s.getSol());

	}



	private static double calculateTemp(double[][] d) {
		double averageDistance = getAverage(d) * d.length;
		averageDistance = averageDistance / 2;
		return averageDistance;
	}
	private static double getAverage(double[][] array){
		int counter=0;
		double sum = 0;
		for(int i=1;i<array.length;i++){
			for(int j=i;j<array.length;j++){
				sum = sum+array[i][j];
				counter++;
			}
		}

		return sum / counter;
	}

	public static TSPSolution SA(double[][] d,TSPSolution s, double t, int iter, double coolRate ){
		TSPSolution currentSol = s;



		for (int i = 0; i < iter; i++){
			double f1 = currentSol.TSPFitness(d);

			TSPSolution newSol = currentSol.smallChange(currentSol.clone());
			double f2 = newSol.TSPFitness(d);

			if(f2 > f1){
				double p = PR(f2, f1, t);

				if (p > Utils.UR(0, 1)){

					currentSol =  newSol;
					System.out.println(currentSol.TSPFitness(d));
				} 

			} 
			else {
				currentSol = newSol;

			}

			t = coolRate * t;
		}
		return currentSol;
	}

	private static double PR(double f2, double f1, double temp){
		double delta = Math.abs(f1 - f2);
		delta = -1 * delta;
		double r = Math.exp(delta / temp) ;
		return r;
	}
	private static double calcCR(double temp, int iter) {
		double ti = 0.001;
		double power = 1.0 / iter;
		double tv = ti / temp;


		return Math.pow(tv, power);
	}
}