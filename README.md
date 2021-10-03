# VaccNow

### Intro. 
  The solution is simply to organize Vaccination process . We have more than one vaccine spread on serveral branches.
 It provides API to check a specific vaccine locations(Branch) , All branches , Branch Working hours (From , to)
 It provide payment method 'provide Email and payment method, recieve email with a Schedule Vaccination.
 also show all confirmed Vaccination ( from : to Dates) , all branch Vaccinations 
 Check availiablity for Vaccine per branch 

## Tools :
   Java 11, Maven, Spring boot (Web, Data, Mail),DB MySql and H2(for test), Model Mapper (entity Model mapping), Junit test

### Architecture :
  #Entities : represent Tables of DB (ORM)
         Branch : branch fields (work start,end , name ,....)  
         Vaccine : Vaccine fields (manufacture , name ,....)  
         VaccineBranch: VaccineBranch fields (branch , vaccine) 'branch has many Vaccines , Vaccine in several branches (many-to-many)'  
         ScheduleVaccination : ScheduleVaccination fields (emial, payment, vaccTime) apply for Vaccine with (email and payment) got email with a scheduled Vaccination
  #Repositories : each entity has repository handle DB Operations ( All DML )  
  #Service : represent logic of the solution , each service has service which handle a piece of logic  
  #Mapping : layer seperate entities from client model .A model represent client logic which may different from backend logic represented By entities 
  #Controllers : layer expose rest api , provide communicaation between backend and frontend  
  #DB :
    For Tests(Unit, Integrated) I use H2 DB and for Solution I use MySql DB  
    Tables Used: a file named 'schema.sql' contains all DDL.
  #Testing : Junit, Mockito. Mainly all solution logic has tests 

