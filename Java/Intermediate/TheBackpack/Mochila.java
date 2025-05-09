
/**
 *
 * Gerardo  A. Moguel Rovelo
 * Ejericio Mochila + seeds
 */
public class Mochila {

    public static void genArticulos(int cuantos, double peso[], double valor[]) {
        double valorMin = 10.0;
        double valorMax = 50.0;
        double pesoMin = 0.250;
        double pesoMax = 1.50;

        double deltaPeso = pesoMax - pesoMin;
        double deltaValor = valorMax - valorMin;

        for (int k = 0; k < cuantos; k++) {
            peso[k] = pesoMin + deltaPeso * Math.random();
            valor[k] = valorMin + deltaValor * Math.random();
        }

    }

    public static int[] binario(int x, int n) {
        int b[] = new int[n];
        for (int k = 0; k < n; k++) {
            b[k] = x % 2;
            x = x / 2;
        }

        return b;
    }

    public static void main(String[] args) {

        Long SEMILLA = args.length > 0 ? Long.parseLong(args[0]) : (long) (10000.0 * Math.random() + 1);
        java.util.Random rnd = new java.util.Random(SEMILLA);
        System.out.println("Seed: " + SEMILLA);

        int cuantos = 20;  // artículos de donde elegir
        int nums = (int) Math.pow(2, cuantos);

        double capMochila = 10.0;
        double pesoMochila = 0.0;
        double valorMochila = 0.0;

        double pesoArt[] = new double[cuantos];
        double valorArt[] = new double[cuantos];
        genArticulos(cuantos, pesoArt, valorArt);

        double pesoCombinacion;
        double valorCombinacion;
        int combinacionMax = 0;
        int b[];
        for (int k = 1; k < nums; k++) {
            b = binario(k, cuantos);
            pesoCombinacion = 0.0;
            valorCombinacion = 0.0;
            for (int j = 0; j < cuantos; j++) {
                pesoCombinacion += pesoArt[j] * b[j];
                valorCombinacion += valorArt[j] * b[j];
            }

            if (pesoCombinacion < capMochila) {
                if (valorCombinacion > valorMochila) {
                    valorMochila = valorCombinacion;
                    pesoMochila = pesoCombinacion;
                    combinacionMax = k;
                }
            }
        }
        System.out.println("ValorMochila:" + valorMochila + ", PesoMochila:" + pesoMochila);
        b = binario(combinacionMax, cuantos);

        String strRes = "";
        for (int k = cuantos - 1; k >= 0; k--) {
            strRes = b[k] + strRes;
        }

        System.out.println("Cuantos_obj:" + cuantos + ", Num_combinacion:" + combinacionMax + ", Num_combinacion_binario:" + strRes);

        System.out.println("Seed: " + SEMILLA + "\n");
    }
}
