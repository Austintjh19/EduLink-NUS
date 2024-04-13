---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# EduLink-NUS Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

<box type="info" seamless>
Please note that certain aspects, such as UML classes, may have been <b>simplified</b> to fit within the diagram's constraints and <b>maintain readability</b>.
</box>


### Add feature

This feature enables users to seamlessly integrate new student profiles into the EduLink-NUS application. To ensure data integrity and completeness, the system necessitates the inclusion of essential parameters such as Name, Student ID, Phone Number, Email, Address, Intake, and Major. Additionally, users have the option to include tags.
The activity diagram below shows the sequence of action users will have to take to add a new Student Profile into the EduLink-NUS application.

<puml src="diagrams/add/AddActivityDiagram.puml" alt="Activity Diagram - Add"/>

#### Implementation - Sequence Diagrams:

The below class diagram represents the key classes and their relationships involved in the implementation of the Add feature in the EduLink-NUS application.

<puml src="diagrams/add/AddClassDiagram.puml" alt="Class Diagram - Add"/>

Some additional information:
* ParserUtil Class: Helper classes used by the AddCommandParser for parsing and validation tasks.
  * E.g. Automatic removal of additional whitespaces in user inputs. E.g. `John        Doe ` will be parsed as `John Doe`.
* ArgumentMultimap Class: ArgumentMultimap helps in mapping command arguments, while ParserUtil provides utility methods for parsing different types of data.
* AddCommand Class: Represents the command to add a new student to the application. Upon execution, it produces a CommandResult. It initializes and adds instances of the Student class.

#### Implementation - Design Considerations:

Automatic Removal of Additional Whitespaces reasoning:
Storing extra whitespaces doesn't add any meaningful information and only introduces unnecessary complexity. By automatically removing these additional whitespaces, the system ensures that data is stored in a clean and consistent format, without sacrificing any essential information.

Creating a new ParserUtil for Data Validation:
* Alternative 1 (Current Implementation):
    * Description: he current implementation separates data validation into a dedicated ParserUtil class, providing a centralized location for validation functions.
    * Pros: Promotes code modularity and maintainability by isolating validation logic from other components, facilitating easier updates and modifications.
    * Cons: Introduces an additional layer of abstraction, potentially increasing complexity.
* Alternative 2:
  * Description: Incorporate validation functions directly within each relevant class, such as Student, Name, Email, etc., eliminating the need for a separate ParserUtil class.
  * Pros: Provides more context-specific validation, allowing each class to enforce its own constraints and behaviors tailored to its purpose.
  * Cons May result in code duplication if similar validation logic is required across multiple classes, leading to potential maintenance challenges.

  We chose Alternative 1 for its centralized validation logic in ParserUtil, promoting code modularity, consistency, and easier maintenance. This approach ensures uniformity across validation rules and We chose Alternative 1 due to the nature of our parameters; name, address, and major share similar validation requirements.
Centralized validation in ParserUtil ensures uniformity, simplifying maintenance and testing across classes, promoting code modularity, and enhancing consistency.


### Find feature

This find feature enables the search for students in the EduLink-NUS application based on their Names, Student IDs, or Both.
The search specification will vary depending on the search parameter. i.e. using Names, Student IDs, or Both. Below is a brief summary:
* Searching by Name - Single Word:
  * Partial word matching is supported when searching by a single word, but matches must commence from the first letter.
* Searching by Name - Multiple Words:
  * Only Student Names which contains the same chronological combination and ordering of those search words will be returned.
  * Specific location of the match is disregarded.
  Only the last search word will allow for partial word matching, but matches must commence from the first letter.
* Searching by ID:
  * Partial word matching is supported and matches need not commence from the first letter.
* Searching by both ID & Name:
  * Only entries with IDs and names that match both criteria will be returned.
  * The constraints for matches, both for Name and ID, are applied the same as when searching by Name and ID individually.

#### Proposed Implementation - Class Diagram:

Below is a representative class diagram of the feature. The implementation of this feature involved the creation of three new classes, those being IdAndNameContainsQueryIdAndNamePredicate, IdContainsQueryIdPredicate, and NameContainsQueryNamePredicate.
Each class is designed to address specific aspects of the search specifications outlined in the description. Essentially, they serve to encapsulate and modularize the logic for finding students based on different search criteria.

<puml src="diagrams/find/FindClassDiagram.puml" alt="UML Class Diagram - Find"/>

#### Proposed Implementation - Sequence Diagrams:

In the sequence diagram provided below, the interaction among various classes forming the foundation of the find feature is illustrated. The sequence is initiated when the user enters the command "find n/John D id/A123" into the command box, triggering the `execute("find n/John D id/A123")` method call in the LogicManager.

<puml src="diagrams/find/FindSequenceDiagram.puml" alt="UML Sequence Diagram - Find"/>

The sequence diagram above reveals that the `FindCommand` constructor requires a `Predicate` argument. The determination of which specific predicate to pass — `IdAndNameContainsQueryIdAndNamePredicate`, `IdContainsQueryIdPredicate`, or `NameContainsQueryNamePredicate` — is elucidated below:

