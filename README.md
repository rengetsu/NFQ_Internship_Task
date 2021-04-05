# NFQ Internship Task
NFQ Internship Task. Java application created to service the queue of customers to register with specialists. 

## Task

Screens with serial numbers can be seen in customer service departments (bank, outpatient
clinic, post office, etc.). The incoming customer prints out the number and the display shows the
waiting line and the customers place in it. To avoid serial numbers printed on paper, a system is
needed that could allow the customer to book a visit time using a website. After booking the
time of the visit, the customer could see the waiting line and their respected place in it. The
queue is displayed on the service department screens. The customer can see how much time
he has left before the meeting according to the reservation code.

## Project database

  This project works with local MySQL database named *'nfqdb'*.
  It has 2 tables *'login'* and *'visits'*. The first table stores the logins and passwords of specialists to join. The second table stores records of registrations for visits to specialists and their statuses. 

## Used jar files

  * MySQL connector 5.1.46

## Requirements

  * Git
  * Ant 1.9.9 or above
  * JDK 8 or 11 (to build NetBeans)
  * JDK 9 or above (to run NetBeans)
  * MinGW (optional), to build Windows Launchers

## Setup

Building this project requires that Ivy be available to Ant.

All you need is ivy.jar in Ant's classpath (in your `$CLASSPATH`,
`$ANT_HOME/lib`, or `~/.ant/lib`).

 To install run the following commands in a working directory:
 ```
 git clone https://github.com/rengetsu/NFQ_Internship_Task.git
 ```

## Picture of the project structure

![GitHub Logo](https://i.ibb.co/m6YJwWp/Project-Structure.png)
