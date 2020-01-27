import ibank.Bank;
import ibank.BaseBankTest;

public class MyTest extends BaseBankTest {

    @Override
    protected Bank createBank() {
        return new MyBank();
    }
}
