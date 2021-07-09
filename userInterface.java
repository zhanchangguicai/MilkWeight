/**
 * 
 * Authors: Yansong Tang, Carleigh Heintz, Doran Redlich, Qi Gao
 * Lecture: CS 400, Spring 2020, LEC 001. 
 * 
 * Program: MilkWeights, a program that can read a file and store the information within to
 *          present summary statistics based on the inputted data. 
 *          
 * (Essential) Classes: DataBox.java, DataManagement.java, DateUtil.java, LineException.java, ParseFileUtil.java,
 *                StatisticEntity.java, StatisticEntityByYear.java, SumByDayEntity.java, SumByMonthEntity.java,
 *                SumByRangeDateEntity.java, SumByYearEntity.java, userInterface.java
 * 
 * In-depth information can be found in the README file in this project. 
 */


package application;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*; 
import javafx.scene.layout.*; 

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * creates user interface for milk weights program, including the window and all the controls
 *
 * @author Yansong Tang
 * @author Carleigh Heintz
 * @author Doran Redlich
 * @author Qi Gao
 *
 *
 *references https://www.geeksforgeeks.org/javafx-hbox-class/
 *http://tutorials.jenkov.com/javafx/index.html
 *
 */
public class userInterface extends Application {
  private List<String> args;
  private static final int WINDOW_WIDTH = 1000;
  private static final int WINDOW_HEIGHT = 500;
  
  // retain which command is being used in show data screen, 0 = none, 1 = farm, 2 = annual, 3 = monthly, 4 = date range
  private int dataVal = 0;
  // manage data to display properly
  private DataManagement dm = new DataManagement();
  //for displaying 12 farms at a time and then transitioning with next and last btn presses
  private int displayStart = 12;
  
