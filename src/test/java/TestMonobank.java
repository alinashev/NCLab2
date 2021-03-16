import com.example.lab2.controller.datacontrollers.DataPullerMono;
import com.example.lab2.model.parsers.ParserByJackson;
import org.junit.jupiter.api.Test;

public class TestMonobank {

    DataPullerMono dataPullerMono = new DataPullerMono();

    @Test
    public void getData(){
        ParserByJackson.parserByJackson().parseJSON(dataPullerMono.getData("data"));
        System.out.println(ParserByJackson.parserByJackson().currentRate("USD"));
    }
}
