package minifunc;

public class StudentTestCases {
    
    private StudentTestCases() {
    }
    
    static void stringArithmeticTest() {
        PrintExpression.print(new StringLiteral("String Arithmetic Expressions Test"));
        PrintExpression.print(new StringLiteral("----------------------------------"));
        PrintExpression.print();
        
        Literal str = StringLiteral.create("Hey");
        Literal str2 = StringLiteral.create("e");
        Variable str5 = BooleanVariable.create("booleanStr", true);
        Literal str3 = StringLiteral.create("Heyeyeyey");
        Literal doubleLiteral = DoubleLiteral.create(-3);
        
        PrintExpression.print(new StringLiteral("str = "), str);
        PrintExpression.print(new StringLiteral("str2 = "), str2.execute());
        PrintExpression.print(new StringLiteral(str5 + " = " + str5.execute()));
        PrintExpression.print(new StringLiteral("doubleLiteral = " + doubleLiteral));
        PrintExpression.print();
        
        PrintExpression.print(new StringLiteral("str * doubleLiteral = " + new MultiplyExpression(str, doubleLiteral).getValue()));
        PrintExpression.print(new StringLiteral("str * str = " + new MultiplyExpression(str, str).getValue()));
        
        PrintExpression.print(new StringLiteral("str - str2 = " + new SubtractExpression(str, str2).getValue()));
        
        PrintExpression.print(new StringLiteral("str5 * booleanStr = " + new MultiplyExpression(str5, str5).getValue()));
        
        PrintExpression.print(new StringLiteral("str3 = " + str3.getValue()));
        PrintExpression.print(new StringLiteral("str3 / str2 = " + new DivisionExpression(str3, str2).getValue()));
        
        PrintExpression.print(new StringLiteral("doubleLiteral - str2 " + new SubtractExpression(doubleLiteral, str2).getValue()));
        PrintExpression.print(new StringLiteral("doubleLiteral - true = " + new SubtractExpression(doubleLiteral, new BooleanLiteral(true)).getValue()));
        
        PrintExpression.print(new StringLiteral("-(str) = " + new NegateExpression(str).execute()));
        
        PrintExpression.print(new StringLiteral(""));
        PrintExpression.print(new StringLiteral(""));
        
    }
    
    static void nestedExpressionsTest() {
        PrintExpression.print(new StringLiteral("Nested Expressions Test"));
        PrintExpression.print(new StringLiteral("------------------------"));
        PrintExpression.print(new StringLiteral(""));
        
        Expression expression1;
        expression1 = new AddExpression(new AddExpression(IntegerLiteral.create(5), IntegerLiteral.create(3)), IntegerLiteral.create(3));
        Expression expression2;
        expression2 = new MultiplyExpression(expression1, new IntegerLiteral(5));
        Expression expression3;
        expression3 = new ConditionalExpression(expression2, new IntegerLiteral(55), ConditionalOperator.GreaterEqual);
        PrintExpression.print(new StringLiteral(expression3 + " = " + expression3.getValue()));
        
        PrintExpression.print(new StringLiteral(""));
        PrintExpression.print(new StringLiteral(""));
        
    }
    
