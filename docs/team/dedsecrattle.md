---
  layout: default.md
  title: "Kumar Prabhat's Project Portfolio Page"
---

### Project: EduLink-NUS

EduLink NUS is a desktop application for Educators to keep track of their past Students. While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).
Given below are my contributions to the project.

* **New Feature**: Added the ability to undo previous commands.
  * What it does: allows the user to undo all previous commands one at a time.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement doesn't require any changes for future commands as long as they use the same logic. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to Logic as well UI.

* **New Feature**: Added a Recent commands history that allows the user to navigate to previous commands either using up/down keys or by clicking on the Recent Commands present in the RecentCommand Panel.
  * What is does: allows the user to retrieve the successful recent commands.
  * Justification: This feature improves the product significantly because a user might want to execute almost similar command as previous one , and this feature will help him increase efficiency.
  * Highlights: This feature was not too challenging except the integration of logic with the UI and allowing user to achieve the functionality either CLI or GUI.
* **New Feature**: Added the ability to export the students data in a nicely formatted `.csv` file
  * What it does: allows user to export student's data in CSV file.
  * Justification: Users can easily port their Application data into some other useful softwares such as Excel, Spreadsheet easily using the CSV File.
  * HighLights: This implementation would require some changes based on the changes done in `Model` i.e Student e.g adding a new field for the Student. Complexity wise it was a bit challenging ensuring that feature works well in all the Operating Systems Smoothly which resulted in limit the putting restrictions on the exported Filename.
* **New Feature**: Added the ability to import the students data from a Compatible JSON File.
  * What it does: allows user to import another JSON file apart from the default `addressbook.json`.
  * Justification: User can now separate Groups of Student in different File , allowing them managing multiple databases (Local) sequentially.
  * HighLights: Complexity wise it was a bit challenging as just by changing the current view of students with the imported file doesn't work, need to ensure that any changes made after import are reflected in the imported file instead of the default one.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=dedsecrattle&breakdown=true)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme.
  * Wrote additional tests for existing features.
  * Modified pre-existing feature according to need of application.

* **Documentation**:
  * User Guide:
    * Added documentation for all the above mentioned feature as well general structure of UserGuide.
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Updated the UML Diagrams according to the changes done in the Code.

* **Community**:
  * PRs reviewed (with non-trivial review comments).
  * Contributed to forum discussions.
  * Reported bugs and suggestions for other teams in the class.


