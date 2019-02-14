import PageObjects.FetchResults;
import PageObjects.ItemSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.net.URL;

@Listeners(ReportClass.class)
public class OpenLink {
    public  URL url;
    WebDriver driver;
    private ItemSearch itemSearch;
    private FetchResults fetchResults;
    private ReadFromExcel readFromExcel=new ReadFromExcel();


    private String getGeckoPath(){
        String s = readFromExcel.readFromCell(0,1);
        return s;
    }

    @BeforeTest
    public void getLink(){
        System.setProperty("webdriver.gecko.driver",getGeckoPath());
        driver=new FirefoxDriver();
        driver.get(readFromExcel.readFromCell(1,1));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestNG testNG=new TestNG();
        testNG.setUseDefaultListeners(true);
    }



    @Test(priority = 1)
    @Parameters({"category","itemname"})
    public void selectCategoryandSearchitem(String category,String itemname){
        itemSearch=new ItemSearch(driver);
        fetchResults=new FetchResults(driver);
        itemSearch.selectCategoryandSearchItem(category,itemname);
    }

    @Test(priority = 2)
    @Parameters({"nthresultToPick"})
    public void pickResult(int nthresultToPick){
        fetchResults.clickresult(nthresultToPick);
    }

    @Test(priority = 3)
    public void getResultTitle(){
        printResultInReportandConsole("Result title : "+fetchResults.getResultTitle());
    }

    @Test(priority = 4)

    public void getAuthorName(){
        printResultInReportandConsole("Author name : "+fetchResults.getAuthorName());
    }

    @Test(priority = 5)
    public void getEditionwisePrice(){
        printResultInReportandConsole("Price : "+fetchResults.getEditionWisePrice());
    }

    @Test(priority = 6)
    public void getAverageReview(){
        printResultInReportandConsole("avg review : "+fetchResults.getCustomerReview());
    }

    @Test(priority = 7)
    public void getStockAvailability(){
        printResultInReportandConsole("Stock availability : "+fetchResults.getStockAvailablity());
    }

    private void printResultInReportandConsole(String log){
        Reporter.log(log);
        System.out.println(log);
    }
}
