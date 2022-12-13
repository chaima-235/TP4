package ua.karatnyk;

import org.junit.Test;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.OfflineJsonWorker;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;



import static junit.framework.TestCase.assertEquals;

public class TestCurrencyConvertor {

    @Test
    public void test_exception_amount_D1() throws ParseException {
        var border1 = CurrencyConvertor.convert(0 ,"CAD","USD",new OfflineJsonWorker().parser());
        var typical = CurrencyConvertor.convert(5000 ,"CAD","USD",new OfflineJsonWorker().parser());
        var border2 = CurrencyConvertor.convert(10000 ,"CAD","USD",new OfflineJsonWorker().parser());

        assertEquals( 0.0, border1);
        assertEquals(3719.267771245323 , typical);
        assertEquals(7438.535542490646 , border2);
    }

    @Test
    public void test_exception_amount_D2() throws ParseException {
        Exception exceptionBorder1 = assertThrows(Exception.class,()-> CurrencyConvertor.convert(-1 ,"CAD","USD",new OfflineJsonWorker().parser()));
        Exception exceptionTypical = assertThrows(Exception.class,()-> CurrencyConvertor.convert(-20000 ,"CAD","USD",new OfflineJsonWorker().parser()));
        assertEquals("Not correct format currency"
                + "" , exceptionBorder1.getMessage());

        assertEquals("Not correct format currency"
                + "" , exceptionTypical.getMessage());

    }

    @Test
    public void test_exception_amount_D3() throws ParseException {
        Exception exceptionBorder1 = assertThrows(Exception.class,()-> CurrencyConvertor.convert(10001 ,"CAD","USD",new OfflineJsonWorker().parser()));
        Exception exceptionTypical = assertThrows(Exception.class,()-> CurrencyConvertor.convert(20000 ,"CAD","USD",new OfflineJsonWorker().parser()));
        assertEquals("Not correct format currency"
                + "" , exceptionBorder1.getMessage());

        assertEquals("Not correct format currency"
                + "" , exceptionTypical.getMessage());

    }

    @Test
    public void test_exception_rates_D1() throws ParseException {
        var  rate1 = CurrencyConvertor.convert(200 ,"CAD","INR",new OfflineJsonWorker().parser());
        var  rate2 = CurrencyConvertor.convert(150 ,"USD","EUR",new OfflineJsonWorker().parser());

        assertEquals(12160.46261008993 , rate1);
        assertEquals(146.43746924813146, rate2);
    }

    @Test
    public void test_exception_rates_D2() throws ParseException {
        Exception exception1 = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200 ,"CAD","HNL",new OfflineJsonWorker().parser()));
        Exception exception2 = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200 ,"RSD","CAD",new OfflineJsonWorker().parser()));
        Exception exception3 = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200 ,"KES","SLL",new OfflineJsonWorker().parser()));


        assertEquals("Not correct format currency"
                + "" , exception1.getMessage());
        assertEquals("Not correct format currency"
                + "" , exception2.getMessage());
        assertEquals("Not correct format currency"
                + "" , exception3.getMessage());
    }

    // tests boite blanche

    // test couverture condition
    @Test
    public void test_couverture_conditions() throws ParseException {

        var res_fff = CurrencyConvertor.convert(2000,"USD","AUD",new OfflineJsonWorker().parser());
        var res_ftf = assertThrows(Exception.class,()-> CurrencyConvertor.convert(2000,"HNL","AUD",new OfflineJsonWorker().parser()));
        var res_ftt = assertThrows(Exception.class,()-> CurrencyConvertor.convert(2000,"HNL","RON",new OfflineJsonWorker().parser()));
        var res_fft = assertThrows(Exception.class,()-> CurrencyConvertor.convert(2000,"CAD","DIN",new OfflineJsonWorker().parser()));
        var res_ttt = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200000,"IQD","CUP",new OfflineJsonWorker().parser()));
        var res_ttf = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200000,"IQD","CHF",new OfflineJsonWorker().parser()));
        var res_tff = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200000,"GBP","CHF",new OfflineJsonWorker().parser()));
        var res_tft = assertThrows(Exception.class,()-> CurrencyConvertor.convert(200000,"GBP","IQD",new OfflineJsonWorker().parser()));
        assertEquals( 3029.3948813270754, res_fff);
        assertEquals("Not correct format currency"
                + "" , res_ftf.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_ftt.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_fft.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_ttt.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_ttf.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_tff.getMessage());
        assertEquals("Not correct format currency"
                + "" , res_tft.getMessage());
    }
}
