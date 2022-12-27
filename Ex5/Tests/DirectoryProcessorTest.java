import filesprocessing.DirectoryProcessor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DirectoryProcessorTest {

    private String [] input;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream errStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setup(){
        input = new String[2];
        input[0] = "Ex5\\Tests\\Test directory";
        input[1] = "Ex5\\Tests\\Command files\\filter";
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errStreamCaptor));
    }

    @After
    public void tearDown(){
        System.setOut(standardOut);
        System.setErr(standardErr);
    }

    @Test
    public void check1() {
        input[1] += "001.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("Warning in line 2", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\n" +
                     "file1.txt\r\n" +
                     "file2.txt\r\n" +
                     "New Text Document.txt\r\n" +
                     "file1.txt\r\n" +
                     "file2.txt", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check2() {
        input[1] += "002.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("Warning in line 4", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check3() {
        input[1] += "003.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\n" + "file1.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check4() {
        input[1] += "004.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check5() {
        input[1] += "005.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check6() {
        input[1] += "006.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check7() {
        input[1] += "007.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check8() {
        input[1] += "008.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nNew Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check9() {
        input[1] += "009.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("end of line no error","", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nNew Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check10() {
        input[1] += "010.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check11() {
        input[1] += "011.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check12() {
        input[1] += "012.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\n" +
                     "file1.txt\r\n" +
                     "file2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check13() {
        input[0] += "1";
        input[1] += "013.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
    assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (3).t\r\n" +
                     "New Text Document.txt\r\n" +
                     "file1.txt\r\n" +
                     "file2.txt\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check14() {
        input[0] += "1";
        input[1] += "014.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (3).t\r\n" +
                     "file2.txt\r\n" +
                     "New Text Document.txt\r\n" +
                     "file1.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check15() {
        input[0] += "1";
        input[1] += "015.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (3).t\r\n" +
                     "file2.txt\r\n" +
                     "New Text Document.txt\r\n" +
                     "file1.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check16() {
        input[0] += "1";
        input[1] += "016.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (3).t\r\n" +
                     "New Text Document.txt",
                     outputStreamCaptor.toString().trim());
    }


    @Test
    public void check17() {
        input[0] += "1";
        input[1] += "017.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("file2.txt\r\nfile1.txt", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check18() {
        input[0] += "1";
        input[1] += "018.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("maybe check order comments 5(4.2.3)","New Text Document.txt\r\n" +
                     "New Text Document (3).t\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2)", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check19() {
        input[0] += "1";
        input[1] += "019.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\n" +
                     "file1.txt", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check21() {
        input[0] += "1";
        input[1] += "021.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document.txt\r\nfile1.txt\r\nfile2.txt",
                     outputStreamCaptor.toString().trim());
    }

    @Test
    public void check30() {
        input[0] += "1";
        input[1] += "030.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (3).t\r\n" +
                     "New Text Document.txt\r\n" +
                     "file2.txt\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check47() {
        input[0] += "1";
        input[1] += "047.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("", errStreamCaptor.toString().trim());
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check50() {
        input[0] += "1";
        input[1] += "050.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("Warning in line 2\r\n" +
                     "Warning in line 4", errStreamCaptor.toString().trim());
        assertEquals("New Text Document (2)\r\n" +
                     "New Text Document (2).flt\r\n" +
                     "New Text Document (2).t\r\n" +
                     "New Text Document (2).t.t.e.w.asda.dasd.a.z\r\n" +
                     "New Text Document (2).yes\r\n" +
                     "New Text Document (3)\r\n" +
                     "New Text Document (3).t\r\n" +
                     "New Text Document.txt\r\n" +
                     "file1.txt\r\n" +
                     "file2.txt", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check51() {
        input[0] += "1";
        input[1] += "051.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertTrue(errStreamCaptor.toString().trim().startsWith("ERROR: "));
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check52() {
        input[0] += "1";
        input[1] += "052.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertTrue(errStreamCaptor.toString().trim().startsWith("ERROR: "));
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check53() {
        input[0] += "1";
        input[1] += "052.flt";
        String [] bla = new String[1];
        try {
            DirectoryProcessor.main(bla);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertTrue(errStreamCaptor.toString().trim().startsWith("ERROR: "));
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check54() {
        input[0] += "1";
        input[1] += "052.flt";
        String temp = input[1];
        input[1] = input[0];
        input[0] = temp;
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
//        assertTrue(errStreamCaptor.toString().trim().startsWith("ERROR: "));
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check55() {
        input[0] += "1";
        input[1] += "052.flt";
        String temp = input[0];
        input[0] = input[1];
        input[1] = temp;
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertTrue(errStreamCaptor.toString().trim().startsWith("ERROR: "));
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    public void check56() {
        input[0] += "1";
        input[1] += "054.flt";
        try {
            DirectoryProcessor.main(input);
        }
        catch (Exception e){
            fail("No exception should leave");
        }
        assertEquals("",errStreamCaptor.toString());
        assertFalse(errStreamCaptor.toString().trim().contains("!"));
        assertEquals("", outputStreamCaptor.toString().trim());
    }


}