    static void logicalandConditionalExpressionsTest() {
        PrintExpression.print(new StringLiteral("Logical and Conditional Expression Test"));
        PrintExpression.print(new StringLiteral("--------------"));
        PrintExpression.print(new StringLiteral(""));
        
        Literal booleanLiteral1 = BooleanLiteral.create(true);
        Literal booleanLiteral2 = BooleanLiteral.create(false);
        Literal booleanLiteral3 = BooleanLiteral.create(true);
        Literal integerLiteral = IntegerLiteral.create(0);
        PrintExpression.print(new StringLiteral(booleanLiteral1 + " = " + booleanLiteral1.execute()));
        Expression or = new OrExpression(booleanLiteral2, integerLiteral);
        Expression and = new AndExpression(booleanLiteral2, booleanLiteral3);
        Expression xor = new XorExpression(booleanLiteral2, booleanLiteral3);
        PrintExpression.print(new StringLiteral(or + " = " + or.execute()));
        PrintExpression.print(new StringLiteral(and + " = " + and.execute()));
        PrintExpression.print(new StringLiteral(xor + " = " + xor.execute()));
        
        Expression condition1 = new ConditionalExpression(new IntegerLiteral(1), new BooleanLiteral(true), ConditionalOperator.LessEqual);
        PrintExpression.print(condition1.execute());
        
        Expression condition2 = new ConditionalExpression(new IntegerVariable("int", 5), new DoubleLiteral(5.5), ConditionalOperator.Less);
        PrintExpression.print(condition1.execute());
        IfExpression ifCondition = new IfExpression(condition1, condition2, new BooleanLiteral(false));
        PrintExpression.print(new StringLiteral(ifCondition.toString() + "  -->" + ifCondition.execute()));
        
        Expression variable1 = new IntegerLiteral(1);
        Expression variable2 = new BooleanLiteral(true);
        Expression variable3 = new BooleanLiteral(false);
        
        Expression condition = new ConditionalExpression(new IntegerLiteral(1), new BooleanLiteral(true), ConditionalOperator.LessEqual);
        
        Expression andCondition = new AndExpression(variable1, variable2);
        PrintExpression.print(new StringLiteral(andCondition + "  --> " + andCondition.execute()));
        
        Expression orCondition = new OrExpression(variable1, new IntegerLiteral(2));
        PrintExpression.print(new StringLiteral(orCondition + " --> " + orCondition.execute()));
        
        Expression xorCondition = new XorExpression(condition, variable3);
        PrintExpression.print(new StringLiteral(xorCondition + " --> " + xorCondition.execute()));
        
        PrintExpression.print(new StringLiteral(""));
        PrintExpression.print(new StringLiteral(""));
        
    }
    
