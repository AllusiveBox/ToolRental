# ToolRental

This project was a chance to work with JUnit Tests in an Environment outside of work. Additionally, it was an opportunity to try some different approaches to different issues.

Given that there is no UI or Database, I opted to make each Tool an Instance of Tool, even though Tool only had one variable that it truly cared about. Additionally, due to the set-up of the project, I decided to make the child classes handle the Tool Brand as a Switch Statement, although in a live production environment it might be more beneficial to have each Tool it's own Class by Brand. I used a switch statement instead of an if statement for the same reason as using the child classes to handle the Tool Brand; given the small scale of the project, it seemed more appropriate. 

I left a method in that I was using to Test a few things manually when I got stuck at one point. You can find it in the MyTools.java. It more or less tested the same output as TestAgreementWithJackHammerOverHolidayAndWeekend.

There are three exceptions that are currently thrown at this time. They do not cause the application to crash, but the output that is received is not valid. In all cases an error message is thrown in a friendly way, but the application continues on with the rest of the processing. I opted not to terminate when the errors were thrown, but some additional error handling would be necessary in a live application to prevent garbage data.
