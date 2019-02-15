# Amazon_Selenium

Developed the test script using java+selenium+maven with POM model along with TestNG framewrok.

### Test Purpose

      Based on the test input ,it search item in amazon site and get data from the result.

### How it works ? 

      * This test will open Amazon site, selects category to search and type the item to search in searchbar , click search button then.
      * Based on the nthResulttopick in testNg.xml file it clicks the result (Ex: if the vale is 2 , it picks the second result)
      * From the result clicked , it get the data like Result title , author ,Editionwise price, stock availability  , Average Customer       
      review
      
### Test inputs 
   
      Category , Item name and nth Result to pick are generic and these could be changed in TestNG.xml file by changing the parameters. 
      
      Some methods in the test might not needed for all cases , ( For example Editionwise price and Author name ) so in these scenarios    
      those methods can be easily removed from test by marking the as 'exclude' in xml file.
      
### Test Reports 

      After test , we can see the results in emailable-report file under test-output folder. For secondary purpose those results would 
      get printed in console as well.
      
### Structure 

      Page Object model have been used in the test , also used Page Factory to initialize the elements. You can find couple of Page objects one for Amazon search page and other one for Fetching data from the results 
      
      Test will read Gecko driver path and Amazon link from the excel file(TestSheet.xls).Used jxl library file to read text from excel.