  @Override
  public void start(Stage mainStage) throws Exception {
    // TODO Auto-generated method stub
    args = this.getParameters().getRaw();
    
    //sets up 3 main scenes of program
    BorderPane mainroot = new BorderPane();
    Scene mainScene = new Scene(mainroot, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    BorderPane alterRoot = new BorderPane();
    Scene alterScene = new Scene(alterRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    BorderPane dataRoot = new BorderPane();
    Scene dataScene = new Scene(dataRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    // create buttons to navigate between scenes and add them to borderpanes of scenes
    Button toAlterBtn = new Button("Change data screen");
    Button toDataBtn = new Button("View data screen");
    Button toMainBtn = new Button("Main screen");
    Button toMainBtn2 = new Button("Main screen");
    
    toAlterBtn.setOnAction(new EventHandler<ActionEvent>() {
       public void handle(ActionEvent e) {
        mainStage.setScene(alterScene);
    }});   
    toMainBtn.setOnAction(new EventHandler<ActionEvent>() {
       public void handle(ActionEvent e) {
        mainStage.setScene(mainScene);
    }});
    toMainBtn2.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       mainStage.setScene(mainScene);
   }});
    toDataBtn.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       mainStage.setScene(dataScene);
   }});
    
    // setup main scene
    Text welcomeTxt = new Text("Welcome to our Milk Weights Program! This program allows you to add data of farms' daily added weights through csv files or manual entry. Once data is entered, you can also view statistics based off on entered dates. Use the two buttons below to navigate between the data input and statistics screen.");
    welcomeTxt.setWrappingWidth(WINDOW_WIDTH/2);
    welcomeTxt.setFont(new Font(20));
    HBox mainCenter = new HBox(welcomeTxt);
    mainCenter.setAlignment(Pos.CENTER);
    mainroot.setCenter(mainCenter);
    
    Button exitBtn = new Button("Exit the program");
    exitBtn.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       System.exit(0);
   }});
    
    HBox mainBtm = new HBox(40);
    mainBtm.setAlignment(Pos.CENTER);
    mainBtm.getChildren().addAll(toAlterBtn, toDataBtn, exitBtn);
    mainroot.setBottom(mainBtm);
    
    // setup scene to modify or add data
    VBox alterTop = new VBox(10);
    alterTop.setAlignment(Pos.CENTER);
    alterRoot.setTop(alterTop);
    
    // adds buttons to scene and aligns them correctly
    Text alterDirections = new Text("Choose your desired action");
    HBox alterChoices = new HBox(10);
    alterChoices.setAlignment(Pos.CENTER);
    Button clearData = new Button("Clear data");
    Button inputData = new Button("Input file as data");
    alterChoices.getChildren().addAll(inputData, clearData);
    alterTop.getChildren().addAll(alterDirections, alterChoices);
    
    VBox inputCenter = new VBox(20);
    inputCenter.setAlignment(Pos.TOP_CENTER);
    alterRoot.setCenter(inputCenter);
    Text currInputType = new Text("Current input type");
    Button processBtn = new Button("Process instruction");
    inputCenter.getChildren().add(currInputType);
    
    //various descriptions along with text fields to accept user input 
    /*Text farmDesc = new Text("Enter in the number of the farm");
    TextField farmInput = new TextField();
    HBox farmBox = new HBox(20);
    farmBox.setAlignment(Pos.CENTER);
    farmBox.getChildren().addAll(farmDesc,farmInput);
    
    Text monthDesc = new Text("Enter in the month as a number (1 = Jan, 12 = Dec)");
    TextField monthInput = new TextField();
    HBox monthBox = new HBox(20);
    monthBox.setAlignment(Pos.CENTER);
    monthBox.getChildren().addAll(monthDesc,monthInput);
    
    Text yearDesc = new Text("Enter in the year");
    TextField yearInput = new TextField();
    HBox yearBox = new HBox(20);
    yearBox.setAlignment(Pos.CENTER);
    yearBox.getChildren().addAll(yearDesc,yearInput);
    
    Text weightDesc = new Text("Enter in the milk weight");
    TextField weightInput = new TextField();
    HBox weightBox = new HBox(20);
    weightBox.setAlignment(Pos.CENTER);
    weightBox.getChildren().addAll(weightDesc,weightInput);
    
    Text dayDesc = new Text("Enter in the day you want to change as a number");
    TextField dayInput = new TextField();
    HBox dayBox = new HBox(20);
    dayBox.setAlignment(Pos.CENTER);
    dayBox.getChildren().addAll(dayDesc,dayInput); */
    
    Text fileInDesc = new Text("Type the name of the file you want to input here");
    TextField fileInput = new TextField();
    HBox fileInBox = new HBox(20);
    fileInBox.setAlignment(Pos.CENTER);
    fileInBox.getChildren().addAll(fileInDesc,fileInput);
    
    Text inputConfirm = new Text("File inputted");
    
      // add functionality to buttons to clear and input data
    clearData.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       inputCenter.getChildren().clear();
       currInputType.setText("Clearing data");
       inputConfirm.setText("Data cleared");
       inputCenter.getChildren().addAll(currInputType, processBtn);
   }});
    
    inputData.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       inputCenter.getChildren().clear();
       currInputType.setText("Inputting a file as data");
       inputConfirm.setText("File inputted");
       inputCenter.getChildren().addAll(currInputType, fileInBox, processBtn);
   }});
    
    processBtn.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       inputCenter.getChildren().clear();
       inputConfirm.setText("");
       if (currInputType.getText().equals("Inputting a file as data")) {
       try {
        dm.loadData(fileInput.getText());
        inputConfirm.setText("File inputted");
      } catch (LineException e1) {
        inputConfirm.setText(e1.getMessage());
      } catch (RuntimeException e1) {
        inputConfirm.setText(e1.getMessage());
      }
       inputCenter.getChildren().addAll(currInputType, fileInBox, processBtn, inputConfirm);
       
       }
       else if (currInputType.getText().equals("Clearing data")) {
         dm.clearData();
         inputCenter.getChildren().addAll(currInputType, processBtn, inputConfirm);
       }
       
   }});
    
    HBox alterBtm = new HBox(toMainBtn);
    alterBtm.setAlignment(Pos.CENTER);
    alterRoot.setBottom(alterBtm);
    
    
    
    
    //setup scene to display data
    VBox dataTop = new VBox(10);
    dataTop.setAlignment(Pos.CENTER);
    dataRoot.setTop(dataTop);
    
    Text dataDirections = new Text("Choose your desired action");
    HBox dataChoices = new HBox(10);
    dataChoices.setAlignment(Pos.CENTER);
    Button farmReport = new Button("Single farm (annual)");
    Button annualReport = new Button("Annual (all farms)");
    Button monthlyReport = new Button("Monthly (all farms)");
    Button dateRange = new Button("Date range (all farms)");
    dataChoices.getChildren().addAll(farmReport, annualReport, monthlyReport, dateRange);
    dataTop.getChildren().addAll(dataDirections, dataChoices);
    
    VBox dataCenter = new VBox(20);
    dataCenter.setAlignment(Pos.TOP_CENTER);
    dataRoot.setCenter(dataCenter);
    
    Text currDataType = new Text("Current data type");
    dataCenter.getChildren().add(currDataType);
    
    Button showDataBtn = new Button("Show Data");
    
    // add buttons to change between sets of farms, not initially displayed
    VBox nextFarms = new VBox(15);
    nextFarms.setAlignment(Pos.CENTER);
    Button nextBtn = new Button("Next farms");
    nextFarms.getChildren().add(nextBtn);
    Button lastBtn = new Button("Last farms");
    VBox lastFarms = new VBox(15);
    lastFarms.setAlignment(Pos.CENTER);
    lastFarms.getChildren().add(lastBtn);
        
        
    // new boxes for data display screen to not have issues when switching screens
    // othewise same 
    Text farmDescD = new Text("Enter in farm number ex: Farm 1");
    TextField farmInputD = new TextField();
    HBox farmBoxD = new HBox(20);
    farmBoxD.setAlignment(Pos.CENTER);
    farmBoxD.getChildren().addAll(farmDescD,farmInputD);
    
    Text monthDescD = new Text("Enter in the month as a number (1 = Jan, 12 = Dec)");
    TextField monthInputD = new TextField();
    HBox monthBoxD = new HBox(20);
    monthBoxD.setAlignment(Pos.CENTER);
    monthBoxD.getChildren().addAll(monthDescD,monthInputD);
    
    Text yearDescD = new Text("Enter in the year");
    TextField yearInputD = new TextField();
    HBox yearBoxD = new HBox(20);
    yearBoxD.setAlignment(Pos.CENTER);
    yearBoxD.getChildren().addAll(yearDescD,yearInputD);
    
    Text startDescD = new Text("Enter in the starting day (yyyy-mm-dd)");
    TextField startInputD = new TextField();
    HBox startBoxD = new HBox(20);
    startBoxD.setAlignment(Pos.CENTER);
    startBoxD.getChildren().addAll(startDescD,startInputD);
    
    Text endDescD = new Text("Enter in the ending day (mm-dd)");
    TextField endInputD = new TextField();
    HBox endBoxD = new HBox(20);
    endBoxD.setAlignment(Pos.CENTER);
    endBoxD.getChildren().addAll(endDescD,endInputD);
    
    // edit various ui components based on what was clicked
    farmReport.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       dataCenter.getChildren().clear();
       currDataType.setText("Input farm id and year");
       dataVal = 1;
       dataCenter.getChildren().addAll(currDataType, farmBoxD, yearBoxD, showDataBtn);
   }});   
    
    annualReport.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       dataCenter.getChildren().clear();
       currDataType.setText("Input a year");
       dataVal = 2;
       dataCenter.getChildren().addAll(currDataType, yearBoxD, showDataBtn);
   }});
    
    monthlyReport.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       dataCenter.getChildren().clear();
       currDataType.setText("Input month and year");
       dataVal = 3;
       dataCenter.getChildren().addAll(currDataType, monthBoxD, yearBoxD, showDataBtn);
   }});
    
    dateRange.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
       dataCenter.getChildren().clear();
       currDataType.setText("Input start day and end day");
       dataVal = 4;
       dataCenter.getChildren().addAll(currDataType, startBoxD, endBoxD, showDataBtn);
   }});
    
        
     VBox showedData = new VBox(15);
     showedData.setAlignment(Pos.CENTER);
     // set up slots for data display, 3 rows with 4 columns of slots
     Text IDVal = new Text();
     HBox r1 = new HBox(10);
     Text s1 = new Text();
     Text s2 = new Text();
     Text s3 = new Text();
     Text s4 = new Text();
     r1.getChildren().addAll(s1, s2, s3, s4);
     r1.setAlignment(Pos.CENTER);
     HBox r2 = new HBox(10);
     Text s5 = new Text();
     Text s6 = new Text();
     Text s7 = new Text();
     Text s8 = new Text();
     r2.getChildren().addAll(s5, s6, s7, s8);
     r2.setAlignment(Pos.CENTER);
     HBox r3 = new HBox(10);
     Text s9 = new Text();
     Text s10 = new Text();
     Text s11 = new Text();
     Text s12 = new Text();
     r3.getChildren().addAll(s9, s10, s11, s12);
     r3.setAlignment(Pos.CENTER);
     Text summaryStats = new Text();
     showedData.getChildren().addAll(IDVal, r1, r2,r3);
     ArrayList<Text> slots = new ArrayList<Text>();
     // addall not working
     //add all slows to an arraylist so they can be iterated over
     slots.add(s1);
     slots.add(s2);
     slots.add(s3);
     slots.add(s4);
     slots.add(s5);
     slots.add(s6);
     slots.add(s7);
     slots.add(s8);
     slots.add(s9);
     slots.add(s10);
     slots.add(s11);
     slots.add(s12);
            
            
    // functionality to show data based on what was previously clicked
    showDataBtn.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        dataCenter.getChildren().clear();
        if (dm.dataLoaded() == false) {
          dataVal = 0;
        }
    
        switch (dataVal) {
          case 0:
            dataCenter.getChildren().add(new Text("You have not inputted any data to display yet in the change data screen"));
            break;
            
          case 1: // farm report
            dataCenter.getChildren().addAll(currDataType, farmBoxD, yearBoxD, showDataBtn, showedData);
          //stop if user doesn't give valid data
            TreeSet<SumByYearEntity> treeset = new TreeSet<SumByYearEntity>();
            try {
              Integer year = Integer.parseInt(yearInputD.getText());
              treeset = dm.getStatisticsFARMREPORT(year, farmInputD.getText());
            }catch (Exception err) {
              // get rid of side navigation buttons if erroneous data
              IDVal.setText("Error in inputting data");
              dataRoot.setRight(null);
              dataRoot.setLeft(null);
              break;
            }
            
         // set the initial values of the slots
            IDVal.setText(farmInputD.getText() + " Year: " + yearInputD.getText());
            int i = 0;
            for (SumByYearEntity data: treeset) { 
              slots.get(i).setText("Month: " + data.getMonth() + ", Weight: " + 
                   data.getWeight() + ", " + data.getPercentInAllMonthWeight() + "%");
             i++;
             if (i >= slots.size()) {
               break;
             }
            }
            // get rid of navigation buttons on side since not needed
            dataRoot.setRight(null);
            dataRoot.setLeft(null);
            break;
            
          case 2: // annual report
            dataCenter.getChildren().addAll(currDataType, yearBoxD, showDataBtn, showedData);
            //stop if user doesn't give valid data
            TreeSet<StatisticEntityByYear> treeset2 = new TreeSet<StatisticEntityByYear>();
            try {
              Integer year = Integer.parseInt(yearInputD.getText());
              treeset2 = dm.getStatisticsANNUALREPORT(year, null);
            }catch (Exception err) {
              // get rid of side navigation buttons if erroneous data
              IDVal.setText("Input data is not valid");
              dataRoot.setRight(null);
              dataRoot.setLeft(null);
              break;
            }
            
         // set the initial values of the slots
            IDVal.setText("Year: " + yearInputD.getText());
            int i2 = 0;
            for (StatisticEntityByYear data: treeset2) { 
              slots.get(i2).setText(data.getFarmId() + ", Weight: " + 
                   data.getWeight() + ", " + data.getPercentInAllFarmIdWeight() + "%");
             i2++;
             if (i2 >= slots.size()) {
               break;
             }
            }
            
         // functionality for btn to go to next farms
            nextBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               Integer year = Integer.parseInt(yearInputD.getText());
               TreeSet<StatisticEntityByYear> treeset = dm.getStatisticsANNUALREPORT(year, null);
               if (displayStart + 12 < treeset.size() + 12) {
                 displayStart += 12;
               }
               ArrayList<StatisticEntityByYear> treeArray= new ArrayList<StatisticEntityByYear>(treeset.size());
               for (StatisticEntityByYear data: treeset) {  
                 treeArray.add(data);
               }
            // change treeset to array to access indecies
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 }
                 if (i >= treeArray.size()-1) {
                   break;
                 }
                 slots.get(i).setText(treeArray.get(i + displayStart - 12).getFarmId() + ", Weight: " + 
                       treeArray.get(i + displayStart - 12).getWeight() + ", " + treeArray.get(i + displayStart - 12).getPercentInAllFarmIdWeight() + "%");
               }  
               
           }});
         // functionality for btn to go to previous farms
            lastBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               Integer year = Integer.parseInt(yearInputD.getText());
               TreeSet<StatisticEntityByYear> treeset = dm.getStatisticsANNUALREPORT(year, null);
               if (displayStart - 12 > 0) {
                 displayStart -= 12;
               }
            // change treeset to array to access indecies
               ArrayList<StatisticEntityByYear> treeArray= new ArrayList<StatisticEntityByYear>(treeset.size());
               for (StatisticEntityByYear data: treeset) {  
                 treeArray.add(data);
               }
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 }
                 slots.get(i).setText(treeArray.get(displayStart - 12 + i).getFarmId() + ", Weight: " + 
                       treeArray.get(displayStart - 12 + i).getWeight() + ", " + treeArray.get(displayStart - 12 + i).getPercentInAllFarmIdWeight() + "%");
                 
               }  
               
           }});          
            
            dataRoot.setRight(nextFarms);
            dataRoot.setLeft(lastFarms);
            break;
            
          case 3: // month report
            dataCenter.getChildren().addAll(currDataType, monthBoxD, yearBoxD, showDataBtn, showedData);
            // stop if user doesn't give valid data
            TreeSet<SumByMonthEntity> treeset3 = new TreeSet<SumByMonthEntity>();
            try {
              Integer year = Integer.parseInt(yearInputD.getText());
              Integer month = Integer.parseInt(monthInputD.getText());
              treeset3 = dm.getStatisticsMONTHLYREPORT(year, month, null);
            }catch (Exception err) {
              // get rid of side navigation buttons if erroneous data
              IDVal.setText("Input data is not valid");
              dataRoot.setRight(null);
              dataRoot.setLeft(null);
              break;
            }
            
         // set the initial values of the slots
            IDVal.setText("Month: " + monthInputD.getText() + " Year: " + yearInputD.getText());
            int i3 = 0;
            for (SumByMonthEntity data: treeset3) { 
              slots.get(i3).setText(data.getFarmId() + ", Weight: " + 
                   data.getWeight() + ", " + data.getPercentInAllFarmIdWeight() + "%");
             i3++;
             if (i3 >= slots.size()) {
               break;
             }
            }
            
         // functionality for btn to go to next farms
            nextBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               Integer year = Integer.parseInt(yearInputD.getText());
               Integer month = Integer.parseInt(monthInputD.getText());
               TreeSet<SumByMonthEntity> treeset = dm.getStatisticsMONTHLYREPORT(year, month, null);
               if (displayStart + 12 < treeset.size() + 12) {
                 displayStart += 12;
               }
            // change treeset to array to access indecies
               ArrayList<SumByMonthEntity> treeArray= new ArrayList<SumByMonthEntity>(treeset.size());
               for (SumByMonthEntity data: treeset) {  
                 treeArray.add(data);
               }
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 }
                 if (i >= treeArray.size()-1) {
                   break;
                 } 
                slots.get(i).setText(treeArray.get(i + displayStart - 12).getFarmId() + ", Weight: " + 
                       treeArray.get(i + displayStart - 12).getWeight() + ", " + treeArray.get(i + displayStart - 12).getPercentInAllFarmIdWeight() + "%");
               }  
               
           }});
            
         // functionality for btn to go to previous farms
            lastBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               Integer year = Integer.parseInt(yearInputD.getText());
               Integer month = Integer.parseInt(monthInputD.getText());
               TreeSet<SumByMonthEntity> treeset = dm.getStatisticsMONTHLYREPORT(year, month, null);
               if (displayStart - 12 > 0) {
                 displayStart -= 12;
               }
            // change treeset to array to access indecies
               ArrayList<SumByMonthEntity> treeArray= new ArrayList<SumByMonthEntity>(treeset.size());
               for (SumByMonthEntity data: treeset) {  
                 treeArray.add(data);
               }
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 } 
                 
                slots.get(i).setText(treeArray.get(displayStart - 12 + i).getFarmId() + ", Weight: " + 
                       treeArray.get(displayStart - 12 + i).getWeight() + ", " + treeArray.get(displayStart - 12 + i).getPercentInAllFarmIdWeight() + "%");
               }  
               
           }});         
            
            dataRoot.setRight(nextFarms);
            dataRoot.setLeft(lastFarms);
            break;
            
          case 4: // date report
            dataCenter.getChildren().addAll(currDataType, startBoxD, endBoxD, showDataBtn, showedData);
            TreeSet<SumByRangeDateEntity> treeset4 = new TreeSet<SumByRangeDateEntity>();
            // stop if user doesn't give valid data
            try {
              treeset4 = dm.getStatisticsDATERANGEREPORT(startInputD.getText().replace("-", ""), "2019" + endInputD.getText().replace("-", ""), null);
            }catch (Exception err) {
              System.out.println();
              // get rid of side navigation buttons if erroneous data
              IDVal.setText("Input data is not valid");
              dataRoot.setRight(null);
              dataRoot.setLeft(null);
              break;
            }
            
            // set the initial values of the slots
            IDVal.setText("Month: " + monthInputD.getText() + " Year: " + yearInputD.getText());
            int i4 = 0;
            for (SumByRangeDateEntity data: treeset4) { 
             slots.get(i4).setText(data.getFarmId() + ", Weight: " + 
                   data.getWeight() + ", " + data.getPercentInAllFarmIdWeight() + "%");
             i4++;
             if (i4 >= slots.size()) {
               break;
             }
            }
            
            IDVal.setText("start day: " + startInputD.getText() + " end day: " + endInputD.getText());

         // functionality for btn to go to next farms
            nextBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               TreeSet<SumByRangeDateEntity> treeset = dm.getStatisticsDATERANGEREPORT(startInputD.getText().replace("-", ""), "2019" + endInputD.getText().replace("-", ""), null);
               if (displayStart + 12 < treeset.size() + 12) {
                 displayStart += 12;
               }
            // change treeset to array to access indecies
               ArrayList<SumByRangeDateEntity> treeArray= new ArrayList<SumByRangeDateEntity>(treeset.size());
               for (SumByRangeDateEntity data: treeset) {  
                 treeArray.add(data);
               }
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 }
                 if (i >= treeArray.size()-1) {
                   break;
                 } 
                 slots.get(i).setText(treeArray.get(i + displayStart - 12).getFarmId() + ", Weight: " + 
                       treeArray.get(i + displayStart - 12).getWeight() + ", " + treeArray.get(i + displayStart - 12).getPercentInAllFarmIdWeight() + "%");
               }  
               
           }});
            // functionality for btn to go to previous farms
            lastBtn.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent e) {
               Integer year = Integer.parseInt(yearInputD.getText());
               Integer month = Integer.parseInt(monthInputD.getText());
               TreeSet<SumByRangeDateEntity> treeset = dm.getStatisticsDATERANGEREPORT(startInputD.getText().replace("-", ""), "2019" + endInputD.getText().replace("-", ""), null);
               if (displayStart - 12 > 0) {
                 displayStart -= 12;
               }
               // change treeset to array to access indecies
               ArrayList<SumByRangeDateEntity> treeArray= new ArrayList<SumByRangeDateEntity>(treeset.size());
               for (SumByRangeDateEntity data: treeset) {  
                 treeArray.add(data);
               }
               for (int i = 0; i < 12; i++) { 
                 if (i >= slots.size()) {
                   break;
                 }
                 slots.get(i).setText(treeArray.get(displayStart - 12 + i).getFarmId() + ", Weight: " + 
                       treeArray.get(displayStart - 12 + i).getWeight() + ", " + treeArray.get(displayStart - 12 + i).getPercentInAllFarmIdWeight() + "%");
               }  
               
           }}); 
            
            dataRoot.setRight(nextFarms);
            dataRoot.setLeft(lastFarms);
            break;
        }
   }});
    
    // 2nd button to navigate back to main scrn
    HBox dataBtm = new HBox(toMainBtn2);
    dataBtm.setAlignment(Pos.CENTER);
    dataRoot.setBottom(dataBtm);
    
    // set window name
    mainStage.setTitle("Milk Weights Program");
    mainStage.setScene(mainScene);
    mainStage.show();
  }
  
  /** creates the program window
   * @param args to be run
   */
  public static void main(String[] args) {
    launch(args);
  }

}