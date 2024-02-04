import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestMeth {
    public static void main(String [] args){
        List<Integer> list = Arrays.asList(8,5,8,4,4,6,2,1);

        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());

        int a = 6;
        int b = 4;
        boolean c;
        c=(a>b & a==6)?true:false;
        System.out.println(Sort.Direction.ASC.name()  );

    }
}
