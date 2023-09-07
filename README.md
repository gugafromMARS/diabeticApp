# Diabetic App

![Logo](https://149777215.v2.pressablecdn.com/wp-content/uploads/2017/10/Gustaitis100617-1000x667.jpg)

Welcome to my Rest Api app, this is my solo application focus on diabetic people, im a diabetic type 1 too, so i decide to use this ideia to all the other people like me, this is a simple application for any patient with connection to his diabetic details, dairy register about glucose and insulin units, connected to your doctor, and both patient and doctor have acess to the appointments. At the moment im trying to implement the monitorazing register for week and monthly!


## Architecture 

![ArchitectureImg](https://github.com/gugafromMARS/diabeticApp/assets/116969206/8a873a6b-96c0-418c-9de9-37494f2d34cc)

## Technology

Here are some technologys used on this project.

* Java version 17

## Services

Services used.

* Github
  
## Getting started

1- Run this command on terminal for start a container with mysql
```shell script
docker run --rm -d -p 3306:3306 --name my_mysql -v $(pwd)/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mydiabeticapp mysql:latest
```
2- Create your jar 
```shell script
mvn jar:jar
```
3- Run the application
```shell script
mvn spring-boot:run
```

## App Functionalitys

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

  If you are a patient you can get all your appointments and all your registers, or if you are a doctor you can see all your appointments too.



## Authors

**gugafromMars**

[Github] (https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
