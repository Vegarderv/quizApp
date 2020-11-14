# User stories

This app has five user stories, with some general demands towards the app. It describes the most important
functions and usages we wanted to implement. Every modifications we have implemented in order to make these
scenarios run as smooth as possible.

## As a user I want to be able to take quizzes so I can use the main function of the app (us-1)

The main idea behind this app is that you can take quizzes. Quiz is a popular game around the world, and
we wanted to provide quizzes that users can take. The user has the opportunity to choose between a number 
of quizzes. Each quiz contains three questions, and four answer alternatives per question. After the user
takes a quiz, they get a score. Also to keep the app interesting, the users can also make their own quizzes, 
that will pop up as a choice for all the users.

**Important visibility**

- See all the quizzes that the user can take.
- The questions and alternatives the user can choose from.
- The mean score that the user attained after submitting answers.

**Important functionality**

- The user can choose whichever quiz they want to take.
- There exists a button for submitting answers.
- There exists radiobuttons for choosing an answer.


## As a user I want to log in so I can save my results (us-2)

To save results and scores obtained in the quizzes, the users should be able to log in. The log in function also come in handy when several users want to compare scores. This function allows the app to create 
a list of highscorers on the scoreboard page. The usernames should be unique, and all users need a password.

**Important visibility**

- A page dedicated to log in.
- A menubutton displaying the active user. A user can have several profiles,    this function allows the user to check if they are logged into the correct one. 

**Important functionality**

- Textfields for writing username and password, and a log in Button.
- A menuitem on the menubutton for logging out.
- When the app is run, it always starts at the log in page, regardless if the user logged off or not when they last closed the app.


## As a user I want to be taken to a front page so I can see my options in the app (us-3)

The app consists of various functions that the user can choose from
when logged in. Thus, we decided to include a main page where 
the user can decide between all of the main options displayed. This includes,amongst others, quiz buttons that take the user to a given quiz,  
buttons that take the user to a page where they can make a new quiz, or log off.

**Important visibility**

- A scrollpane that displays the quizzes that the user can take.
- Various methods that the user can choose from. 

**Important functionality**

- Buttons for choosing options
- A scrollpane that adds new quizzes that the user creates. 


## As a user I want to see my own profile so I can see my score in quizzes (us-4)

The user also has a personal profile page. This profile page displays user settings,
shows the mean score of all the quizzes the user has taken and includes a button where the user can switch between darkmode and lightmode. 

**Important visibility**

- Mean score.
- Darkmode button.
- Active user.


**Important functionality**

- Button for taking user to profile page via menubutton.
- Imageview with quiz logo for taking user back to main menu from profile page.

## As a user I want to create my own user so I can log in (us-5)

New users need to be able to create their own profile. At the sign up page the user can create a new username and
password and sign up. The next time the user uses the app, they can log in as usual on the log in page. To 
access the sign up page, there is a button that takes the user to this page from log in page. There is also
a button that takes the user back to the log in page in case the user happens to click on the wrong button.

**Important visibility**

- A page dedicated to sign in.

**Important functionality**

- Button that takes user both to and from sign up page.
- Textfields that the user can fill in with a new username and a password, and abutton to sign up that takes the user to the main page. 