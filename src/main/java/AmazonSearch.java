import PageObjects.FetchResults;
import PageObjects.ItemSearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners(ReportClass.class)
public class AmazonSearch {
    public  URL url;
    WebDriver driver;
    private ItemSearch itemSearch;
    private FetchResults fetchResults;
    private ReadFromExcel readFromExcel=new ReadFromExcel();


    private String getGeckoPath(){
        return readFromExcel.readFromCell(0,1);
    }

    private String getLink(){
        return readFromExcel.readFromCell(1,1);
    }

    @BeforeTest
    public void initialize(){

        /*Checking assert not null for gecko driver path which is reading from excel file*/

        Assert.assertNotEquals(getGeckoPath(),"","Check gecko path in excel sheet");
        System.setProperty("webdriver.gecko.driver",getGeckoPath());
        driver=new FirefoxDriver();

        /*Checking assert for getting link*/
        Assert.assertNotEquals(getLink(),"","Check link in excel sheet");
        driver.get(getLink());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        TestNG testNG=new TestNG();
        testNG.setUseDefaultListeners(true);
    }



    @Test(priority = 1)
    @Parameters({"category","itemname"})
    public void selectCategoryandSearchitem(String category,String itemname){
        itemSearch=new ItemSearch(driver);
        itemSearch.selectCategoryandSearchItem(category,itemname);
    }

    @Test(priority = 2)
    @Parameters({"nthresultToPick"})
    public void pickResult(int nthresultToPick){
        fetchResults=new FetchResults(driver);
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
    
    @AfterTest
    private void closeDriver(){
        driver.quit();
    }
}
