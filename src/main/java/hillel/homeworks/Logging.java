package hillel.homeworks;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logging {
    private FileWriter out;

    Logging(String logFileName) {
        try {
            out = new FileWriter(logFileName, true);
        } catch (IOException e) {}
    }

    void close() {
        try {
            out.close();
        } catch (IOException e) {}
    }

    void print(String s) {
        try {
            System.out.print(s);                            //  на консоль
            out.write(LocalDateTime.now() + "  " + s);      //  в файл
        } catch (IOException e) {}
    }

    void println(String s) {
        print(s + "\n");
    }
}