1. Parsing and Determination by FindCommandParser:
    - The `FindCommandParser` is responsible for parsing the command string and extracting relevant search criteria.
    - Based on the parsed criteria, such as name and ID prefixes in the command string, the `FindCommandParser` determines the appropriate predicate to use for the search.

2. Predicate Selection Criteria:
    - If the command includes both name and ID criteria (`n/John` and `id/A123`), the `FindCommandParser` selects `IdAndNameContainsQueryIdAndNamePredicate`.
    - If only the ID criterion is present (`id/A123`), the `FindCommandParser` selects `IdContainsQueryIdPredicate`.
    - If only the name criterion is present (`n/John`), the `FindCommandParser` selects `NameContainsQueryNamePredicate`.

3. Passing Predicate to FindCommand:
    - Once the appropriate predicate is determined, the `FindCommandParser` instantiates a `FindCommand` object, passing the selected predicate as an argument to its constructor.
    - This ensures that the `FindCommand` is equipped with the correct predicate for executing the search operation effectively.

The below sequence diagram highlights this process:

<puml src="diagrams/find/FindParserSequenceDiagram.puml" alt="UML Sequence Diagram - Find Parser"/>

#### Proposed Implementation - Design Considerations:

Design of Predicate:
* Alternative 1 (Current Implementation):
  * Description: Each search criteria (e.g., ID, Name) has its own dedicated predicate class (e.g., IdContainsQueryIdPredicate, NameContainsQueryNamePredicate).
  * Pros: Encapsulates the logic for each search criterion in separate classes, ensuring modularity and maintainability.
  * Cons: Requires creating a significant number of predicate classes, potentially leading to codebase complexity.
* Alternative 2: 
  * Description: Create a single, more generalized predicate class capable of handling multiple search criteria.
  * Pros:  Reduces the number of classes needed, simplifying the codebase.
  * Cons: Combining multiple search criteria into a single class may reduce modularity, making it harder to isolate and maintain specific functionality.

Design of Matching Name Criteria Reasoning:
* Partial word matching supported but must commence from fist letter:
  * Requiring matches to start from the first letter ensures that search results are precise and relevant. This prevents unrelated or unintended matches that might occur if partial matches were allowed to begin from any position within the name.
* Sequential Combination Matching: Requiring the names to contain the same chronological combination and ordering of the keywords ensures precise matches.

Design of Matching ID Criteria Reasoning:
* Partial matching for ID:
  * Allowing partial word matching for IDs enhances the flexibility of the search functionality. Users can search for IDs even if they don't remember the complete sequence, making it easier to find specific students.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Professors and Teaching Assistants at the National University of Singapore seeking a streamlined platform to manage past and current students.
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
* EduLink NUS offers National University of Singapore professors and teaching assistants a centralised platform for effortless connection and search capabilities regarding past and current students. By streamlining communication and data retrieval, it enhances efficiency in academic engagement and administrative tasks.
* manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a…    | I can…                                                                                                             | So that I can…                                                                                |
|----------|----------|--------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `**`     | new user | access a help guide that provides detailed instructions                                                            | effectively utilize the platform's features and functionalities                               |
| `*`      | user     | export student data to a .csv, .json or pdf file                                                                   | perform analytics work, such as data analysis and statistical modeling                        |
| `**`     | user     | import student data from .txt, .csv and .json file                                                                 | eliminate the need to input every piece of information individually                           |
| `***`    | user     | view a list of student                                                                                             | Easily reach out to them for academic support or research opportunities                       |
| `***`    | user     | add new student information to the system (Grade, Cohort, Module, Contact Information)                             | keep the database up-to-date with the latest student records                                  |
| `***`    | user     | delete a student information from the system when necessary                                                        | ensure outdated or incorrect records are removed efficiently and accurately                   |
| `***`    | user     | edit the information of a student in the system                                                                    | update their details accurately as needed                                                     |
| `**`     | user     | sort student data by various criteria such as grade and cohort                                                     | organize student information efficiently and make informed decisions                          |
| `**`     | user     | filter data based on specific criteria                                                                             | tailor information retrieval to meet diverse academic and administrative needs                |
| `***`    | user     | search for students by their name or Student ID                                                                    | quickly locate specific individuals within the system                                         |
| `***`    | user     | add tags to students and classify them based on various criteria such as “Potential Teaching Assistant”            | easily identify and group students based on specific attributes or characteristics            |
| `*`      | user     | check the number of students in the database based on specific criteria, such as those belonging to a specific tag | assess the scope and distribution of students across various criteria                         |
| `*`      | user     | add notes or comments to a student's profile                                                                       | maintain a comprehensive record of student achievements, and challenges                       |
| `*`      | user     | have automatic tagging, e.g. students below a certain grade threshold are tagged with "high priority student"      | save time and resources by automating the identification and classification of students       |
| `***`    | user     | enjoy the benefit of automatic prevention of duplicate entries                                                     | ensure data integrity                                                                         |
| `***`    | user     | retrieve specific information based on tags                                                                        | streamline communication with a huge number of students                                       |
| `*`      | user     | automatically updates student information using the system, e.g. student year group based on current datetime      | ensure data accuracy and reduce manual data editing                                           |
| `***`    | user     | view all contact information for students in an organized and accessible format within the system (Nice GUI)       | look for students easily                                                                      |
| `*`      | user     | filter data by multiple criteria simultaneously within the system                                                  | refine and narrow down the displayed information                                              |
| `*`      | user     | perform bulk deletion of data based on specific criteria within the system                                         | efficiently remove outdated or irrelevant records in large quantities                         |
| `*`      | user     | undo previous actions within the system                                                                            | revert changes or mistakes made, providing a safety net for data integrity                    |
| `*`      | user     | view the history of changes within the system                                                                      | restore previous versions of data or records in case of accidental changes                    |
| `**`     | user     | add group tags to multiple students simultaneously within the system                                               | streamline the process of categorizing and organizing student data                            |
| `***`    | user     | enjoy a user-friendly interface (UI) when interacting with the system                                              | reduce cognitive load                                                                         |
| `***`    | user     | efficiently navigate and interact with the system using typed user commands                                        | access features swiftly, and accomplish tasks with ease                                       |
| `***`    | user     | automatically save my modifications every time I make a change within the system                                   | ensure contacts and information are consistently backed up, preventing any major loss of data |
| `*`      | user     | manage multiple databases within the system                                                                        | organize and segregate data into distinct databases                                           |
| `*`      | user     | view my most recent searches within the system                                                                     | access previously searched items, saving time and effort                                      |
| `*`      | user     | calculate statistical measures for specific data stored within the system                                          | analyze the distribution and central tendencies of the data                                   |
| `*`      | user     | calculate statistical measures for specific filtered data stored within the system                                 | analyze the distribution and central tendencies of the data of certain groups                 |
| `*`      | user     | enjoy autocomplete suggestions for commands or queries as I type                                                   | improve efficiency and accuracy                                                               |


