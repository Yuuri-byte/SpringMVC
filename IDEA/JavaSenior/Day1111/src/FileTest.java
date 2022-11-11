import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test1() throws IOException {
        //创建File类实例
        File file = new File("hello.txt");//相对当前moudle
        file.createNewFile();//创建文件
        

    }



}
