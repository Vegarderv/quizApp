# User stories

This app has five user stories, with some general demands towards the app. It describes the most important
functions and usages we wantes to implement. Every modifications we have implemented in order to make these
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
- There exists a radiobutton for choosing an answer.


## As a user I want to log in so I can save my results (us-2)

To save results and scores obtained in the quizzes, the users should be able to log in. The log in function also come in handy when several users want to compare scores. This function allows the app to create 
a list of highscorers on the scoreboard page. The usernames should be unique, and all users need a password.

**Important visibility**

- A page dedicated to log in.
- A menubutton displaying the active user. A user can have several profiles,    this function allows the user to check if they are logged into the correct one. 

**Important functionality**

- Textfiels for writing usernames and password, and a log in Button
- A button for logging out 
- To start the app with log in page, regardless if the user logged off or not when they last closed the app


## As a user I want to be taken to a front page so I can see my options in the app (us-3)

The app has several options to do when logged in. Therefore we think it is best to have a main page where 
the user has all its main options displayed. This includes amongst several things, which quiz to take to 
make a new quiz, or maybe log off.

**Important visuability**

- Options to choose

**Important functionality**

- Buttons for choosing options


## As a user I want to see my own profile so I can see my score in quizzes (us-4)

The user also has benefits in having a personal page. This profile page would display user settings, and
show the mean score of all the quizzes the user has taken.

**Important visuability**

- Mean score
- Active user

**Important functionality**

- Button for taking user to profile page
- Button for taking user back from profile page

## As a user I want to create my own user so I can log in (us-5)

New users need the chance to create their own profile. At the sign up page the user can create a new username and
password and sign up. The next time the user uses the app, they can just log in as usual at the log in page. To 
access the sign up page, there should be a button that takes the user to this page from log in page. There is also
a button that takes the user back to log in page, if the user happens to click the wrong button.

**Important visuability**

- A page dedicated to log in

**Important functionality**

- Button that takes user both to and from sign up page
- Textfield the user can fill inn new username and password, and button to sign up