### Use cases

(For all use cases below, the **System** is the `EduLink NUS` and the **Actor** is the `National University of Singapore professors and teaching assistants`, unless specified otherwise)

**Use Case: Add a Tag to a Student's Profile**

**MSS**

1.  User request to list students.
2.  AddressBook shows a list of all students.
3.  User get to know the name or ID of a specific student.
4.  User requests to add tag a specific student by inputting that student's ID and tag information.
5.  The tag is successfully added to that student.

    Use case ends.
**Extensions**
* 2a. The list is empty.

  Use case ends.

* 4a. Invalid student ID entered

    * 4a1. AddressBook inform user that student does not exist
    * 4a2. User enters new student ID and tag information
      Steps 4a1 - 4a2 are repeated till a valid student ID is given

      Use case resumes at step 5

* 4b. Duplicate tag found

    * 4b1. AddressBook inform user that tag already exist for the student specified

      Use case ends

**Use Case: Edit the Information of a Student**

**MSS**

1.  User request to list students.
2.  AddressBook shows a list of all students.
3.  User get to know the name or ID of a specific student.
4.  User prompt to edit a specific student by inputting that student's ID and updated information.
5.  The student's information is successfully changed.

    Use case ends.
**Extensions**
* 2a. The list is empty.

  Use case ends.

* 4a. Invalid student ID entered

    * 4a1. AddressBook inform user that student does not exist
    * 4a2. User enters new student ID and updated information
      Steps 4a1 - 4a2 are repeated till a valid student ID is given

      Use case resumes at step 5.

* 4b. detect error in any updated information entered

    * 4b1. inform user on the information that does not meet requirement
    * 4c1. user enters new updated information (all information)
      Steps 4b1 - 4b2 are repated till the data entered are correct.

      Use case resumes at step 5.

* 4c. updated information is the same as current information

    * 4c1. No changes made to information

      Use case ends.

**Use Case: Delete a Student**

**MSS**

1.  User request to list students.
2.  AddressBook shows a list of all students.
3.  User get to know the name or ID of a specific student.
4.  User prompt to delete that student by its ID.
5.  That student is successfully deleted.

    Use case ends.
**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student ID is invalid.

    * 3a1. AddressBook inform user that student does not exist.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be responsive to user input, as user might have a series of commands to execute.
5.  Should have a resizable UI as the user might work on different programs in parallel.
6.  Should be able to use Offline (without Internet Connection).
### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Student ID**: A unique identifier assigned to each student for precise reference and modification within the system.
* **Tag**: A customizable label or keyword attached to student profiles.
* **Data Integrity**: Maintaining the accuracy, consistency, and lack of corruption within the student data.
* **Commands**: The input typed by the user in the application.
* **Autocomplete**: Functionality that suggests relevant commands or queries as the user types, for greater speed and accuracy.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.


<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
