package minifunc;

import java.util.*;

public class MiniFuncTest {

    public static void main(String[] args) {

        /// given test cases
        TestCases.literalTests();
        TestCases.variableTests();
        TestCases.arithmeticExpressioTest();
        TestCases.logicalExpression();
        TestCases.conditionalExpression();
        TestCases.ifExpression();

        /// student test cases
        StudentTestCases.stringArithmeticTest();
        StudentTestCases.nestedExpressionsTest();
        StudentTestCases.logicalandConditionalExpressionsTest();
        StudentTestCases.loopAndFunctionsTest();
        StudentTestCases.arrayTest();

        BillTest.test1();
    }

}
