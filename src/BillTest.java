package minifunc;

public class BillTest {

    
    public static void test1() {
        ArrayExpression billList = new ArrayExpression(
                new DoubleLiteral(2.5),
                new DoubleLiteral(3.5),
                new DoubleLiteral(0.75)
        );
        IntegerVariable firstMeal = new IntegerVariable("firstMeal");
        IntegerVariable secondMeal = new IntegerVariable("secondMeal");
        IntegerVariable thirdMeal = new IntegerVariable("thirdMeal");

        PrintExpression.print(new StringLiteral("Enter value for first meal"));
        firstMeal.getInputFromStdIn();
        PrintExpression.print(new StringLiteral("Enter value for second meal"));
        secondMeal.getInputFromStdIn();
        PrintExpression.print(new StringLiteral("Enter value for third meal"));
        thirdMeal.getInputFromStdIn();

        ArrayExpression mealList = new ArrayExpression(
                firstMeal,
                secondMeal,
                thirdMeal
        );

        /*
            func declaration
         */
        IntegerVariable weightIndex = new IntegerVariable("i");
        ArrayExpression weightArrayInput = new ArrayExpression("ArrayInput");
        ArrayExpression weights = new ArrayExpression("Weights");
        Expression weightedArraySizeGetter = weightArrayInput.arrayExpressionSizeGetter();
        Expression weightedArrayGetter = weightArrayInput.arrayExpressionValueGetter(weightIndex);
        Expression weigthGetter = weights.arrayExpressionValueGetter(weightIndex);
        DoubleVariable weightedSum = new DoubleVariable("sum");
        AssignExpression assignWeightedSum = new AssignExpression(weightedSum, 
                new AddExpression(weightedSum, new MultiplyExpression(weightedArrayGetter, weigthGetter)));
        ConditionalExpression condToStopAverage = new ConditionalExpression(weightIndex, weightedArraySizeGetter, ConditionalOperator.Equal);
        AssignExpression incrementIndex = new AssignExpression(weightIndex, new AddExpression(weightIndex, new IntegerLiteral(1)));
        FunctionExpression weightedArray = new RecursiveFunctionExpression("weightedAverage", new ArrayExpression(weightArrayInput, weights), weightedSum, condToStopAverage, null,
                assignWeightedSum,
                incrementIndex
        );


        PrintExpression.print(weightedArray.execute(mealList, billList));

    }

}
