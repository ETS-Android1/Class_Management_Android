<font size= "5"> **Table Of Contents** </font>
- [Introduction](#introduction)
- [Environment](#environment)
- [Usage](#usage)
  - [Login interface](#login-interface)
  - [Mainscreen interface](#mainscreen-interface)
    - [Interacting with classroom objects](#interacting-with-classroom-objects)
    - [Interacting with student objects](#interacting-with-student-objects)
  - [Notification interface](#notification-interface)
    - [Viewing classroom schedule](#viewing-classroom-schedule)
    - [Attending students](#attending-students)
  - [Home interface](#home-interface)
- [Contributors](#contributors)


# Introduction 
:notebook: This classroom management program will provide basic utilities to help teachers easily to manage students in their classes. This project is made for learning purposes. :man_student:  :woman_student:

# Environment
- We run this project on Android Studio IDE.
- Our virtual device that we used to debug the program in android studio is Pixel 5 (api: 26, resolution: 1080 x 2340: 440dpi, Target: Android 8.0, CPU/ABI: x86).
- Our physical device: Redme 5 pro
# Usage
## Login interface
- User can login by google account.
- If the user doesn't have an account before, click on "Create New Account" to signup account.
<p align= "center">
<img src="./images/Login_Screenshot.png" alt="Login Screenshot" style="width:100px;height:200px;" />
<img src="./images/Signup_Screenshot.png" alt="Signup Screenshot" style="width:100px;height:200px;" />
</p>

## Mainscreen interface
- After successfully logining, the main interface of the program will appear. Click on the bottom menu (includes notification item, home item, list of classrooms, about item) on the screen and select item connected with list of the unique user's classrooms (the third item from left to right of the bottom menu).
- About item contains the application's intro, the current version number, information of the account, signout button.
<p align= "center">
<img src="./images/ClassList_Screenshot.png" alt="List of classrooms Screenshot" style="width:100px;height:200px;" />
<img src="./images/About_Screenshot.png" alt="About Screenshot" style="width:100px;height:200px;" />
</p>

### Interacting with classroom objects
- If you want to add a new classroom, you will click on the ( + ) image button at the bottom-right, then a adding window to add a new classroom will appear, fill up with classroom's desired information and click "Save" to save the infor.
- If you want to update the class information, just long press on this classroom item in the list view, then an editing window will appear (for this classroom item). Hearin, You can update this classroom's information, delete the classroom by clicking the trash icon at the top-right corner of action bar (a dialog will pop up to confirm deleting).
<p align= "center">
<img src="./images/AddingClass_Screenshot.png" alt="Adding Classroom Screenshot" style="width:100px;height:200px;" />
<img src="./images/UpdatingClass_Screenshot.png" alt="Updating Classroom Screenshot" style="width:100px;height:200px;" />
<img src="./images/DeletingClass_Screenshot.png" alt="Deleting Classroom Screenshot" style="width:100px;height:200px;" />
</p>

### Interacting with student objects
- When clicking on a specific classroom in list view of classrooms, a list of students corresponding to this classroom will appear. The number of available students in the classroom will be displayed on the screen.
<p align= "center">
<img src="./images/StudentList_Screenshot.png" alt="List of Students Screenshot" style="width:100px;height:200px;" />
</p>

- If you want to add a student to the classroom, you will click on the ( + ) at the bottom-right, then a adding window to add a new classroom will appear, fill up with the student's desired information and click "Save" to save the infor.
- If you want to update student's information, just long press on this student item in the list view, then an editing window will appear (for this student item). Hearin, You can update or delete the same as above with the classroom's manual.
- Besides, you can also search for the student' name in the list in the class by clicking on the searching icon at the top of action bar.

<p align= "center">
<img src="./images/AddingStudent_Screenshot.png" alt="Adding Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/UpdatingStudent_Screenshot.png" alt="Updating Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/DeletingStudent_Screenshot.png" alt="Deleting Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/SearchingStudent_Screenshot.png" alt="Searching Student Screenshot" style="width:100px;height:200px;" />
</p>

## Notification interface
- This interface includes two functions: viewing classroom schedule and attendance in everyday, when user long press on a classroom item, an attendance window will appear (respectively attending on a day selected in calander).
### Viewing classroom schedule
<p align= "center">
<img src="./images/Notification1_Screenshot.png" alt="Notification1 Screenshot" style="width:100px;height:200px;" />
<img src="./images/Notification2_Screenshot.png" alt="Notification2 Screenshot" style="width:100px;height:200px;" />
</p>

### Attending students
<p align= "center">
<img src="./images/Attending_Screenshot.png" alt="Attending Screenshot" style="width:100px;height:200px;" />
</p>

## Home interface

<p align= "center">
<img src="./images/Flowers_Falling_Effects.png" alt="Flowers_Falling_Effects" style="width:100px;height:200px;" />
</p>

# Contributors
This program was built by *Vu Duc Thai*, *Phan Thanh Tung*, *Vu Trong Toi* under the guidance of *Accociate Prof. Do Trong Tuan* using  java language, combining with some basic techniques of android. 
