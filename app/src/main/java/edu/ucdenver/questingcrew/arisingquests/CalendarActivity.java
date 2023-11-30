package edu.ucdenver.questingcrew.arisingquests;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.time.Month;
import java.util.Calendar;
import java.util.Objects;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityCalendarBinding;


public class CalendarActivity extends AppCompatActivity {

    private ActivityCalendarBinding binding;
    private int monthCounter;
    private int yearCounter;
    private Calendar calendar;

    private TaskDatabase taskDatabase;

    @Override
    //protected void onCreate(Bundle savedInstanceState) {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.CalendarToolbar.inflateMenu(R.menu.menu_calendar_activity);
        binding.CalendarToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_cancel) {
                    exitTask(view);
                }
                return false;
            }
        });





        //SETTING UP THE CALENDAR IE MAKING THE BUTTONS AND WEEKS FORMAT CORRECTLY

        //6 rows of weeks
        //list just to store the weeks
        LinearLayout[] weeks = new LinearLayout[6];
        //6 rows 7 days each week
        //this is just a list to store the buttons i create later
        Button[] days = new Button[6 * 7];

        //adding all the linear layouts for the weeks to the list
        weeks[0] = binding.weekone;
        weeks[1] = binding.weektwo;
        weeks[2] = binding.weekthree;
        weeks[3] = binding.weekfour;
        weeks[4] = binding.weekfive;
        weeks[5] = binding.weeksix;

        //this is a constructor that tells the height and width for each button
        //currently it is setup for each button to take up the entire space of the linear layour
        //button weight will change that so that each button only takes up 1 ouut of 7 space in the linear layout
        //this is why in the xml file the WEIGHT SUM FOR EACH LINEAR LAYOUT IS 7
        LinearLayout.LayoutParams buttonParameters = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //each button now takes 1/7 of each week
        buttonParameters.weight = 1;


        //This for loop makes the day button objects and adds them to the linear layout and a day list
        int daycount = 0;
        for (int weeknumber = 0; weeknumber < 6; weeknumber++) {
            for (int daynumber = 0; daynumber < 7; daynumber++) {
                //creating a new button in "this" activity
                final Button day = new Button(this);
                //setting color
                day.setTextColor(Color.GRAY);
                day.setBackgroundColor(Color.TRANSPARENT);
                //setting text size may need to change later
                day.setTextSize(30);
                //Using the layout parameter made earlier on the button
                day.setLayoutParams(buttonParameters);
                //now we add the day to the list and of days and to the corresponding linear layout the day is supposed to be on
                //adding day to day list
                days[daycount] = day;
                //adding day to the linear layout
                weeks[weeknumber].addView(day);
                Log.d("Week and day", "added day" + daycount + " to week " + weeknumber);
                //counting the number of days in the whole array
                //This is so that it doesnt overwrite the previous week everytime we add a new day to the list
                //basically just making sure it doesnt go 1,2,3,4,5,6,7 then start over because thats what it does in the loop
                daycount++;
            }

        }

        //getting information from the default android calendar about the current day year...
        calendar = Calendar.getInstance();
        int CurrentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //each one must have a clicked one as well. The clicked int is on the current date when the calendar is started but the user can change that
        int ClickedDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //Months start at 0 ie november = 10
        int CurrentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int ClickedMonth = Calendar.getInstance().get(Calendar.MONTH);

        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        int ClickedYear = Calendar.getInstance().get(Calendar.YEAR);
        //gets days in month by using actualmaximum
        int DaysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);

        //what day is it sunday monday etc..
        int CurrentDayofWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //setting the calednar to the first day of month to retrieve the first day of the week in the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayofMonth = calendar.get(Calendar.DAY_OF_WEEK);


        //returning the calednar to the current day
        calendar = Calendar.getInstance();

        Log.d("CurrentDay", "CurrentDAY: " + CurrentDay + "Current Month" + CurrentMonth + "Current Year " + CurrentYear + "First Day of week month" + firstDayofMonth);




        //Making the Month and Current Day header at the Top
        TextView DateHeader = binding.DateHeader;
        //to store day
        String TextCurrentDay;
        //switch statement coverting day numbers into days ie 0 = sunday
        switch (CurrentDayofWeek) {
            case 1:
                TextCurrentDay = "Sunday";
                break;
            case 2:
                TextCurrentDay = "Monday";
                break;
            case 3:
                TextCurrentDay = "Tuesday";
                break;
            case 4:
                TextCurrentDay = "Wednesday";
                break;
            case 5:
                TextCurrentDay = "Thursday";
                break;
            case 6:
                TextCurrentDay = "Friday";
                break;
            case 7:
                TextCurrentDay = "Saturday";
                break;
            default:
                TextCurrentDay = "Error";
        }

        //now using  switch statement to convert the month numbers into Strings
        String TextCurrentMonth;

        switch (CurrentMonth) {
            case 0:
                TextCurrentMonth = "January";
                break;
            case 1:
                TextCurrentMonth = "February";
                break;
            case 2:
                TextCurrentMonth = "March";
                break;
            case 3:
                TextCurrentMonth = "April";
                break;
            case 4:
                TextCurrentMonth = "May";
                break;
            case 5:
                TextCurrentMonth = "June";
                break;
            case 6:
                TextCurrentMonth = "July";
                break;
            case 7:
                TextCurrentMonth = "August";
                break;
            case 8:
                TextCurrentMonth = "September";
                break;
            case 9:
                TextCurrentMonth = "October";
                break;
            case 10:
                TextCurrentMonth = "November";
                break;
            case 11:
                TextCurrentMonth = "December";
                break;
            default:
                TextCurrentMonth = "error";
                break;
        }
        //displaying the date header
        String HeaderString = TextCurrentDay + ", " + TextCurrentMonth + " " + CurrentDay + ", " + CurrentYear;
        DateHeader.setText(HeaderString);
        DateHeader.setTextSize(30);

        // THINGS TO CHANGE MONTH TO PREVIOUS OR NEXT MONTH
        //when the user goes back months month counter keeps track back one month = -- forward a month = ++
        //same with year
         monthCounter = CurrentMonth;
         yearCounter = CurrentYear;
        Button NextMonthButton = binding.NextMonthButton;
        Button PreviousMonthButton = binding.PreviousMonthButton;
        NextMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //increase the month by 1 and recreate calendar
                if(monthCounter == 11){
                    //if dedcember increase year and change month to january
                    yearCounter++;
                    calendar.set(Calendar.YEAR, yearCounter);
                    calendar.set(Calendar.MONTH, 0);
                    monthCounter = 0;
                }
                else {
                    monthCounter++;
                    calendar.set(Calendar.MONTH, monthCounter);
                }
                //restarting activity
                finish();
                startActivity(getIntent());
            }
        });
        PreviousMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monthCounter == 0){
                    yearCounter--;
                    calendar.set(Calendar.YEAR, yearCounter);
                    calendar.set(Calendar.MONTH, 11);
                    monthCounter = 11;
                }
                else{
                    monthCounter--;
                    calendar.set(Calendar.MONTH, monthCounter);
                }
                //restarting activity
                finish();
                startActivity(getIntent());
            }
        });






        //Making the Days in the current month
        //index to go 1 through 31(or however many days the month has)
        int DayNumber = 1;
        //this is just another version of firstdayofmonth so that we don't change the value of firstday of month
        int daysForFirstWeek = firstDayofMonth;
        //this is just to keep the index of the last day of the month ie the last few days of the previous month + all the days in the next month
        int DayAfterLastDayInMonth = daysForFirstWeek+DaysInMonth;;
        int Sunday = 1;
        //sunday for readability
        int arrayIndex = daysForFirstWeek;
        Log.d("arrayval", "Array value: " + arrayIndex );
        //Case for if the first day of the month isnt sunday
        //the index in the lib for days starts at 1 for some reason even though months start at 0 irl
        if(firstDayofMonth != Sunday){
            for(int i = arrayIndex; i < DayAfterLastDayInMonth; i++){
                //highlight if the day is the clicked day or the current day
                if(CurrentMonth == ClickedMonth && CurrentDay == ClickedDay && CurrentYear == ClickedYear){
                    //the -1 is because the days were all one ahead of the real calendar
                    days[i-1].setBackgroundColor(Color.parseColor("#FF606C38"));
                    days[i-1].setTextColor(Color.WHITE);
                }
                else{
                    days[i-1].setBackgroundColor(Color.TRANSPARENT);
                    days[i-1].setTextColor(Color.BLACK);
                }
                //array linked to each day in the days list
                //this new array will hold the exact date including month and year for each day
                int dateArray[] = new int[3];
                dateArray[0] = DayNumber;
                dateArray[1] = CurrentMonth;
                dateArray[2] = CurrentYear;
                //set tag tags the day in the days list and associates the datearray with it
                //info can be retrieved with getTag()
                days[i-1].setTag(dateArray);
                //setting the text to the day
                Log.d("Set Date", "Added day "+ DayNumber +"day in array " + i );
                days[i-1].setText(String.valueOf(DayNumber));
                //onclick listener for each day
                days[i-1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                //increment to the next day
                DayNumber++;

            }
        }
        //the reason we need if on sunday is because the previous month wont need to be shown if the month starts on sunday
        //could use else but using if in case of errors
        //implementing LATER
        //  if(firstDayofMonth == 1){

        //  }
        //now Setting days for the previous month shown on calendar
        //if Janurary the previous month is december ie 0 to 11
        //remember 11 is december
        if(CurrentMonth == 0){
            calendar.set(CurrentYear -1,  11, 1);
        }
        else{
            calendar.set(CurrentYear, CurrentMonth -1, 1);

        }
        //getting number of days in previous month
        int DaysInPreviousMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //if the first day of the month is thursday(5) it decrements i until 0 and fills the daays with the total number of days in the previous month -1 each loop
        // firstdayofmonth minus 1 so it doesn't write over the first day of the current month
        for(int i = firstDayofMonth-1; i > 0; --i ){

            //same setTag exact date thing
            int dateArray[] = new int[3];
            //if Janurary the previous month is december ie 0 to 11
            //remember 11 is december
            //if the month is janurary previous month is december
            if(CurrentMonth == 0){
                dateArray[0] = DaysInPreviousMonth;
                dateArray[1] = 11;
                dateArray[2] = ClickedYear-1;
            }
            else{
                days[i-1].setBackgroundColor(Color.TRANSPARENT);

                dateArray[0] = DaysInPreviousMonth;
                dateArray[1] = ClickedMonth -1;
                dateArray[2] = ClickedYear;
            }
            //because array starts from zero
            days[i-1].setTag(dateArray);
            days[i-1].setText(String.valueOf(DaysInPreviousMonth));
            days[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            DaysInPreviousMonth--;
        }


        //now creating the days after the current month
        int DaysinNextMonth = 1;
        //the index for the last day of the month which would be number of days in the current month
        //plus the ones shown for the previous month
        //loops until it reaches the total number of day objects created ie the end of the calendar
        //6*7 == 42 so 42 days objects in total
        for(int i = DayAfterLastDayInMonth; i <= days.length; i++ ){
            int[] dateArray = new int[3];
            //for normal month
            if(ClickedMonth < 11 ){
                //if you click on a day in the next month it turns green
                //else it is grey
                if (CurrentMonth == ClickedMonth + 1
                        && CurrentYear == ClickedYear
                        && DaysinNextMonth == CurrentDay) {
                    days[i-1].setBackgroundColor(Color.GREEN);
                } else {
                    days[i-1].setBackgroundColor(Color.TRANSPARENT);
                }
                dateArray[0] = DaysinNextMonth;
                dateArray[1] = ClickedMonth + 1;
                dateArray[2] = ClickedYear;
            }
            //if its december and the next month goes into the next year
            else{
                //11 == december
                if (CurrentMonth == 11
                        && CurrentYear == ClickedYear +1
                        && DaysinNextMonth == CurrentDay) {
                    // FF606C38 = green
                    days[i-1].setBackgroundColor(Color.parseColor("#FF606C38"));
                } else {
                    days[i-1].setBackgroundColor(Color.TRANSPARENT);
                }
                //info to set the tag for the day
                dateArray[0] = DaysinNextMonth;
                dateArray[1] = 0;
                dateArray[2] = ClickedYear + 1;
            }
            days[i-1].setTag(dateArray);
            days[i-1].setTextColor(Color.GRAY);
            days[i-1].setText(String.valueOf(DaysinNextMonth));
            DaysinNextMonth++;
            days[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        //GETTING INFO FROM DATABASE IE THE TASKS AND THEIR DATES
        taskDatabase = TaskDatabase.getInstance(this);

        //creating task list "tasks" sorted by date
        Task[] tasks = taskDatabase.taskDao().getAllTasksByDate();

        for(int i= 0; i < tasks.length; i++){
            Log.d("TASKDATE", "Task " +tasks[i].getTitle() + "Date " + tasks[i].getDueDate());
            String taskDate = tasks[i].getDueDate();
            //spliting the date into month, day, year
            //will make integers later
            if(taskDate != null) {
                if (taskDate != null && taskDate.length() > 0) {
                    String[] taskDateArray = new String[3];
                    if (taskDate.contains("/")){
                        taskDateArray = (taskDate.split("/"));
                    } else {
                        taskDateArray[0] = taskDate.substring(0, 2);
                        taskDateArray[1] = taskDate.substring(taskDate.length() - 6, taskDate.length() - 4);
                        taskDateArray[2] = taskDate.substring(taskDate.length() - 4);
                    }
                    Log.d("TASKSPLIT", taskDateArray[0]+ " " +taskDateArray[1]  + " "+ taskDateArray[2] + " First day of month " + firstDayofMonth + " day of the event " + Integer.parseInt(taskDateArray[1]));
                    if (Integer.parseInt(taskDateArray[0]) - 1 == CurrentMonth && Integer.parseInt(taskDateArray[2]) == CurrentYear) {
                        //finding index for the day of the task
                        //two minus -1s because they both start from zero
                        int dayindex = (firstDayofMonth - 1) + (Integer.parseInt(taskDateArray[1]) - 1);
                        //setting the background color of the day of the task to something else
                        Log.d("FORMAT", tasks[i].getImportance());
                        if (Objects.equals(tasks[i].getImportance(), "High")) {
                            days[dayindex].setBackgroundColor(Color.RED);
                        } else if (Objects.equals(tasks[i].getImportance(), "Medium")) {
                            days[dayindex].setBackgroundColor(Color.YELLOW);

                        } else if (Objects.equals(tasks[i].getImportance(), "Low")) {
                            days[dayindex].setBackgroundColor(Color.GREEN);

                        } else {
                            days[dayindex].setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        }


        //end of oncreate method
    }
    public void exitTask(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

}
