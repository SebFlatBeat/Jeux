import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnuthAlgo {

        private static final Random rand = new Random();

        public static <T> Knuth.Fonction<T, List<T>> setOfN(final int n) {
            return new Knuth.Fonction<T, List<T>>() {
                private List<T> tentative = new ArrayList<T>(n);
                private int i = 0;

                public List<T> appelle (T item) {
                    if (++i <= n) {
                        tentative.add(item);
                    } else if (rand.nextInt(i) < n) {
                        tentative.set(rand.nextInt(n), item);
                    }
                    return tentative;
                }
            };
        }
    }
