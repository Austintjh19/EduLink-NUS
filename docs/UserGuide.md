---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# EduLink-NUS User Guide

#

### Welcome to the EduLink-NUS User Guide!

Welcome to the **EduLink NUS User Guide** – your essential companion in unlocking the full potential of academic contacts and data management at the National University of Singapore (NUS).

In this comprehensive guide, we'll take you on a journey to harness the power of EduLink-NUS. But first, we would  like to extend a warm welcome and express our gratitude to you for choosing EduLink-NUS to enhance and improve your academic interactions.

--------------------------------------------------------------------------------------------------------------------
## Table of Contents

#

- [Quick Start](#quick-start)
- [Features](#edulink-nus-features)
    - [Viewing help: `help`](#viewing-help--help)
    - [Adding a student: `add`](#adding-a-student-add)
    - [Editing a student: `edit`](#editing-a-student--edit)
    - [Deleting a student: `delete`](#deleting-a-student--delete)
    - [Listing all students: `list`](#listing-all-students--list)
    - [Viewing a Student on the Student Card](#viewing-a-student-on-the-student-card)
    - [Search students by name or ID: `find`](#search-students-by-name-or-id-find)
    - [Filtering List of Students: `filter`](#filtering-list-of-students--filter)
    - [Adding or Editing Module Grade to a Student: `grade`](#adding-or-editing-module-grade-to-a-student--grade)
    - [Deleting Module Grade to a Student: `dgrade`](#deleting-module-grade-to-a-student--dgrade)
    - [Tagging a student: `tag`](#tagging-a-student--tag)
    - [Editing a student's tag: `etag`](#editing-tags-for-a-student-etag)
    - [Deleting a student's tag: `dtag`](#deleting-a-tag-from-a-student--dtag)
    - [Exporting students data: `export`](#exporting-students-data--export)
    - [Importing students data: `import`](#importing-students-data--import)
    - [Undo Changes: `undo`](#undo-changes-undo)
    - [Clearing all entries: `clear`](#clearing-all-entries--clear)
    - [Exiting the program: `exit`](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------
## Introduction

#

### What is EduLink-NUS

EduLink-NUS serves as a **desktop application tailored specifically for academic instructors** at the National University of Singapore (NUS). It is a powerful tool that can assists in **managing academic contacts and data**. With its purposefully designed Command Line Interface (CLI) and Graphical User Interface (GUI), users have no trouble in integrating it into workflows for efficient academic administration.

Overview of Main Features: 
- Contact Management: Easily add, edit, and delete student entries, ensuring up-to-date information of students is always available.
- Module Grade Tracking: Record and manage module grades for individual students.
- Tagging System: Categorize students with custom tags for streamlined organization and quick retrieval of specific student groups.
- Export and Import Functionality: Seamlessly export student data to CSV files for external use and import data from JSON files for easier management of diverse organizational data. 
- Undo Functionality: Safeguard against accidental changes with the ability to undo the last action.

### User Proficiency and Expectations

- Level of Relatedness: Users of EduLink-NUS are assumed to have a direct relationship with NUS and are actively engaged in academic activities, including teaching, student mentoring, and administrative tasks related to student management.


- Comprehension: Users of EduLink-NUS are assumed to have a strong comprehension of academic terminology and procedures used within NUS. They are familiar with concepts such as student IDs and the module grading system. 


- Prior Knowledge: Users of EduLink-NUS are assumed to possess prior knowledge of computer applications and basic technical skills necessary to navigate software interfaces.


- Desire for Efficiency: Users of EduLink-NUS prioritize efficiency and productivity in their academic administration tasks.

### Why This Guide Matters

This guide is more than just a manual, it is a gateway to maximize your experience with EduLink-NUS. By following the instructions and insights provided here, you'll discover shortcuts, tips, and strategies to streamline your workflow and enhance productivity. Whether you're a seasoned user or just getting started, this guide will equip you with the knowledge and skills to leverage EduLink-NUS effectively.

We're excited to embark on this journey with you and help you make the most out of your academic engagement at NUS with EduLink-NUS. So let's dive in and unleash the full potential of EduLink-NUS together!

--------------------------------------------------------------------------------------------------------------------
## How to use this User Guide

#

This section is designed to help users effectively navigate the User Guide for EduLink-NUS. Below, you'll find information on how to interpret icons, formatting, and instructions provided throughout the document.

### Navigating the Document:
- **Table of Contents:** Located at the beginning of the document, the Table of Contents provides an overview of the topics covered. Users can click on any section title to jump directly to that part of the guide.


- **Section Organization:** Each section is organized with headings and subheadings, breaking down information into manageable chunks. Users can easily locate specific topics or commands by scanning through the contents.

### Sections: 

Below is a detailed overview of the main sections within this User Guide and what can be expected from each section. 

- [Installation](): If you're new to EduLink-NUS or require guidance on installing the application, navigate to this section for step-by-step instructions.


- [Getting Started](): Designed for beginners, this section provides an introductory overview of EduLink-NUS, covering essential information such as the [Layout]() and [Command format]().


- [Commands](): Section of the User Guide that delves into the intricacies of EduLink-NUS commands, it is here where you'll find detailed explanations and practical examples for effective command usage.


- [Command Summary](): For experienced users seeking a quick reference, this section offers a concise summary of all supported commands.


- [FAQ](): Should you encounter any queries or uncertainties while using the application, refer to this section for answers to frequently asked questions.


<box type="info" seamless>
For first-time users of the application, it is highly recommended for you to read through the Getting Started section before utilizing the application.
</box>

### Icons: 

As you navigate through this user guide, you'll encounter colored boxes containing valuable insights. Each box is adorned with an icon, indicating the type of information it holds.

| Icon                                   | Meaning                                                      |
|----------------------------------------|--------------------------------------------------------------|
| <box type="tip" seamless> </box>       | Tips to help you make the most out of EduLink-NUS.           |
| <box type="info" seamless> </box>      | Information you should take note of while using EduLink-NUS.     |
| <box type="warning" seamless>   </box> | Warnings that may corrupt your app and data if not followed. |

--------------------------------------------------------------------------------------------------------------------
## Getting Started

#

### Installation

1. Ensure you have Java `11` installed in your Computer. EduLink-NUS is optimized for this version, and while it may function on other versions, we cannot guarantee smooth operation outside of Java 11.

1. Download the latest `EduLink-NUS.jar` from [here](https://github.com/AY2324S2-CS2103T-T16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your EduLink NUS.

1. Open a command terminal, `cd` into the folder you have placed the jar file in, and enter the `java -jar EduLink-NUS.jar` command to run the application.<br><br>
   A GUI similar to the below should appear in a few seconds. Note that the application will contain some sample data.<br><br>
   ![Ui](images/Ui.png) <br><br>
   <box type="info" seamless> To remove the sample data quickly, you can use the [`clear`]() command.</box> <br><br>

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features]() below for details of all commands supported by EduLink-NUS. Alternatively, refer to the [Command Summary]() for a quick summary. 

### Graphical User Interface Layout:

#### User Interface Overview:

EduLink NUS's user interface can be split into 7 main components, as shown below:

![Ui](images/UiLayout1.png)

The designated names assigned to each component of the User Interface will be consistently utilized throughout the User Guide to mitigate any potential confusion.
Please refer below for a comprehensive description of each component.

* Menu Bar:  Located at the top of the interface, the menu bar provides access to various functions and features within EduLink NUS.
* Command Box: This is the designated space where users can input commands to be executed by EduLink NUS.
* Result Box: Located prominently on the interface, this box showcases the outcomes of executed commands.
* Recent Command Box: This section conveniently presents a history of recently executed commands for quick reference. The ordering in the Recent Command Box goes from left to right, with the most recent executed command appearing on the left.
* Student List Panel: This panel serves as a visual representation of the roster of students stored within the EduLink NUS system.
  * Student Panel Card: Within the Student List Panel, each student is represented by a concise overview encapsulated in a card format.
* Student Card: For more detailed information about an individual student stored in the system, users can refer to the Student Card, providing comprehensive insights.

#### Additional UI Components:

![Ui](images/UiLayout2.png)

* Recent Command: Highlighted in gray, this component stores a previously successfully executed command.
* Index: This component indicates the position of each student in the Student List Panel.
* Student Tag: Highlighted in purple, the Student Tag appears in both the Student Card and Student Panel Card. Each student can have multiple tags associated with them.
* Student Grade: Highlighted in pink, the Student Grade appears in both the Student Card and Student Panel Card, providing information about the academic performance of each student. Like tags, students can have multiple grades associated with them.
* Selected Student Highlight: Highlighted with a purple outline, this component indicates the selected Student Panel Card for viewing in the Student Card.

### How to use EduLink-NUS commands:

EduLink-NUS operates primarily through text-based commands. Before we explore the specific commands in detail in the Commands section, let’s familiarize ourselves with the basic components and format of a command.

#### Parameter Prefixes:

In EduLink-NUS, a parameter prefix acts as a delimiter for specifying different types of parameters in commands. Here's a reference table for common parameter prefixes and their corresponding parameters:

| Parameter Prefix | Corresponding Parameter |
|------------------|-------------------------|
| `id/`            | `STUDENT_ID`            |
| `n/`             | `NAME`                  |
| `p/`             | `PHONE_NUMBER`          |
| `e/`             | `EMAIL`                 |
| `a/`             | `ADDRESS`               |
| `m/`             | `MAJOR`                 |
| `in/`            | `INTAKE`                |
| `t/`             | `TAG`                   |
| `mod/`           | `MODULE`                |
| `s/`             | `MODULE_SCORE`          |
| `f/`             | `FILENAME`              |

#### Parameter:

In EduLink-NUS, a parameter represents a placeholder where users input data. Parameters typically follow immediately after their corresponding Parameter Prefixes. Essentially they are to be supplied by the user.

Each parameter has unique constraints, which restrict what users can input for the parameter. Refer to the table below for details.

<box type="info" seamless> 
Some parameters to not come along with their own Parameter Prefix. E.g. KEYWORD and INDEX. 
</box> 

| Parameter      | Parameter Prefix | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
|----------------|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `STUDENT_ID`   | `id/`            | Specifies the Student ID of a student. <br/><br/> - IDs must contain only alphanumeric characters. <br/> - The ID must start with a letter, followed by exactly 7 digits, and end with a letter.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `NAME`         | `n/`             | Specifies the Name of a student. <br/><br/> - Names must contain only alphanumeric characters and whitespaces. <br/> - The name must start with a letter. <br/> - Names are restricted to a 100 characters long.                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | 
| `PHONE_NUMBER` | `p/`             | Specifies the Phone Number of a student. <br/><br/> - Phone numbers must contain only numerical digits. <br/> - Phone numbers must be at least 3 digits long. <br/> - Phone numbers are restricted to 20 digits.                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | 
| `EMAIL`        | `e/`             | Specifies the Email of a student. <br/><br/> - Emails should be of the format `local-part@domain`. <br/> - local-part should only contain alphanumeric characters and these characters: `+` `_` `.` `-`  <br/> - The local-part must be followed by a '@' and then a domain name. <br/> -  The domain name is made up of domain labels separated by periods. <br/> - The domain name must end with a domain label at least 2 characters long. <br/> - Each domain label must start and end with alphanumeric characters. <br/> -  Each domain label consists of alphanumeric characters, separated only by hyphens, if any. <br/> - Emails are restricted to a 100 characters long. |
| `ADDRESS`      | `a/`             | Specifies the Address of a student. <br/><br/> - Addresses can contain only alphanumeric characters, whitespaces, and the following special characters: `,` `#` `-`.  <br/> - Addresses are restricted to a 100 characters long.                                                                                                                                                                                                                                                                                                                                                                                                                                                    | 
| `MAJOR`        | `m/`             | Specifies the Major of a student. <br/><br/> - Majors must contain only alphabetical characters.  <br/> - Majors are restricted to 50 characters long.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 
| `INTAKE`       | `in/`            | Specifies the Intake of a student. <br/><br/> - Intake should contain Year in the form YYYY. <br/> - Intakes can't be after the current year.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | 
| `TAG`          | `t/`             | Specifies the tag to categorize a student under. <br/><br/> - Tags names should be alphanumeric. <br/> - Tags are restricted to 15 characters long.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | 
| `MODULE`       | `mod/`           | Specifies the module code of an associated grade score for a student. <br/><br/> - Module code should be in the format LLDDDD[L] where L represents a letter and D represents a digit. <br/> - [L] represents optional letter at the end of the code.                                                                                                                                                                                                                                                                                                                                                                                                                               | 
| `MODULE_SCORE` | `s/`             | Specifies the module score for an associated module grade for a student. <br/><br/> - Module score should be a non-negative number between 0 and 100 inclusive.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | 
| `FILENAME`     | `f/`             | Specifies the file to import or export from. <br/><br/> - File names can only contain alphanumeric characters and the special characters: `-` and `_`.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 
| `KEYWORD`      | Not Applicable   | Specifies the keywords to search for when finding students.  <br/><br/> - Can contain alphanumeric characters, spaces, and any special characters, except the special character: `/`.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | 
| `INDEX`        | Not Applicable   | Refers to the index number shown in the Student List Panel.  <br/><br/> -  Must be a positive whole number, e.g. 1, 2, 3. And fall withing the range of 1 to 2,147,483,647.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | 

#### Command Format:

To understand how a full command is interpreted, we will utilise the following example.

**Example:** `add n/NAME id/STUDENT_ID p/PHONE_NUMBER e/EMAIL a/ADDRESS in/INTAKE m/MAJOR [t/TAG]…​`

|             | Component Name    | Meaning                                                        |
|-------------|-------------------|----------------------------------------------------------------|
| **`add`**   | Command           | Will result in the execution of the Add Command by EduLink-NUS |
| **`n/`**    | Parameter Prefix  | Distinguishes `NAME` from other input fields.                  |
| **`NAME`**  | Parameter         | Represents placeholder for name of the student.                |

Certainly, here's a revised version:

You'll notice that `[TAG]` is wrapped in square brackets, indicating that it's an optional component.

- For example, in `n/STUDENT_NAME [TAG]`, you can include a tag like `n/John t/TA` or simply input the student's name as `n/John`.

Moreover, notice that `[TAG]` is followed by an ellipsis (`…`). This signifies that items followed by an ellipsis can be entered multiple times, including zero times.

- As an illustration, `n/STUDENT_NAME [TAG]…` can be used with no tags at all, like `n/John`, or with multiple tags, such as `n/John t/Math t/TA`.


<box type="info" seamless>

**General Notes about EduLink-NUS:**<br>

* Duplicate student entires is defined as 2 person who have identical `STUDENT_ID`.

* Unless explicitly stated otherwise, when indicating character limitations, spaces between words are counted within the total character count, while any leading or trailing spaces are not included in the calculation.

</box>

<box type="info" seamless>

**Notes about the command format:**<br>

* Commands are case-insensitive. e.g `filter` and `Filter` are the same command.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/TA` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/TA`, `t/TA t/Helpful` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* The `INDEX` value must fall within the range of 1 to 2,147,483,647. Any value outside of this range will be deemed incorrect. Moreover, if the value falls within the specified range but is not found in the student list panel, it will be considered out of bounds.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

<box type="warning" seamless>

**VERY IMPORTANT !!!**<br>

* **TAB** key serve a distinct purpose and should now be utilised for creating four whitespaces.

* Any parameter supplied by the user must not contain the **/** character as it serves a distinct purpose in the application.
</box>


--------------------------------------------------------------------------------------------------------------------
## Commands

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

* The format does not require any additional parameters; entering parameters will be disregarded.
* The help message image provided offers clear instructions for users seeking assistance.


### Adding a student: `add`

Adds a Student to the EduLink NUS.

Format: `add n/NAME id/STUDENT_ID p/PHONE_NUMBER e/EMAIL a/ADDRESS in/INTAKE m/MAJOR [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A student can have any number of tags (including 0)
</box>

* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g. A0265901E.
* The `PHONE_NUMBER` must consist of only numeric characters and can be at least 3 digits and at most 15 digits longer. 
* The `INTAKE` must be a 4 digit positive number and can't be after the current year i.e. while the current year is `2024` you can't assign an intake of `2027`.
* The `TAG` must be less than 16 characters long with no space in between.
* The `ADDRESS` must be less than 101 characters long with only following characters allowed :-
    * Lowercase or uppercase letters (a-z, A-Z)
    * Digits (0-9)
    * Whitespace
    * Comma (,)
    * Hash/pound sign (#)
    * Hyphen (-)


Examples:
* `add n/John Doe id/A2265901E p/1234567890 e/john.doe@example.com a/311, Clementi Ave 2, #02-25 in/2023 m/Computer Science t/Honors`

### Editing a student : `edit`

Edits an existing student in the address book, using the Student List Panel index. 

![Index Location](images/Index.png)

Format: `edit INDEX [id/STUDENT_ID] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MAJOR] [in/INTAKE]​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the Student List Panel. The index **must be a positive integer** 1, 2, 3, …​
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g. A0265901E.
* The `PHONE_NUMBER` must consist of only numeric characters and be at least longer than 3 digits.
* The `INTAKE` must be a 4 digit positive number and cant be after the current year.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower in/2020` Edits the name of the 2nd student to be `Betsy Crower` and changes the intake to `2020`.

### Deleting a student : `delete`

Deletes a specified or a group of individual from the EduLink NUS system. Either through the student ID, Student List Panel index, or Student List Panel.

Format: `delete INDEX` **OR** `delete id/STUDENT_ID` **OR** `delete all`

![Delete All](images/deleteAll.png)

* Deletes the student at the specified `INDEX` or deletes the student identified by the specified `STUDENT_ID`.
* The index refers to the index number shown in the Student List Panel.
* The `STUDENT_ID` refers to the unique identification string associated with individuals stored in EduLink NUS.
* The INDEX **must be a positive integer** 1, 2, 3, …​
* The STUDENT_ID **must exist within the system**
* Using the `all` keyword removes all the current students displayed within the Student List Panel from EduLink NUS.

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.
* `delete id/A026273X` deletes the student with Student ID A026273X, even if it is not currently on displayed on the Student List Panel.
* `filter t/TA` followed by `delete all` deletes all students with the `TA` tag. I.e. it removes all students currently displayed on the Student List Panel.

### Listing all students : `list`

Shows a list of all Students in the EduLink NUS.

Format: `list`

* Displays the list of all students stored within EduLink NUS on the Student List Panel.
* No parameters are required for this command, and any parameter added will be ignored.

### Viewing a Student on the Student Card:

View a single student's details in a formatted and organized manner.

Method: Left-click on a specific Student Panel Card within the Student List Panel of the EduLink NUS User Interface.

* The Student Card will display the currently selected student from the Student List Panel.
* The Student Card will display the details of the first student on the Student List Panel, when a specific student is not selected from the Student List Panel.
* The Student Card will update automatically to display the details of the first student on the Student List Panel whenever a command that alters the Student List Panel is executed. e.g. `delete`, `add`, `find`, `filter` ...
* If the Student List Panel is empty or becomes empty due to the execution of a command, the Student Card will display nothing.

### Accessing the Recent Commands in CommandBox:

Access the 5 most Recent Successful Commands in the CommandBox.

![Ui](images/RecentCommands.png)

**Method 1 -  Using GUI**:
Left-Click with Mouse on the desired Recent Command (any one of the command marked with Red Rectangle).

**Method 2 -  Using CLI**:Press `TAB` on your Keyboard to access the RecentCommands in the order shown in the Image above
starting from left to right i.e (Most RecentCommand First)


### Search Students by Name or ID: `find`

This command facilitates the search for students in the EduLink NUS application based on their **Names**, **Student IDs**, or **Both**. Matching students will be displayed on the Student List Panel.

Formats: `find n/NAME`, `find id/STUDENT_ID`, **OR** `find n/NAME id/STUDENT_ID`

* The search is case-insensitive. e.g `john` will match `John`, `a1234567x` will match `A1234567X`
* The search by name supports partial word matching, but must be in chronological order e.g. `John` will match `Jonathan`. And `nathan` will not match with `Jonathan`.
* When searching by name with multiple words, only entries with the same chronological combination of the search words will match. It disregards the specific location of the match. e.g. `Li A` will successfully match `John Li An`.
  Additionally, `Hans Bo` will return `Hans Bober`, `Hans Bober` and not `Hans Mayer`
* The search by ID supports partial word matching and does not need to be in chronological order e.g. `A123` will match `A1234567X`. And `2345` will match with `A1234567X`.
* When searching by both ID and name, only entries with IDs and names that match both criteria will be returned.
 e.g. `Hans Bo` and `A1234` will return entries that has names that includes `Hans Bo` and IDs with `A1234`.

Examples:
* `find n/John` returns `john`, `John Doe`, `Johnathan`
* `find n/alex david` returns `Alex David`, `Alex Davidson`
* `find id/A1234567X` returns a person with ID `A12345678X`
* `find id/A123` returns entries with IDs with `A123`
* `find id/A1234567X n/John Doe` returns a person `John Doe` with ID `A12345678X`
* `find id/234 n/John D` returns a person `Jeff John Doe Leong` with ID `A12345678X`


### Filtering List of Students: `filter`

Filter displayed list of students on the Student List Panel based on a tag or tags.

Format: `filter t/TAG [t/TAG] …​`

* Tag names are case-insensitive. e.g `TA` will match `Ta`
* The order of the tags does not matter. e.g. result for `TA` and `Knowledgeable` will match `Knowledgeable` and `TA`.
* Only full words will be matched, no support for partial word matches. e.g. `TA` will not match `TAPotential`.
* Persons matching all tags listed will be returned. E.g. Person with `TA` tag only will not be returned, if tags
  specified includes `TA` and `Year 2`.

Examples:
* `filter t/CS2103T` will display only people that have been tagged with `CS2103T`.
* `filter t/CS2103T t/TA` wil display only people that have been tagged with `CS2103T` and `TA`.

### Adding or Editing Module Grade to a Student: `grade`

Adds a Module Grade for an individual from the EduLink NUS system, using the Student ID. 

Format: `grade id/STUDENT_ID mod/MODULE_CODE s/SCORE`

* Adds a Module Grade to a student identified by the specified STUDENT_ID.
* The `STUDENT_ID` refers to the alphanumeric string on the left of Name
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g A0265901E
* The `STUDENT_ID` must exist within the system
* The `MODULE_CODE` must be valid i.e. matches [2 letters] followed by [4 digits] then [an optional letter]
* The SCORE must be within 0 to 100. It will round values up to 2 deicimal places when displayed.
* Command will **edit** the grade of a specified module if `MODULE_CODE` already exists. 
* For each execution the Command can only **add** or **edit** a single module grade for one particular student.

Examples:
* `grade id/A0264273X mod/CS2103T s/77` grades the student with Student ID A0264273X for module CS2103T. 77 will be the displayed grade.
* `grade id/A0262743X mod/CS2103T s/85` edits the student with Student ID A0262743X's grade for module CS2103T because a grade for it already exists. 85 will be the displayed grade.


### Deleting Module Grade to a Student: `dgrade`

Deletes a Module Grade for an individual from the EduLink NUS system, using the Student ID.

Format: `dgrade id/STUDENT_ID mod/MODULE_CODE `

* Deletes a Module Grade for a student identified by the specified STUDENT_ID.
* The `STUDENT_ID` refers to the alphanumeric string on the left of Name
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g A0265901E
* The `STUDENT_ID` must exist within the system
* The `MODULE_CODE` must be valid i.e. matches [2 letters] followed by [4 digits] then [an optional letter]. 
* The `MODULE_CODE` must already exist for that particular student.
* For each execution the Command can only **delete** a single module grade entry for one particular student.

Examples:
* `dgrade` id/A026273X mod/CS2103T deletes the grade of the student with Student ID A026273X for module CS2103T.

### Tagging a student : `tag`

Tags the specified student from the address book.

Format: `tag id/STUDENT_ID t/TAG [t/TAG] …​`

* Adds a tag or multiple tags to the student with id `STUDENT_ID`.
* The `STUDENT_ID` refers to the alphanumeric string on the left of Name
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g A0265901E
* Each `TAG` should be alphanumeric, not more than 15 characters long, with no space in between. 
* `Tag` is case-insensitive: TA and ta are the same.
  * If add two same tag, only one will be added.
  * Can't add tag that the student already have.

Examples:
* `tag id/A0257418E t/potentialTA t/Active`

### Editing tags for a Student: `etag`

Edits the tag of a specific student in EduLink NUS

Format: `etag id/STUDENT_ID t/EXISTING_TAG t/RESULTING_TAG`

* Edits the tag of the student with id `STUDENT_ID`.
* The `STUDENT_ID` refers to the alphanumeric string on the left of Name
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g A0265901E
* `EXISTING_TAG` It is the existing tag
that you intend to edit. It is case-insensitive.
* `RESULTING_TAG` is the new tag that will replace the existing tag.
* Both the `EXISTING_TAG` and `RESULTING_TAG` must be not more than 15 characters long with no space in between.
* Both the `EXISTING_TAG` and `RESULTING_TAG` much be alphanumeric, and they are case-insensitive.

Examples:
* `etag id/A0265901E t/Honors t/Scholar` updates the tag from `Honors` to `Scholar` for the student with ID `A0265901E`.

### Deleting a tag from a student : `dtag`

Remove a list of specified tags from a student's profile.

Format: `dtag id/STUDENT_ID t/TAG [t/TAG] …​`

* Deletes tags of the student with id `STUDENT_ID`.
* The `STUDENT_ID` refers to the alphanumeric string on the left of Name
* The `STUDENT_ID` must start with an alphabet followed by 7 digits and ending with an alphabet e.g A0265901E
* Each `TAG` should be alphanumeric, not more than 15 characters long, with no space in between.
* `Tag` is case-insensitive: TA and ta are the same.
  * If add two same tag, only one of them will be added.
  * Can't add tag that the student already have.

Examples:
* `dtag id/A0257418E t/potentialTA t/Active`

### Exporting Students Data : `export`

Exports the students from the address book in a CSV file.

Format: `export f/[FILENAME]`

* Exports the Students Data in a CSV file named `FILENAME.csv` in the **exports** directory i.e `[JAR_FILE_LOCATION]/exports/FILENAME.csv`
* Multiple values within an attribute is separated with a `|` e.g Tags -> `Potenial TA | Honours` in the CSV File.
* `FILENAME` must be alphanumeric and it can include ` _ (Underscore)` and `- (Hyphen)`.

Examples:
* `export f/Mystudents`
* `export f/NUS-CS`

### Importing Students Data : `import`

Imports data from a valid JSON file into the Application

Format: `import f/[FILENAME]`

* Imports Students Data from JSON file named `FILENAME.json` in the **data** directory i.e `[JAR_FILE_LOCATION]/data/FILENAME.json`.
* `FILENAME` must be alphanumeric and it can include ` _ (Underscore)` and `- (Hyphen)`.
* If you try to import an invalid JSON file, it will display an error message.

Examples:
* `import f/Mystudents`
* `import f/NUS-CS`
* `import f/_Stanford`

### Undo Changes: `undo`

Undoes the last command executed and reverts the application to the previous state.

Format: `undo`

* The `undo` command revert the changes done by last **Data changing command** i.e. command that changes (adds, edits or deletes) information for any Student in the Application.
* The application stores up to 20 previous states, allowing you to undo up to the last 20 commands.
* If there are no commands to undo or else you already executed `undo` for 20 commands , an error message will be displayed.

Examples:
* `undo`

### Grading a student : `grade`

Grades the specified individual from the EduLink NUS system for a specified module

Format: `grade id/STUDENT_ID mod/MODULE_CODE s/SCORE`

* Grades the student identified by the specified `STUDENT_ID`.
* The STUDENT_ID refers to the unique identification string associated with individuals stored in EduLink NUS.
* Edit's the student identified by the specified `STUDENT_ID`'s grade if a grade for the specified `MODULE` already exists.
* The STUDENT_ID **must exist within the system**
* The MODULE_CODE **must be valid i.e. matches [2 letters] followed by [4 digits] then [a optional letter]**
* The SCORE **must be within 0 to 100**. 0 stands up **Not Available**.

Examples:
* `grade id/A026273X mod/CS2103T s/77` grades the student with Student ID A026273X for module CS2103T. 77 out of 100 is translated to grade B.
* `grade id/A026273X mod/CS2103T s/85` edits the student with Student ID A026273X's grade for module CS2103T because a grade for it already. 85 out of 100 is now translated to grade A.

### Deleting a grade for a student : `dgrade`

Deletes the grade of the specified individual from the EduLink NUS system for the specified module.

Format: `dgrade id/STUDENT_ID mod/MODULE_CODE`

* Deletes grade of the student identified by the specified `STUDENT_ID` for the specified `MODULE_CODE`.
* The STUDENT_ID refers to the unique identification string associated with individuals stored in EduLink NUS.
* The STUDENT_ID **must exist within the system**
* The MODULE_CODE **must be valid i.e. matches [2 letters] followed by [4 digits] then [a optional letter]**
* The MODULE_CODE **must exist within the student's list of grades**

Examples:
* `dgrade id/A026273X mod/CS2103T` deletes the grade of the student with Student ID A026273X for module CS2103T.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------
## Glossary

Term    | Definition and or Explanation
--------|----------------------------------------------------------------------
**CSV** | **Comma-Seperated Values** , a file format generally used to import data in Spreadsheets and Do analysis
**JSON** | **JavaScript Object Notation**, a standard file format for data interchange
**Data Changing Command** | Commands in the Application that modifies the Students Data e.g Delete, Edit, Tag, etc
**NUS** | National University of Singapore , A University Located in Central Singapore
**Student ID** | A particular format of ID followed in NUS, Starting with a Alphabet followed by 7 digits and ending with an Alphabet e.g A0252195L
**GUI** | Graphical User Interface , all the part of the application which you can interact with your mouse
**CLI** | Command Line Interface , part of application which can only be used with commands from the Keyboard


## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add id/ID n/NAME p/PHONE_NUMBER e/EMAIL  m/MAJOR in/INTAKE a/ADDRESS [t/Tag]…​` <br> e.g., `add id/A0265901E n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 m/Computer Science in/2020 t/Sincere`
**Clear**  | `clear`
**Delete** | `delete INDEX` **OR** `delete id/STUDENT_ID` **OR** `delete all` <br> e.g., `delete 3`, `delete id/A026273X`, `delete all`
**Edit**   | `edit INDEX [id/ID] [m/MAJOR] [in/INTAKE] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` ,`edit 1 id/A0267901E`
**Find**   | `find n/NAME`  **OR**  `find id/STUDENT_ID` **OR** `find n/NAME id/STUDENT_ID` <br> e.g. `find n/John`, `find id/A123`, `find id/234 n/John D`
**Filter** | `filter t/TAG [t/TAG] …​`<br> e.g., `filter t/CS2103T`, `filter t/CS2103T t/TA`
**Add Tag/Tags** | `tag id/ID t/TAG t/TAG` <br> e.g, `tag id/A0257418E t/potentialTA t/Active`
**List**   | `list`
**Undo**   | `undo`
**Export**   | `export f/FILENAME` <br> e.g, `export f/mystudents`
**Import**   | `import f/FILENAME` <br> e.g,`import f/NTU-CS`
**Delete tag/tags**   | `dtag id/STUDENT_ID t/TAG` <br> e.g,`dtag id/A0257418E t/potentialTA`
**Edit tag/tags** | `etag id/STUDENT_ID t/TAG t/RESULTING_TAG` <br> e.g `etag id/A0265901E t/Honors t/Scholar`
**Grade** | `grade id/STUDENT_ID mod/MODULE_CODE s/SCORE` <br> e.g `grade id/A0262733X mod/CS2103T s/77`
**Delete Grade** | `dgrade id/STUDENT_ID mod/MODULE_CODE` <br> e.g `dgrade id/A0262733X mod/CS2103T`
**Help**   | `help`
