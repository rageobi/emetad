# Emetad Web application- An online dating system

## Signup page - signup.html
### Frontend and browser validations
- Required fields are filled
- Email has '@' as a character in between alphanumeric string combination
- On-keyup event on password inputs: password and re-enter password fields text match indication
- Entered DOB is lesser than today
- Upload photo file restricted to certain types on upload
- Backend validations - On clicking the submit button
- If the user email exist in the DB and we alert the user in the same page
- If the user email doesn't exist in DB the application user is created and the application proceeds on redirection to the login page of the application for user login

## Login page - login.html
### Frontend and browser validations
- Required fields are filled
- Backend validations - On clicking the submit button
- If email ID doesn't exist in DB there is a redirect to signup page with an alert on top
- If email ID exists in DB the application proceeds on redirection to the main page of the application(main.jsp) by creating a session indication a successful login

## Main page - main.jsp
- Populates user cards with information stored in the database
- When there is a match as an user clicks the "Like User" button, there is a modal shown asking user to enter message to send to the other user
- The application uses JavaMail to send the message as email. The sender wouldn't know the receivers email. However, the receiver would be notified with the senders name and email. They can wish to contact back if interested still.
- The java mail uses gmail smtp with the credential and connection details/properties stored in web.xml
- The applications social contact icon in the footer are present in this page, profile.jsp and search.jsp pages

## Profile page - profile.jsp
- Populates user details from the DB, which could be modified by the user
- The validation works similar to signup page validation

## Search page - search.jsp
- There is a search input field in headers of all the logged in pages, which searches for the string entered and filters out the user lists for display
- This is a search filtered main.jsp view

## Other Application features
- Ability to modify/update profile
- Tried to include classes for html elements from bootstrap which makes the application responsive
- Secure application. Ensured not to use front-end hidden elements for storing private information. All of that is handled in the server end
- Focused a lot on UI/UX aside backend. A small hover, over majority of the elements can give you a hint
- Ajax has been used along with jQuery in main and search pages for sending email if there is a match or to search based on button click
- After 30 mins of inactivity, the user is redirected to login page if any page redirect occurs - This is done by utilising the java session API
- Logout button to kill all the session created attributes and also to "Logout" of the account obviously- 
- Ensured that there are no duplicate entries inserted in both the database tables mentioned below by validating entry through the Utility and Controller java classes
- Aside basic DDL and DML, focused a bit on adding triggers to the Database which will auto-fill columns(Created_date and User_id)
- Implemented a Model 2 Architecture
  - Model - Utility and Javabean Class
  - View - JSP and HTML
  - Controller - Servlets
  
  ## Screenshots(Screengrabbed from reports.html)
 ![](https://i.ibb.co/0tZnZwr/signup-1.png)
 ![](https://i.ibb.co/b3vT1d9/signup-2.png)
 ![](https://i.ibb.co/RPSB4bG/signup-3.png)
 ![](https://i.ibb.co/9tL2M8H/signup-4.png)
 ![](https://i.ibb.co/hWmTWL0/signup-5.png)
 ![](https://i.ibb.co/YWyrZdq/signup-6.png)
 ![](https://i.ibb.co/MCpP2S0/signup-7.png)
 ![](https://i.ibb.co/wgsgf9H/signup-8.png)
    
