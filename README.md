# Diabetic App

![Logo](https://github.com/gugafromMARS/diabeticApp/assets/116969206/c099ee05-d38b-4301-a1d9-2090e839da55)

Welcome to my Rest Api backend app, this is my solo application focus on diabetic people, im a diabetic type 1 too, so i decide to use this ideia to all the other people like me, this is a simple application for any patient with connection to his diabetic details, dairy register about glucose and insulin units, connected to your doctor, and both patient and doctor have acess to the appointments! We have a feature that patient and doctor can access to registers between dates or get the average of insulin and glucose from registers between dates.


## Architecture

![ArchitectureImg](https://github.com/gugafromMARS/diabeticApp/assets/116969206/b715e010-b7e4-4d96-96aa-65f85ff787ed)


## Technology

Here are some technologys used on this project.

* Java version 17

## Services

Services used.

* Github
  
## Getting started

1- Run this command on terminal for start a container with mysql
```shell script
docker run --rm -d -p 3306:3306 --name my_mysql -v $(pwd)/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD={YOUR_PASSWORD} -e MYSQL_DATABASE={YOUR_DATABASE_NAME} mysql:latest
```
2- Create your jar 
```shell script
mvn jar:jar
```
3- Run the application
```shell script
mvn spring-boot:run
```
4- After run it, you have available a Swagger UI

[Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

## App functionalitys

In this app you have several options :

* **Create**

  Create a patient, create a diabetic details from patient, create register from patient, create a doctor and create a appointment from doctor.
* **Delete**

  Delete a patient, doctor, or a register.
* **Update**

  Patient info, doctor info, register info or appointment info too.
* **Register**

  In this api you can register your glucose, and the total of carbohydrates in grams you going to eat, and the app is going to calculate the insulin indice you put on your diabetic details and calculate the total of insulin you need to give!
* **Get**

  If you are a patient you can get all your appointments and all your registers, or if you are a doctor you can see all your appointments too. You can even get your average glucose and insulin from your registers between dates.

## Tests

I made some integration controller tests and service unit tests for all controllers, using :

* Mockito
* Junit5

## Authors

**gugafromMars**

[Github-gugafromMars](https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
