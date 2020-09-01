# Math Expression Calculator

This application allows the user to calculate automatically complex Math expressions. The user just need to write the expression or click on the buttons and click in the "=" button to get the result. The calculator allows the basic operators and operatings:
* 0-9 numbers
* Sum (+)
* Minus (-)
* Multiplication (x)
* Division (/)
* Decimal numbers adding (.)
It also allows complex calculates like:
* Operations with parenthesis "()"
* Constants (e,π)
* Pow (^)
* Square root (√)
* Log-10 and Log-e(log,ln)
* Percentage (%)
* Factorial (!)
* Sin, cos and tan operation (sin,cos,tan)

This application can handle commons mistake that user can have writing a calculation, like forgetting a number between, adding more than one time the operator(+-*/) or ending the calculation with a operator with operating after. Besides, it abbreviates some calculation like operating a number with multiple operators at once ("sin(log(10!))"), that can be reduced to ("sinlog10!"). In case of unhandled exception, the result will be the error ocurred and the result will be 0.0.

This application also provides a Restful web service to make the calculations. In order to make the request, you need to go to the url /api/calculator/ writing the operation after the url as a path variable to make the HTTP GET request. The response is a JSON object with the expression received and the result of the expression. For example:
{
    "text": "log100",
    "result": 2.0
}

## Install/Execute

This project was made with Spring boot so the server (Tomcat) is embedded, so it is only needed to clone and execute the application, specifically, the main class that Spring boot provides (CalculatorApplcation.java) located in src/main/java/com/urena/calculator.

## Built with

#### Backend
* Java - Language
* Spring and Spring boot - Framework for Java
* REST - Software Architecture to develop Web services
* JSON - Data format text
#### Frontend
* HTML5,JavaScript and CSS - Languages
* Thymeleaf - XML/HTML5 template
* Boostrap - CSS library


