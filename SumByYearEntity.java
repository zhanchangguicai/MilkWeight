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

/**
 * input: farmId  year month
 * output：relevant statistics
 *
 */
public class SumByYearEntity implements
        Comparable<SumByYearEntity>{

    private String farmId;
    private Integer year;
    private Integer month;

    private Integer weight;

    private Integer allFarmIdWeight;
    private Integer allMonthWeight;
        
    public SumByYearEntity(String farmId, Integer year, Integer month,Integer weight) {
        this.farmId=farmId;
        this.year=year;
        this.month=month;
        this.weight=weight;
    }

    /**
    * Returns the percentage the weight makes up of the entire weight produced by this farmID
    * @return String trimmedPercent - percentage rounded to 5 significant figures
    */
    public String getPercentInAllFarmIdWeight(){
      double percent = ((double)100) * ((double) weight) / ((double) allFarmIdWeight);
      String trimmedPercent = percent + "";
      trimmedPercent = trimmedPercent.substring(0, 5);
      return trimmedPercent;
    }
    
    /**
    * Returns the percentage the weight makes up of the entire weight produced for this month
    * @return String trimmedPercent - percentage rounded to 5 significant figures
    */
    public String getPercentInAllMonthWeight(){
      double percent = ((double)100) * ((double) weight) / ((double) allMonthWeight);
      String trimmedPercent = percent + "";
      trimmedPercent = trimmedPercent.substring(0, 5);
      return trimmedPercent;
    }

    /**
    * @return ID of farm
    */
    public String getFarmId() {
        return farmId;
    }

    /**
    * @param farmId - ID of farm to be set
    */
    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
        
    /**
    * @return year data is from
    */
    public Integer getYear() {
        return year;
    }
        
    /**
    * @param year - year data comes from
    */
    public void setYear(Integer year) {
        this.year = year;
    }
    /**
    * @return month data is from
    */
    public Integer getMonth() {
        return month;
    }

    /**
    * @param month - month data comes from
    */
    public void setMonth(Integer month) {
        this.month = month;
    }
    /**
    * @return milk weight produced
    */
    public Integer getWeight() {
        return weight;
    }

    /**
    * @param weight - milk weight produced
    */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
    * @param allMonthWeight - total milk weight produced in month
    */
    public void setAllMonthWeight(Integer allMonthWeight) {
        this.allMonthWeight = allMonthWeight;
    }
    /**
    * @param allFarmIdWeight weight from all farmIDs
    */
    public void setAllFarmIdWeight(Integer allFarmIdWeight) {
        this.allFarmIdWeight = allFarmIdWeight;
    }

    @Override
    public int compareTo(SumByYearEntity other) {
        int result= this.month.compareTo(other.month);
        if(result==0){
            result = this.farmId.compareTo(other.farmId);
        }
        return result;
    }

    @Override
    public String toString() {
        return "SumByYearEntity{" +
                "farmId='" + farmId + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", weight=" + weight +
                ", allFarmIdWeight=" + allFarmIdWeight +
                ", allMonthWeight=" + allMonthWeight +
                '}';
    }
}
