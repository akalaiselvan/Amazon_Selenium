import com.sun.org.apache.regexp.internal.RE;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportClass implements ITestListener {

    @Override
    public void onStart(ITestContext ar0){
        Reporter.log("Starting Test "+ar0.getName());
    }

//    @Override
//    public void onTestStart(ITestResult arg0){
//        Reporter.log("Starting test ..."+arg0.getName()+"\n \r \n \r");
//    }

//    @Override
//    public void onTestSuccess(ITestResult arg0){
//        Reporter.log("\n \r \n \r"+arg0.getName()+" test Completed");
//    }

    @Override
    public void onTestFailure(ITestResult arg0){
        Reporter.log(arg0.getName()+ " Test FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult arg0){
        Reporter.log(arg0.getName()+" Test skipped");
    }

    @Override
    public void onFinish(ITestContext arg0){
        Reporter.log(arg0.getName()+" Test Finished");
    }

}
