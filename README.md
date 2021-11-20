<font size= "5"> **Table Of Contents** </font>
- [Introduction](#introduction)
- [Environment](#environment)
- [Usage](#usage)
  - [Login interface](#login-interface)
  - [Mainscreen interface](#mainscreen-interface)
  - [Interacting with classroom objects](#interacting-with-classroom-objects)
  - [Interacting with student objects](#interacting-with-student-objects)
- [Contributors](#contributors)


# Introduction 
:notebook: This classroom management program will provide basic utilities to help teachers easily manage students in their classes. This project is made for learning purposes. :man_student:  :woman_student:

# Environment
- We run this project on Android Studio IDE.
- Our virtual device that we used to debug the program in android studio is Pixel 5 (api: 26, resolution: 1080 x 2340: 440dpi, Target: Android 8.0, CPU/ABI: x86).
- Our physical device: ... 
# Usage

## Login interface
- Log in with the user's account and password (the trial will use the account 'admin' with the password 'admin').
<p align= "center">
<img src="./images/Login_Screenshot.png" alt="Login Screenshot" style="width:100px;height:200px;" />
</p>

## Mainscreen interface
- After successfully logining, the main interface of the program will appear. Click on the bottom menu (includes notification item, home item, list of classrooms, about item) of the screen and select the classroom management function (the third item from left to right of the bottom menu).
- About item contains the application's intro, the current version number.
<p align= "center">
<img src="./images/ClassList_Screenshot.png" alt="List of classrooms Screenshot" style="width:100px;height:200px;" />
<img src="./images/About_Screenshot.png" alt="About Screenshot" style="width:100px;height:200px;" />
</p>

## Interacting with classroom objects
- If you want to add a new classroom, you will click on the ( + ) image button at the bottom-right, then a adding window to add a new classroom will appear, fill in the classroom's desired information and click "Save" to save the infor.
- If you want to update the class information, just long press on this classroom item in the list view, then an editing window will appear (for this classroom item). Hearin, You can update the classroom's information, delete the classroom by clicking the trash icon at the top-right corner of action bar (a dialog will pop up to confirm deleting).
<p align= "center">
<img src="./images/AddingClass_Screenshot.png" alt="Adding Classroom Screenshot" style="width:100px;height:200px;" />
<img src="./images/UpdatingClass_Screenshot.png" alt="Updating Classroom Screenshot" style="width:100px;height:200px;" />
<img src="./images/DeletingClass_Screenshot.png" alt="Deleting Classroom Screenshot" style="width:100px;height:200px;" />
</p>

## Interacting with student objects
- When clicking on a specific classroom in list view of classrooms, a list of students corresponding to this classroom will appear. The number of available students in the classroom will be displayed on the screen.
<p align= "center">
<img src="./images/StudentList_Screenshot.png" alt="List of Students Screenshot" style="width:100px;height:200px;" />
</p>

- If you want to add a student to the classroom, you will click on the ( + ) at the bottom-right, then a adding window to add a new classroom will appear, fill in the student's desired information and click "Save" to save the infor.
- If you want to update student's information, just long press on this student item in the list view. then an editing window will appear (for this student item). Hearin, You can update or delete the same as above with the classroom's information (a dialog will pop up to confirm deleting).
- Besides, you can also search for the student' name in the list in the class by clicking on the searching icon at the top of action bar.
<p align= "center">
<img src="./images/AddingStudent_Screenshot.png" alt="Adding Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/UpdatingStudent_Screenshot.png" alt="Updating Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/DeletingStudent_Screenshot.png" alt="Deleting Student Screenshot" style="width:100px;height:200px;" />
<img src="./images/SearchingStudent_Screenshot.png" alt="Searching Student Screenshot" style="width:100px;height:200px;" />
</p>

# Contributors
This program was built by *Vu Duc Thai*, *Phan Thanh Tung*, *Vu Trong Toi* under the guidance of *Accociate Prof. Do Trong Tuan* using  java language, combining with some basic techniques of android. 
