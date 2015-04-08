# standard-crud-sample
Sample Application of standard CRUD using TERASOLUNA Server Framework 5.x (5.0.0.RELEASE)

## Main Frameworks and Libraries

|Class|Library|Version|
|:---|:---|:---|
|DI Container|Spring Framework|4.1.4.RELEASE|
|MVC Framework|Spring MVC|4.1.4.RELEASE|
|Security Framework|Spring Security|3.2.5.RELESAE|
|Validation Framework|Bean Validation(Hivernate Validator)|1.1(5.1.3.Final)|
|O/R Mapper|MyBatis|3.2.8|
|Bean Mapper|Dozer|5.5.1|
|Layout Engine|Tiles|3.0.5|
|Date and Time Operations|Joda Time|2.5|
|Logging|Logback|1.1.2|
|+α|TERASOLUNA Server Framework 5.x common library|5.0.0.RELEASE|

## Features of this samples
This sample application provide how to implement standard features.

Target features are follows:

#### Domain Layer
* Entity CRUD operations
* Transaction management
* Exclusion control (Optimistic Lock control & Long transaction control)

#### Application Layer
* Standard screen flow control (PRG pattern)
* Transaction token check
* Input validation (Bean Validation & Spring Validator)
* Form managment on `HttpSession` (using `@SessionAttributes` annotation)
* Message management
* Pagination
* Exception handling

#### Security Layer
* Authentication (Login & Logout)
* Login form validation (integrating with Spring MVC)
* Authorization (URL based control & screen item control)
* Attack measure (CSRF & Session fixation protection & securty HTTP headers)
* Invalid session detection


## Sample Application Basic Design

to be written later

