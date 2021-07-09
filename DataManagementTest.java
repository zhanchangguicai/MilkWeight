package application;

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

import org.junit.jupiter.api.Test;

import java.util.TreeSet;

public class DataManagementTest {
//    @Test
//    public void testGetStatisticsByStartAndEndDay(){
//        DataManagement dataManagement=new DataManagement();
//        dataManagement.loadData("C:\\wps网盘同步文件夹\\高祈\\2020年4月28日\\ateam125-milk-weights-master\\test01.csv");
//        TreeSet<SumByDayEntity> result= dataManagement.getStatisticsByStartAndEndDay("20190401","20190403",false);
//        for(SumByDayEntity minSumEntity:result){
//            System.out.println(minSumEntity);
//        }
//    }

  
    @Test
    public void testGetStatisticsByYearAndFarmId(){
        DataManagement dataManagement=new DataManagement();
        try {
          dataManagement.loadData("2019-1.csv");
        } catch (LineException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        TreeSet<SumByMonthEntity> result= dataManagement.getStatisticsMONTHLYREPORT(2019, 1, null);
        for(SumByMonthEntity item:result){
            System.out.println(item);
        }
    }
    
    @Test
    public void testGetStatisticsStartAndEndDay() {
      DataManagement dataManagement=new DataManagement();
      try {
        dataManagement.loadData("2019-1.csv");
      } catch (LineException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      TreeSet<SumByRangeDateEntity> result= dataManagement.getStatisticsDATERANGEREPORT("20190101", "20190110", null);
      for(SumByRangeDateEntity item:result) {
        System.out.println(item);
      }
      
    }
    
    @Test
    public void testErrors() {
      System.out.println("ANNUAL REPORT:");
      DataManagement dataManagement=new DataManagement();
      try {
        dataManagement.loadData("2019-1-large.csv");
      } catch (LineException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      TreeSet<StatisticEntityByYear> result= dataManagement.getStatisticsANNUALREPORT(2019, null);
      for(StatisticEntityByYear item:result) {
        System.out.println(item);
      }
      
    }
    
    @Test
    public void testgetStatisticsFARMREPORT() {
      System.out.println("FARM REPORT: ");
      DataManagement dataManagement=new DataManagement();
      try {
        dataManagement.loadData("2019-1.csv");
      } catch (LineException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      TreeSet<SumByYearEntity> result= dataManagement.getStatisticsFARMREPORT(2019, "Farm 0");
      for(SumByYearEntity item:result) {
        System.out.println(item);
      }
      
    }
}
