import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        System.out.println(calculateExpression(54));
    }

    private static double calculateExpression(long n) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i <= n; i++) {
            BigDecimal value = BigDecimal.valueOf(multiplyNumbersBetweenBorders(i, n));
            sum = sum.add(BigDecimal.ONE.divide(value, 6, RoundingMode.HALF_UP));
        }
        return sum.doubleValue();
    }

    private static long multiplyNumbersBetweenBorders(long left, long right) {
        long result = 1;
        for (long i = left + 1; i <= right; i++) {
            result *= i;
        }
        return result;
    }
}