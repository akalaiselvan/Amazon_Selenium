import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ReadFromExcel {
        Workbook workbook;


    public String readFromCell(int column,int row){
        try {
            workbook= Workbook.getWorkbook(new File(System.getProperty("user.dir") + "/TestSheet.xls"));

        } catch (IOException|BiffException e) {
            e.printStackTrace();
        }
        Sheet sheet=workbook.getSheet("sheet");
        return sheet.getCell(column,row).getContents();
    }


    @Test
    public void tests(){
//        String s=System.getProperty("user.dir");
//        System.out.println(s);
        String s=readFromCell(0,1);
        System.out.println(s);
    }
}
