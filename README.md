README

Course: cs400
Semester: Spring 2020
Project name: Milk Weights
Team Members:
1. Doran Redlich, dredlich@wisc.edu, Lecture 001, x125
2. Carleigh Heintz, cmheintz@wisc.edu, LEC 001, x125
3. Qi Gao, qgao46@wisc.edu, Lec 001, x125
4. Yansong Tang, ytang229@wisc.edu, Lec 001, x125
 

Which team members were on same xteam together?
All members were part of the same xteam.

Other notes or comments to the grader:

Ordering of FarmID, when comparing the two farmID strings, such as Farm 100 and Farm 3, orders based on the '1' in Farm 100 
and does not take into account the two extra 0's. Therefore, Farm 100 comes before Farm 3 in the output. 

Our program is able to read the input from a single file at a time and display an individual farm report, a monthly report of all farms,
a yearly report of all farms, and a report from an inputted date-range of all farms. 

REPORTS:

For an individual farm report, the displayed data shows each month's total milk weight and the farm's percentage/contribution to the total weight for all farms during that month. 

For a monthly report of all farms, each farm is listed individually for that month with their total milk weights and percentage of the total milk weights across all farms for that month. 

For the yearly report, all farms are listed for each month, with their monthly total milk weights and monthly percentages as well.

Finally, the date range report functions similarly to the monthly report; one report is show for each farm across the given time
period, with their total weights and percentages listed. 

ERRORS:

For any files inputted with unexpected formats or errors, an error message is given for the first line encountered with that error and 
no data is saved within the program. For a given file name that does not exist, an error message stating such is printed 

For any unexpected user inputs, an error message stating that the input is incorrect is given and the user can try another input. 

If the data input is correct but there exits no data for the given FarmID or date range, no data is shown. 

For large data inputs, occassional error messages are thrown, but they do not affect the functioning of the GUI. 
 
