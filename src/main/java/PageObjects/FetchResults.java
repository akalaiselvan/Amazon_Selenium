

/*Page Object class for getting data from the search result
*
* */

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
    private WebElement searchResults;      //element which contains list of results

    @FindBy(tagName = "a")
    private WebElement findAnchorTag;      // find using html anchor tag

    @FindBy(css = "#title")
    private WebElement getTitle;

    @FindBy(css = "#mediaTabs_tabSet")
    private WebElement editionWisePrice;   // element to get edition wise price

    @FindBy(css = "#tmmSwatches")
    private WebElement editionWisePriceByclass;  // Second method to get Editionwise price

    @FindBy(css = "#bylineInfo")
    private WebElement authorName;            // element to get author name

    @FindBy(css = "#acrPopover")
    private WebElement customerReview;        // element to get customer review

    @FindBy(css = "#availability")
    private WebElement stockAvailablity;      // element for getting stock availability




    /* This method will return the result element's id as
    * List*/

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


    /*From the search result list it click the
    nth result based on the input*/

    public void clickresult(int nthResult) {
        WebElement element = driver.findElement(By.cssSelector("#" + getSearchResultsinList().get(nthResult)));
        String getAttrib = findAnchorTag.getAttribute("title");
        element.findElement(By.linkText(getAttrib)).click();
    }


    /*To get the result title (For example : Book name)*/
    public String getResultTitle() {
        List<String> resultDetails=new ArrayList<String>();
        List<String> getResultTitles = new ArrayList<String>();
        int spancount = 0;
        wait=new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(getTitle));

        /*Getting elements in list based on the html tag 'span'*/

        List<WebElement> elementList = getTitle.findElements(By.tagName("span"));
        for (WebElement element : elementList) {

            /*Adding the element's id in list*/

            getResultTitles.add(element.getAttribute("id"));
            WebElement webElement;
            try {

                /*From the list of ids,getting the element using css selector*/

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
            /*Using html tag 'li' getting the element */

            getspan = editionWisePrice.findElements(By.tagName("li"));
        } catch (Exception e) {
            try {

                /*Editionwise price might get fail in above case, so trying another method*/

                getspan = editionWisePriceByclass.findElements(By.tagName("li"));
            } catch (Exception e1) {
                e.printStackTrace();

                /*If both method fails it assumes editionwise price is not available
                * and returns the same*/

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

