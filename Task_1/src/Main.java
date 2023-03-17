import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println(intToIp(2149583360L));
        System.out.println(ipToInt(intToIp(2149583360L)));
    }

    private static String intToIp(long number) {
        int mask = (1 << 8) - 1;
        return String.valueOf(number >> 24 & mask) + '.' +
                (number >> 16 & mask) + '.' +
                (number >> 8 & mask) + '.' +
                (number & mask);
    }

    private static long ipToInt(String ip) {
        List<Integer> octets = Stream.of(ip.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return ((long) octets.get(0) * (int) Math.pow(256, 3))
                + ((long) octets.get(1) * (int) Math.pow(256, 2))
                + (octets.get(2) * 256)
                + octets.get(3);
    }
}