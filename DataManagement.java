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
import java.util.*;

/**
* DataManagement is the 'data manager' class of this program. It loads the data into our program and allows for 
* an individual farm report, a monthly farm report, a yearly farm report, and a date range farm report to be 
* generated. It uses a combination of the built in TreeMap java ADT and custom classes to store and sort the
* data.
*/
public class DataManagement{

  /**
   * key1: year month date
   * key2: farmid
   */
  private TreeMap<String,Map<String, SumByDayEntity>> allData=new TreeMap<>();
  private boolean hasData = false; //keeps track if this program has data. 

  public DataManagement() {}


  /**
  * This method creates an individual farm report that lists the farms total milk weight
  * for each month over the given year and the percentage of total milk weight that farm 
  * generated compared to the other farms for that month. 
  * @param year - the year to search in for this farm data
  * @param farmID - the farmID to search for in the given year.
  * @return treeSet - a treeSet containing SumByYearEntitys that describe
  * each the farm's summary statistics over a year. 
  */
  public TreeSet<SumByYearEntity> getStatisticsFARMREPORT(
          Integer year,String farmId) {
    TreeSet<SumByYearEntity> treeSet=new TreeSet<>();

    String startDay=DateUtil.getFirstDayStrByYear(year);
    String endDay=DateUtil.getEndDayStrByYear(year);
    endDay=DateUtil.getAddDaysStr(endDay,1);
    DataBox dataBox=new DataBox();
    DataBox dataBoxAllFarmerByMonth=new DataBox();
    SortedMap<String,Map<String, SumByDayEntity>> targetData=allData.subMap(startDay,endDay);
    for(String day:targetData.keySet()) {
      Collection<SumByDayEntity> items = targetData.get(day).values();
      for (SumByDayEntity item : items) {
        if (item.getFarmId().equals(farmId)) {
          dataBox.addItem(item.getMonth() + ""
                  , item.getFarmId(), item.getWeight());
        }
        dataBoxAllFarmerByMonth.addItem(item.getMonth() + ""
                , item.getFarmId(), item.getWeight());
      }
    }
      Map<String, DataBox.SumDate> result=dataBox.sumByDate();
      Map<String, DataBox.SumDate> resultAllFarmer=dataBoxAllFarmerByMonth.sumByDate();
      for(String month:result.keySet()){
      DataBox.SumDate sumDate=result.get(month);
      SumByYearEntity entity=new SumByYearEntity(farmId,year,
              Integer.parseInt(month),
              sumDate.weight);
      entity.setAllFarmIdWeight(dataBox.getTotalWeight());
      entity.setAllMonthWeight(resultAllFarmer.get(month+"").weight);
      treeSet.add(entity);
    }
    return treeSet;
  }

  /**
   * This method generates an annual report based on the given year for each farm contained in the data. 
   * It shows, by month, a farm's total milk weight contribution and their percentage for that month. 
   * @param year - the given year to find the milk weight.
   * @param asc - determines how to sort the generated TreeSet:
   *  if asc is null: sorted by farmid!
   *     asc is true: sorted by weight  asc
   *     asc is false: sorted by weight desc
   * @return treeset - a treeset containing StatisticEntityByYears for each farmID contained in the data. 
   */
  public TreeSet<StatisticEntityByYear> getStatisticsANNUALREPORT(
          Integer year,Boolean asc) {
    StatisticEntityByYear.ASC=asc;
    TreeSet<StatisticEntityByYear> treeSet=new TreeSet<>();
    String startDay=DateUtil.getFirstDayStrByYear(year);
    String endDay=DateUtil.getEndDayStrByYear(year);
    endDay=DateUtil.getAddDaysStr(endDay,1);
    DataBox dataBox=new DataBox();
    SortedMap<String,Map<String, SumByDayEntity>> targetData=allData.subMap(startDay,endDay);
    for(String day:targetData.keySet()){
      Collection<SumByDayEntity> allFarmInOneDay = targetData.get(day).values();
      for(SumByDayEntity item:allFarmInOneDay){
        dataBox.addItem(item.getYear()+"",item.getFarmId(),item.getWeight());
      }
    }
    Map<String, DataBox.SumDate> result=dataBox.sumByFarmId();
    for(String farmId:result.keySet()){
      DataBox.SumDate sumDate=result.get(farmId);
      StatisticEntityByYear entity=new StatisticEntityByYear(farmId,year,sumDate.weight);
      entity.setAllFarmIdWeight(dataBox.getTotalWeight());
      treeSet.add(entity);
    }
    return treeSet;
  }


