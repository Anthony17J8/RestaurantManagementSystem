[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e881334d3a3243f9a2be5f4d1e71e0f4)](https://app.codacy.com/app/kiselev.anton.ev/RestaurantManagementSystem?utm_source=github.com&utm_medium=referral&utm_content=Anthony17J8/RestaurantManagementSystem&utm_campaign=Badge_Grade_Dashboard)
[![CircleCI](https://circleci.com/gh/Anthony17J8/RestaurantManagementSystem.svg?style=svg)](https://circleci.com/gh/Anthony17J8/RestaurantManagementSystem)
<p>Design and implement API using Hibernate/Spring/SpringMVC (or Spring-Boot) </p>
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
<li>If it is before 11:00 we asume that he changed his mind.</li>
<li>If it is after 11:00 then it is too late, vote can't be changed</li>
</ul>
</li>
</ul>
<p>Each restaurant provides new menu each day.</p>
<p>As a result, provide a link to github repository.</p>
<hr>