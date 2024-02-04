import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestMeth {
    public static void main(String [] args){
        List<Integer> list = Arrays.asList(8,5,8,4,4,6,2,1);

        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("Shubham");

        System.out.println("Shubham1");
        System.out.println("Shubham2");
        System.out.println("Shubham3");
    }
}
