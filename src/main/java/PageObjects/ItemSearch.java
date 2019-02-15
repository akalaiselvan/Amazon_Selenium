package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ItemSearch {
    WebDriver driver;

    @FindBy(css = "#searchDropdownBox")
    private WebElement selectCategoryinSearch;    /*Category dropdown element*/

    @FindBy(css = "#twotabsearchtextbox")         /*Search box textfield element*/
    private WebElement searchBox;

    @FindBy(css = ".nav-search-submit")            /*Search button*/
    private WebElement searchButton;

    public ItemSearch(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    /*Selects the category from the dropdown based on the input*/

    private void selectSearchCategory(String category) {
        new Select(selectCategoryinSearch).selectByVisibleText(category);
    }

    /*Search item in the search box textfield based on the credentials*/

    private void itemToSearch(String itemName) {
        searchBox.sendKeys(itemName);
    }


    /*To click search button*/

    private void clickSearch() {
        searchButton.click();
    }

    public void selectCategoryandSearchItem(String category, String itemname) {
        selectSearchCategory(category);
        itemToSearch(itemname);
        clickSearch();
    }
}