    static void loopAndFunctionsTest() {
        PrintExpression.print(new StringLiteral("Loops and Functions Test"));
        PrintExpression.print(new StringLiteral("--------------"));
        PrintExpression.print(new StringLiteral(""));

        //Factorial using loop
        PrintExpression.print(new StringLiteral("**Factorial using loop**"));
        PrintExpression.print(new StringLiteral(""));
        
        IntegerVariable variable1 = new IntegerVariable("Variable", 5);
        IntegerVariable result = new IntegerVariable("Result", 1);
        ConditionalExpression condition = new ConditionalExpression(variable1, new IntegerLiteral(0), ConditionalOperator.Greater);
        Expression block = new AssignExpression(result, new MultiplyExpression(variable1, result));
        Expression loop = new LoopExpression(variable1, condition, new SubtractExpression(variable1, new IntegerLiteral(1)), block);
        PrintExpression.print(new StringLiteral(loop.toString()));
        loop.execute();
        PrintExpression.print(new StringLiteral(result + " = " + result.getValue()));

        //Test function
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable funcVariable1 = new IntegerVariable("funcVariable1", 5);
        IntegerVariable funcVariable2 = new IntegerVariable("funcVariable2", 3);
        IntegerVariable returnValue = new IntegerVariable("returnValue");
        ConditionalExpression condition2 = new ConditionalExpression(funcVariable1, funcVariable2, ConditionalOperator.Equal);
        Expression ifExpression = new IfExpression(condition2, new AssignExpression(returnValue, funcVariable1), new AssignExpression(returnValue, funcVariable2));
        FunctionExpression function1 = new FunctionExpression("test", returnValue, ifExpression, loop);
        
        PrintExpression.print(new StringLiteral(function1.toString()));
        PrintExpression.print(new StringLiteral(function1.outStr()));

        //Factorial using recursive func
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable input = new IntegerVariable("Input", 5);
        IntegerVariable result2 = new IntegerVariable("Result", 1);
        ConditionalExpression condition3 = new ConditionalExpression(input, new IntegerLiteral(0), ConditionalOperator.Equal);
        Expression block2 = new AssignExpression(result2, new MultiplyExpression(input, result2));
        Expression test = new AssignExpression(input, new SubtractExpression(input, new IntegerLiteral(1)));
        FunctionExpression factorial = new RecursiveFunctionExpression("Factorial", new ArrayExpression(input), result2, condition3, null, block2, test);
        PrintExpression.print(new StringLiteral(factorial.toString()));
        factorial.execute(new IntegerLiteral(7));
        PrintExpression.print(new StringLiteral(factorial.outStr()));
        factorial.execute(new IntegerLiteral(0));
        PrintExpression.print(new StringLiteral(factorial.outStr()));
        factorial.execute(new IntegerLiteral(3));
        PrintExpression.print(new StringLiteral(factorial.outStr()));

        //Max value with 2 inputs
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable max1 = new IntegerVariable("Value1");
        IntegerVariable max2 = new IntegerVariable("Value2");
        IntegerVariable resultMax = new IntegerVariable("result Max");
        ConditionalExpression condition5 = new ConditionalExpression(max1, max2, ConditionalOperator.Greater);
        Expression block3 = new IfExpression(condition5, new AssignExpression(resultMax, max1), new AssignExpression(resultMax, max2));
        FunctionExpression maxFunction = new FunctionExpression("Max", new ArrayExpression(max1, max2), resultMax, block3);
        PrintExpression.print(new StringLiteral(maxFunction.toString()));
        maxFunction.execute(new IntegerLiteral(7), new IntegerLiteral(17));
        PrintExpression.print(new StringLiteral(maxFunction.outStr()));

        //Add function
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable add1 = new IntegerVariable("Value1");
        IntegerVariable add2 = new IntegerVariable("Value2");
        IntegerVariable resultAdd = new IntegerVariable("resultAdd");
        Expression assignBlock = new AssignExpression(resultAdd, new AddExpression(add1, add2));
        FunctionExpression addFunction = new FunctionExpression("Add", new ArrayExpression(add1, add2), resultAdd, assignBlock);
        PrintExpression.print(new StringLiteral(addFunction.toString()));
        addFunction.execute(new IntegerLiteral(15), new IntegerLiteral(27));
        PrintExpression.print(new StringLiteral(addFunction.outStr()));
        addFunction.execute(new IntegerLiteral(1235), new IntegerLiteral(26547));
        PrintExpression.print(new StringLiteral(addFunction.outStr()));

        //Sum of all integer numbers using recursive function
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable inputOfSumFunc = new IntegerVariable("Input");
        IntegerVariable sumResult = new IntegerVariable("Result");
        ConditionalExpression breakCondition = new ConditionalExpression(inputOfSumFunc, new IntegerLiteral(0), ConditionalOperator.Equal);
        Expression sumBlock1 = new AssignExpression(sumResult, new AddExpression(inputOfSumFunc, sumResult));
        Expression sumBlock2 = new AssignExpression(inputOfSumFunc, new SubtractExpression(inputOfSumFunc, new IntegerLiteral(1)));
        
        RecursiveFunctionExpression sumOfFunction = new RecursiveFunctionExpression("sum", new ArrayExpression(inputOfSumFunc), sumResult, breakCondition, null, sumBlock1, sumBlock2);
        PrintExpression.print(new StringLiteral(sumOfFunction.toString()));
        sumOfFunction.execute(new IntegerLiteral(5));
        PrintExpression.print(new StringLiteral("sumOf(5) = " + sumResult.execute()));
        sumOfFunction.execute(new IntegerLiteral(6));
        PrintExpression.print(new StringLiteral("sumOf(6) = " + sumResult.execute()));

        //Fibonacci with FunctionExpression
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable numToIterate = new IntegerVariable("inp");
        IntegerVariable fibonacciNum1 = new IntegerVariable("num1");
        IntegerVariable fibonacciNum2 = new IntegerVariable("num2", 1);
        Expression print = new PrintExpression(fibonacciNum1);
        AssignExpression firstAssign = new AssignExpression(fibonacciNum2, new AddExpression(fibonacciNum2, fibonacciNum1));
        AssignExpression secondAssign = new AssignExpression(fibonacciNum1, new SubtractExpression(fibonacciNum2, fibonacciNum1));
        AssignExpression decrementNum = new AssignExpression(numToIterate, new SubtractExpression(numToIterate, new IntegerLiteral(1)));
        ConditionalExpression condToStop = new ConditionalExpression(numToIterate, new IntegerLiteral(0), ConditionalOperator.Equal);
        
        FunctionExpression fibonacci = new RecursiveFunctionExpression("Fibonacci",
                new ArrayExpression(numToIterate, fibonacciNum1, fibonacciNum2), fibonacciNum1,
                condToStop, null,
                print,
                firstAssign,
                secondAssign,
                decrementNum
        );
        
        PrintExpression.print(new StringLiteral(fibonacci.toString()));
        
        fibonacci.execute(new IntegerLiteral(8));
        PrintExpression.print(new StringLiteral(fibonacci.outStr()));
        System.out.println("");
        fibonacci.execute(new IntegerLiteral(10));
        PrintExpression.print(new StringLiteral(fibonacci.outStr()));
        PrintExpression.print(new StringLiteral(""));
        PrintExpression.print(new StringLiteral(""));

        //Average of array
        PrintExpression.print(new StringLiteral("--------------"));
        ArrayExpression averageInput = new ArrayExpression("array");
        averageInput.add(new IntegerLiteral(11));
        averageInput.add(new IntegerLiteral(21));
        averageInput.add(new IntegerLiteral(31));
        averageInput.add(new IntegerLiteral(41));
        averageInput.add(new IntegerLiteral(51));
        averageInput.add(new IntegerLiteral(110));
        
        ArrayExpression averageInput2 = new ArrayExpression("array2");
        averageInput2.add(new IntegerLiteral(1));
        averageInput2.add(new IntegerLiteral(2));
        averageInput2.add(new IntegerLiteral(3));
        averageInput2.add(new IntegerLiteral(4));
        averageInput2.add(new IntegerLiteral(5));
        
        ArrayExpression averageInput3 = new ArrayExpression("array3");
        averageInput3.add(new IntegerLiteral(5));
        averageInput3.add(new IntegerLiteral(6));
        averageInput3.add(new IntegerLiteral(7));
        averageInput3.add(new IntegerLiteral(8));
        averageInput3.add(new IntegerLiteral(9));
        averageInput3.add(new IntegerLiteral(54));
        averageInput3.add(new IntegerLiteral(213));
        averageInput3.add(new IntegerLiteral(43));
        averageInput3.add(new IntegerLiteral(5435));
        averageInput3.add(new IntegerLiteral(123));
        
        IntegerVariable averageIndex = new IntegerVariable("i");
        DoubleVariable averageSum = new DoubleVariable("sum");
        DoubleVariable averageResult = new DoubleVariable("avgRes");
        Expression getArraySize = averageInput.arrayExpressionSizeGetter();
        Expression getArrayValue = averageInput.arrayExpressionValueGetter(averageIndex);
        AssignExpression avgAssign = new AssignExpression(averageSum, new AddExpression(averageSum, getArrayValue));
        LoopExpression averageLoop = new LoopExpression(averageIndex, new ConditionalExpression(averageIndex, getArraySize, ConditionalOperator.Less),
                new AddExpression(averageIndex, new IntegerLiteral(1)),
                avgAssign);
        FunctionExpression average = new FunctionExpression("avg", new ArrayExpression(averageInput), averageResult, averageLoop,
                new AssignExpression(averageResult, new DivisionExpression(averageSum, getArraySize)));
        PrintExpression.print(new StringLiteral(average.toString()));
        average.execute(averageInput2);
        PrintExpression.print(new StringLiteral(average.outStr()));
        averageIndex.assign(new IntegerLiteral(0));
        averageSum.assign(new IntegerLiteral(0));
        average.execute(averageInput3);
        PrintExpression.print(new StringLiteral(average.outStr()));

        //GCD
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable gcdNum1 = new IntegerVariable("num1");
        IntegerVariable gcdNum2 = new IntegerVariable("num2");
        IntegerVariable gcdTemp = new IntegerVariable("temp");
        ConditionalExpression gcdCond = new ConditionalExpression(gcdNum2, new IntegerLiteral(0), ConditionalOperator.Equal);
        AssignExpression gcdAssign1 = new AssignExpression(gcdTemp, gcdNum1);
        AssignExpression gcdAssign2 = new AssignExpression(gcdNum1, gcdNum2);
        ModuloExpression modulo = new ModuloExpression(gcdTemp, gcdNum2);
        AssignExpression gcdAssign3 = new AssignExpression(gcdNum2, modulo);
        FunctionExpression gcd = new RecursiveFunctionExpression("gcd",
                new ArrayExpression(gcdNum1, gcdNum2, gcdTemp), gcdNum1, gcdCond, null,
                gcdAssign1,
                gcdAssign2,
                gcdAssign3
        );
        
        PrintExpression.print(new StringLiteral(gcd.toString()));
        gcd.execute(new IntegerLiteral(24), new IntegerLiteral(12));
        PrintExpression.print(new StringLiteral(gcd.outStr()));
        gcd.execute(new IntegerLiteral(23), new IntegerLiteral(12));
        PrintExpression.print(new StringLiteral(gcd.outStr()));

        //Weighted average
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable weightIndex = new IntegerVariable("i");
        ArrayExpression weightArrayInput = new ArrayExpression("ArrayInput");
        ArrayExpression weights = new ArrayExpression("Weights");
        Expression weightedArraySizeGetter = weightArrayInput.arrayExpressionSizeGetter();
        Expression weightedArrayGetter = weightArrayInput.arrayExpressionValueGetter(weightIndex);
        Expression weigthGetter = weights.arrayExpressionValueGetter(weightIndex);
        DoubleVariable weightedSum = new DoubleVariable("sum");
        AssignExpression assignWeightedSum = new AssignExpression(weightedSum, new AddExpression(weightedSum, new MultiplyExpression(weightedArrayGetter, weigthGetter)));
        DoubleVariable weightedAverageResult = new DoubleVariable("Result");
        Expression lastExp = new AssignExpression(weightedAverageResult, new DivisionExpression(weightedSum, weightedArraySizeGetter));
        ConditionalExpression condToStopAverage = new ConditionalExpression(weightIndex, weightedArraySizeGetter, ConditionalOperator.Equal);
        AssignExpression incrementIndex = new AssignExpression(weightIndex, new AddExpression(weightIndex, new IntegerLiteral(1)));
        FunctionExpression weightedArray = new RecursiveFunctionExpression("weightedAverage", new ArrayExpression(weightArrayInput, weights), weightedAverageResult, condToStopAverage, lastExp,
                assignWeightedSum,
                incrementIndex
        );
        
        PrintExpression.print(new StringLiteral(weightedArray.toString()));
        
        ArrayExpression weightsExample = new ArrayExpression(
                new DoubleLiteral(2),
                new DoubleLiteral(1)
        );
        ArrayExpression weightsExampleInput = new ArrayExpression(
                new DoubleLiteral(5),
                new DoubleLiteral(10)
        );
        weightedArray.execute(weightsExampleInput, weightsExample);
        PrintExpression.print(new StringLiteral(weightedArray.outStr()));

        //Power of numbers
        PrintExpression.print(new StringLiteral("--------------"));
        DoubleVariable powInput = new DoubleVariable("num");
        DoubleVariable powCount = new DoubleVariable("pow");
        DoubleVariable powResult = new DoubleVariable("res", 1);
        ConditionalExpression condToStopPow = new ConditionalExpression(powCount, new IntegerLiteral(0), ConditionalOperator.Equal);
        AssignExpression assignPow = new AssignExpression(powResult, new MultiplyExpression(powResult, powInput));
        AssignExpression decrementPow = new AssignExpression(powCount, new SubtractExpression(powCount, new IntegerLiteral(1)));
        
        FunctionExpression pow = new RecursiveFunctionExpression("pow", new ArrayExpression(powInput, powCount), powResult, condToStopPow, null,
                assignPow,
                decrementPow
        );
        
        PrintExpression.print(new StringLiteral(pow.toString()));
        pow.execute(new IntegerLiteral(2), new IntegerLiteral(5));
        PrintExpression.print(new StringLiteral(pow.outStr()));

        //Armstrong numbers
        PrintExpression.print(new StringLiteral("--------------"));
        IntegerVariable armstrongInput = new IntegerVariable("num");
        IntegerVariable armstrongPow = new IntegerVariable("pow");
        IntegerVariable armstrongResult = new IntegerVariable("res");
        IntegerVariable armstrongDigit = new IntegerVariable("digitOfNumber");
        
        FunctionAssign testFunc = new FunctionAssign(pow, new ArrayExpression(armstrongDigit, armstrongPow));
        
        ConditionalExpression condToStopArmstrong = new ConditionalExpression(armstrongInput, new IntegerLiteral(0), ConditionalOperator.Equal);
        AssignExpression assignDigit = new AssignExpression(armstrongDigit, new ModuloExpression(armstrongInput, new IntegerLiteral(10)));
        AssignExpression assignInput = new AssignExpression(armstrongInput, new DivisionExpression(armstrongInput, new IntegerLiteral(10)));
        AssignExpression assignResult = new AssignExpression(armstrongResult, new AddExpression(armstrongResult,
                testFunc));
        
        FunctionExpression armstrong = new RecursiveFunctionExpression("armstrongNumbers", new ArrayExpression(armstrongInput, armstrongPow), armstrongResult,
                condToStopArmstrong, null,
                assignDigit,
                assignResult,
                assignInput
        );
        IntegerVariable isArmstrongNumber1 = new IntegerVariable("NumToCheck");
        IntegerVariable isArmstrongNumber2 = new IntegerVariable("NumToCheckDigitNum");
        BooleanVariable isArmstrongBoolean = new BooleanVariable("returnBoolean");
        IntegerVariable temp = new IntegerVariable("temp");
        
        FunctionAssign armstrongAssign = new FunctionAssign(armstrong, new ArrayExpression(isArmstrongNumber1, isArmstrongNumber2));
        AssignExpression assignArmstrongInput = new AssignExpression(temp, armstrongAssign);
        ConditionalExpression isArmstrongCondition = new ConditionalExpression(isArmstrongNumber1, temp, ConditionalOperator.Equal);
        
        AssignExpression assignBoolean = new AssignExpression(isArmstrongBoolean, isArmstrongCondition);
        
        FunctionExpression isArmstorngNumber = new FunctionExpression("isArmstrong", new ArrayExpression(isArmstrongNumber1, isArmstrongNumber2),
                isArmstrongBoolean,
                assignArmstrongInput,
                assignBoolean
        );
        
        PrintExpression.print(new StringLiteral(armstrong.toString()));
        PrintExpression.print();
        isArmstorngNumber.execute(new IntegerLiteral(135), new IntegerLiteral(3));
        PrintExpression.print(new StringLiteral(isArmstorngNumber.toString()));
        PrintExpression.print(new StringLiteral(isArmstorngNumber.outStr()));
        isArmstorngNumber.execute(new IntegerLiteral(1634), new IntegerLiteral(4));
        PrintExpression.print(new StringLiteral(isArmstorngNumber.outStr()));

        PrintExpression.print(new StringLiteral(""));
        PrintExpression.print(new StringLiteral(""));
        
        
    }
    
