# standard-crud-sample
Sample Application of standard CRUD using TERASOLUNA Server Framework 5.x (5.0.0.RELEASE)

## Frameworks and Libraries

|Class|Library|Version|
|:---|:---|:---|
|DI Container|Spring Framework|4.1.4.RELEASE|
|MVC Framework|Spring MVC|4.1.4.RELEASE|
|Security Framework|Spring Security|3.2.5.RELESAE|
|Validation Framework|Bean Validation(Hivernate Validator)|1.1(5.1.3.Final)|
|O/R Mapper|MyBatis|3.2.8|
|Bean Mapper|Dozer|5.5.1|
|Template Engine|Java Server Page|-|
|Layout Engine|Tiles|3.0.5|
|Date and Time Operations|Joda Time|2.5|
|Logging|Logback|1.1.2|
|+α|TERASOLUNA Server Framework 5.x common library|5.0.0.RELEASE|
|+α|Lombok|1.14.8|

> **NOTE:**
>
> Please install the lombok into  IED(Eclipse, STS, IDEA ,etc...). For details, refer to [here](http://projectlombok.org/download.html).

## Standard features of this samples
This sample application provide how to implement standard features.

Target features are follows:

#### Domain Layer (Infrastructure Layer)
* Entity CRUD operations
* Transaction management
* Exclusion control (Optimistic Lock control & Long transaction control)

#### Application Layer
* Standard screen flow control (PRG pattern)
* Input validation (Bean Validation & Spring Validator)
* Pagination
* Transaction token check
* Exception handling
* Message management
* Tiles layout
* JSP with JSTL and Spring tag libraries
* File upload (comming soon !!)
* File download (comming soon !!)

#### Security Layer
* Form and DB Authentication
* Logout
* Login form validation using Spring MVC
* URL based Authorization
* Screen item control with Authorization 
* Attack measure (CSRF & Session fixation protection & securty HTTP headers)
* Invalid session detection

## Extended features of this samples

#### Application Layer
* Shared screen flow control (Providing common components in the `com.github.kazuki43zoo.app.flow` package)

## Sample Application Basic Design

to be written later

