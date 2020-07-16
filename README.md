# Villa Rental Management System - backend.
this is a breif readme file for my Github VillaRentalManagementSystem repository.

## the system provide some main features ,features Api's mentiond below in another section  :
* allow its users to register and login.
* should display all villas and users can apply many transactions .
* allow the customers to Reserve a villa and display his villa online .
* allow to register employees .
* the system supports the online payments.
* the manager can review reports.

# Useful Links.
> first of all u can see the following resources to understand the system in terms of the database and requirements
* [system requirements and UML diagrams](https://drive.google.com/file/d/10wbWBk6YfkZnLqHbsE1Kdzamx9jqt36C/view?usp=sharing)

* [ERD for database](
https://drive.google.com/file/d/1EB41YHtAtr5JNI-vs0je2EU_JnXPjM_5/view?usp=sharing)
* [Mysql database template](https://drive.google.com/drive/folders/18nfINZMMfJXnQVyvCm1-KvG-GJ1v7wL9?usp=sharing)


# Settings , Languages , Libraries , plugins and Techniques used in the project :
1. project written using springboot2 , java jdk14
2. lombok , jpa , junit5 and dto mapstruct used in the project.
3. Mysql and h2 used as a database.
4. swagger and postman can used to  treat with Api's.
5. pagination , validation , projection , dto and Exception handler were applied.
6. the project support loging to console , file and helpful documentation.
7. each entity has a test class which test its Api's using assertion and rest template.
8. Apache Maven used in the project see pom.xml file.

# Steps to Run the Project :
- [1] download the repository. 
- [2] import the project using one of the IDE's eg .IntelliJ.
- [3] download the Mysql database and import it .
- [4] change the database settings as username and passowrd from properties.
- [5] download lombok plugin from settings > plugins > lombok.
- [6] run the project.
- [7] the project runs on a server port 8080 u can change it from the properties.
- [8] using swagger or postman u can use the Api's.
- [9] u can activate h2 database using h2 properties profile insted of mysql.

# Apis
> each entity have a group of Api's that perform an action , see the following example :
### Villa
| Api name  		    | Description 					|    		Input			| Output  	 |
| :---        	    	|    :----:  					|          ---: 			|---:		 |
| findById    			| find a villa bu its id , id must be positive integer      	|  int id                |   	villa object     |
|save|save the villa object|Villa object JSON |void|
|filterVillasByCost|return the villas that their cost less than the input value'double'|cost|List of villas|
|findAll|get all villas from the villa table||List of villas|
|update|update an exist villa object |int id , villa object |void|
|delete|delete a villa from the table |int id |void|
|findVillaByName|returns the villas contains a sub string|string name|Lsit of villas|
|findAllProjectedBy|returns a list of villas with the primary info's||List of projected villas|

### Contact Info
* a7mad.amerr@gmail.com 
*  © 2020 Ahmad Amer