    static void arrayTest() {
        
        PrintExpression.print(new StringLiteral("Array test"));
        PrintExpression.print();
        ArrayExpression array = new ArrayExpression("arr1");
        
        ConditionalExpression conditional = new ConditionalExpression(new AddExpression(new IntegerLiteral(3), new IntegerLiteral(5)),
                new SubtractExpression(new DoubleLiteral(10.0), new DoubleLiteral(2.0)), ConditionalOperator.Equal);
        array.add(new IntegerLiteral(1));
        array.add(new DoubleLiteral(2.0));
        array.add(new BooleanLiteral(true));
        array.add(conditional);
        array.add(new IfExpression(conditional, new PrintExpression(new StringLiteral("truee")), new PrintExpression(new StringLiteral("falseee"))));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        array.remove(new DoubleLiteral(2.0));
        
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        array.remove(new DoubleLiteral(2.0));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        array.remove(new IntegerLiteral(0));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        PrintExpression.print(array.size());
        array.add(array);
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        PrintExpression.print(array.size());
        PrintExpression.print(array.get(new IntegerLiteral(0)));
        //PrintExpression.print(array.get(new BooleanLiteral(true)));
        PrintExpression.print(array.get(new DoubleLiteral(1)));
        PrintExpression.print(array.get(new DoubleLiteral(2.5)));
        array.set(new IntegerLiteral(2), new StringLiteral("asd"));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        /*array.set(new BooleanLiteral(true), new StringLiteral("asd"));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));*/
        ArrayExpression arr2 = new ArrayExpression(array);
        ArrayExpression arr3 = new ArrayExpression("arr3");
        arr3.assign(array);
        array.assign(new IntegerLiteral(5));
        PrintExpression.print(new StringLiteral(array.elementsAsString()));
        PrintExpression.print(new StringLiteral(arr2.elementsAsString()));
        PrintExpression.print(new StringLiteral(((ArrayExpression)arr2.get(new IntegerLiteral(0))).elementsAsString()));
        PrintExpression.print(new StringLiteral(arr3.elementsAsString()));
        arr2.add(arr3);
        PrintExpression.print(new StringLiteral(((ArrayExpression)arr2.get(new IntegerLiteral(1))).elementsAsString()));
    }
    
}