  /**
   * This method generates a monthly report of all farms contained in the data. It lists out the each farm's
   * contribution for that month as well as their milk weight percentage for that month.
   * @param year - the given year to find the milk weights.
   * @param month - the given month to find the milk weights.
   * @param asc - determines how to sort the generated TreeSet:
   *  if asc is null: sorted by farmid!
   *     asc is true: sorted by weight  asc
   *     asc is false: sorted by weight desc
   * @return treeset - a treeset containing StatisticEntityByYears for each farmID contained in the data. 
   */
  public TreeSet<SumByMonthEntity> getStatisticsMONTHLYREPORT(
          Integer year,Integer month,Boolean asc) {
    SumByMonthEntity.ASC=asc;

    TreeSet<SumByMonthEntity> returnResult=new TreeSet<>();

    for (int mon = 1; mon <=12 ; mon++) {
      String startDay = DateUtil.getFirstDayStr(year,mon);
      String endDay= DateUtil.getEndDayStr(year,mon);
      endDay=DateUtil.getAddDaysStr(endDay,1);
      //get all month data
      SortedMap<String,Map<String, SumByDayEntity>> oneMonthData=allData.subMap(startDay,endDay);
      //farmid,collection
      DataBox dataBox=new DataBox();
      //put data to databox!
      for(String day:oneMonthData.keySet()){
        for(SumByDayEntity oneFarmInOneDay:oneMonthData.get(day).values()){
           dataBox.addItem(oneFarmInOneDay.getYYYYMMDate(),oneFarmInOneDay.getFarmId(),oneFarmInOneDay.getWeight());
        }
      }

      Map<String, DataBox.SumDate> boxResult=dataBox.sumByFarmId();
      for(String farmId:boxResult.keySet()){
        DataBox.SumDate oneFarmResult=boxResult.get(farmId);
        SumByMonthEntity oneFarmReturnValue
                =new SumByMonthEntity(farmId,year,mon,boxResult.get(farmId).weight);
        oneFarmReturnValue.setAllFarmIdWeight(dataBox.getTotalWeight());
        returnResult.add(oneFarmReturnValue);
      }

    }
    return returnResult;
  }

 /**
	 * Generates a report based on the given date range by the user. For that period, all farms that 
   * have data are presented with their totals during that period, as well as their percentage contributions
   * for that period.
	 * @param asc - determines how to sort the generated TreeSet:
   * if asc is null: sorted by farmid!
   *    asc is true: sorted by weight  asc
   *    asc is false: sorted by weight desc
   * @param startDay - the start date of this report
   * @param endDay - the end date of this report.
   * @return treeset - a treeset containing SumByRangeDateEntitys for each farmId contained in the data. 
	*/
  public TreeSet<SumByRangeDateEntity> getStatisticsDATERANGEREPORT(
          String startDay,String endDay,Boolean asc) {
    String s = endDay;
    endDay=DateUtil.getAddDaysStr(endDay,1);
    SumByDayEntity.ASC=asc;
    TreeSet<SumByRangeDateEntity> treeSet=new TreeSet<>(); //[startDay,endDay)
    SortedMap<String,Map<String, SumByDayEntity>> targetData=allData.subMap(startDay,endDay);
    DataBox dataBox =new DataBox();
    for(String day:targetData.keySet()){
        Collection<SumByDayEntity> allFarmInOneDay = targetData.get(day).values();
        for(SumByDayEntity item:allFarmInOneDay){
            dataBox.addItem("",item.getFarmId(),item.getWeight());
        }
    }
    Map<String, DataBox.SumDate> result=dataBox.sumByFarmId();
    for(String farmId:result.keySet()){
      DataBox.SumDate item=result.get(farmId);
      treeSet.add(new SumByRangeDateEntity(farmId,startDay+"-"+s,item.weight,dataBox.getTotalWeight()));
    }
    return treeSet;
  }

  private int computeSumWeight(Collection<SumByDayEntity> allFarmInOneDay) {
      int sum=0;
      for (SumByDayEntity item :
              allFarmInOneDay) {
        sum+=item.getWeight();
      }
    return sum;
  }


  /**
  * Loads data into this program based on the given file name. If any exceptions are thrown, they are passed
  * into the userInterface method where they are handled. 
  * @param file - the given file name
  * @throws LineException - this error is thrown when encountering an unexpected format for a line. Handled in 
  * userInterface. 
  * @throws RuntimeException - this error is thrown when encountering IOExceptions or a FileNotFoundException.
  * Is caught and handled in userInterface.
  */
  public void loadData(String file) throws LineException, RuntimeException{
    ParseFileUtil testing = new ParseFileUtil();
    TreeMap<String,Map<String, SumByDayEntity>> fileData=testing.parseFile(file);
    allData.putAll(fileData);
    hasData = true;
  }

  /**
  * Clears all data contained within this program.
  *
  */
  public void clearData(){
      allData.clear();
      hasData = false;
  }
  
  public boolean dataLoaded() {
    return hasData;
  }

}
