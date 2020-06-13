[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e881334d3a3243f9a2be5f4d1e71e0f4)](https://app.codacy.com/app/kiselev.anton.ev/RestaurantManagementSystem?utm_source=github.com&utm_medium=referral&utm_content=Anthony17J8/RestaurantManagementSystem&utm_campaign=Badge_Grade_Dashboard)
[![CircleCI](https://circleci.com/gh/Anthony17J8/RestaurantManagementSystem.svg?style=svg)](https://circleci.com/gh/Anthony17J8/RestaurantManagementSystem)

#Requirement
<p>Design and implement API using Hibernate/Spring/SpringMVC </p>
<p>The task is:</p>
<p>Build a voting system for deciding where to have lunch.</p>
<ul>
<li>2 types of users: admin and regular users</li>
<li>Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)</li>
<li>Menu changes each day (admins do the updates)</li>
<li>Users can vote on which restaurant they want to have lunch at</li>
<li>Only one vote counted per user</li>
<li>If user votes again the same day:
<ul>
<li>If it is before 11:00 we assume that he changed his mind.</li>
<li>If it is after 11:00 then it is too late, vote can't be changed</li>
</ul>
</li>
</ul>
<p>Each restaurant provides new menu each day.</p>
<p>As a result, provide a link to github repository.</p>

#Run Application
#####1. Setup Tomcat
 * Download and install [Tomcat9](https://tomcat.apache.org/download-90.cgi)
 * Add user with access to deployment an application:   
 File **$CATALINA_HOME/conf/tomcat-users.xml** :
    ```xml
   <tomcat-users>
   ...
    <role rolename="manager-script"/>
    <role rolename="manager-gui"/>
    <user username="admin" password="admin" roles="manager-gui"/>
    <user username="admin1" password="admin1" roles="manager-script"/>
   ...
   </tomcat-users>
    ``` 
* Set up tomcat server   
File **.m2/settings.xml**
    ```xml
    <servers>
      <server>
        <id>tomcat-server</id>
        <username>admin1</username>
        <password>admin1</password>
      </server>
    </servers>
    ```

#####2. Run tomcat
* Run $CATALINA_HOME/bin/catalina.sh run

#####3. Deploy App
* Run command from ROOT of the project:
`` mvn tomcat7:deploy``    
#####4. Check application at the context path: [http://localhost:8080/app](http://localhost:8080/app )