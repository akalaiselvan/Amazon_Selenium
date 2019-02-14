package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;


public class FetchResults {
    WebDriver driver;
    WebDriverWait wait;

    public FetchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "#atfResults")
    private WebElement searchResults;

    @FindBy(tagName = "a")
    private WebElement findAnchorTag;

    @FindBy(css = "#title")
    private WebElement getTitle;

    @FindBy(css = "#mediaTabs_tabSet")
    private WebElement editionWisePrice;

    @FindBy(css = "#tmmSwatches")
    private WebElement editionWisePriceByclass;

    @FindBy(css = "#bylineInfo")
    private WebElement authorName;

    @FindBy(css = "#acrPopover")
    private WebElement customerReview;

    @FindBy(css = "#availability")
    private WebElement stockAvailablity;





    public List<String> getSearchResultsinList() {
        List<String> searchResult = new ArrayList<String>();
        wait=new WebDriverWait(driver,90);
        wait.until(ExpectedConditions.elementToBeClickable(searchResults));
        List<WebElement> webdlist = searchResults.findElements(By.tagName("li"));
        for (WebElement webElement : webdlist) {
            searchResult.add(webElement.getAttribute("id"));
        }
        return searchResult;
    }


    public void clickresult(int nthResult) {
        WebElement element = driver.findElement(By.cssSelector("#" + getSearchResultsinList().get(nthResult)));
        String getAttrib = findAnchorTag.getAttribute("title");
        element.findElement(By.linkText(getAttrib)).click();
    }

    public String getResultTitle() {
        List<String> resultDetails=new ArrayList<String>();
        List<String> getResultTitles = new ArrayList<String>();
        int spancount = 0;
        wait=new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(getTitle));
        List<WebElement> elementList = getTitle.findElements(By.tagName("span"));
        for (WebElement element : elementList) {
            getResultTitles.add(element.getAttribute("id"));
            WebElement webElement;
            try {
                webElement = driver.findElement(By.cssSelector("#" + getResultTitles.get(spancount)));
                resultDetails.add(webElement.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            spancount++;
        }
        return resultDetails.toString();
    }


    public String getAuthorName() {
        List<String> author=new ArrayList<String>();
        List<WebElement> forAuthor = authorName.findElements(By.tagName("a"));
        int n = 1;
        for (WebElement webElement : forAuthor) {
            if (!webElement.getText().equals("")) {
                author.add(webElement.getText());
            }
            n++;
        }
        return author.toString();
    }

    public String getEditionWisePrice() {
        List<String> editionwisePrice=new ArrayList<String>();
        List<WebElement> getspan = null;
        try {
            getspan = editionWisePrice.findElements(By.tagName("li"));
        } catch (Exception e) {
            try {
                getspan = editionWisePriceByclass.findElements(By.tagName("li"));
            } catch (Exception e1) {
                e.printStackTrace();
                return "Edition wise price not available";            }
            for (WebElement element:getspan){
                editionwisePrice.add(element.getText());
            }
            return editionwisePrice.toString();

        }
        int count = getspan.size();
        int n = 1;
        for (WebElement webElement : getspan) {
            if (n <= count - 2) {
                WebElement edition = driver.findElement(By.cssSelector("#" + webElement.getAttribute("id")));
                editionwisePrice.add(edition.getText());
                n++;
            }
        }
        return editionwisePrice.toString();
    }

    public String getCustomerReview() {
        wait=new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.elementToBeClickable(customerReview));
        return customerReview.getAttribute("title");
    }

    public String getStockAvailablity() {
        wait=new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.elementToBeClickable(stockAvailablity));
        return stockAvailablity.getText();
    }
}

