package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ItemSearch {
    WebDriver driver;

    @FindBy(css = "#searchDropdownBox")
    private WebElement selectCategoryinSearch;

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(css = ".nav-search-submit")
    private WebElement searchButton;

    public ItemSearch(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void selectSearchCategory(String category) {
        new Select(selectCategoryinSearch).selectByVisibleText(category);
    }

    public void itemToSearch(String itemName) {
        searchBox.sendKeys(itemName);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void selectCategoryandSearchItem(String category, String itemname) {
        //selectSearchCategory(category);
        itemToSearch(itemname);
        clickSearch();
    }
}
