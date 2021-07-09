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
 * input: year month
 */
public class SumByMonthEntity implements Comparable<SumByMonthEntity>{

    public static Boolean ASC = true;

    private String farmId;
    private Integer year;
    private Integer month;
    private Integer weight;
    private Integer allFarmIdWeight;
    
    public SumByMonthEntity(String farmId, Integer year, Integer month, Integer weight) {
        this.farmId=farmId;
        this.year=year;
        this.month=month;
        this.weight=weight;
    }

    public String getYYYYMMDate(){
        return DateUtil.getYYYYMMDate(year,month);
    }

    @Override
    public String toString() {
        return "SumByMonthEntity{" +
                "farmId='" + farmId + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", weight=" + weight +
                ", allFarmIdWeight=" + allFarmIdWeight +
                ", weightPercent=" + this.getPercentInAllFarmIdWeight() +
                '}';
    }

    /**
    * returns the percentage of weight compared to total weight produced by farmID
    * @return String trimmedPercent - percentage rounded to 5 significant figures
    */
    public String getPercentInAllFarmIdWeight(){
      double percent = ((double)100) * ((double) weight) / ((double) allFarmIdWeight);
      String trimmedPercent = percent + "";
      trimmedPercent = trimmedPercent.substring(0, 5);
      return trimmedPercent;
    }
    
    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setAllFarmIdWeight(Integer allFarmIdWeight) {
        this.allFarmIdWeight = allFarmIdWeight;
    }

    @Override
    public int compareTo(SumByMonthEntity other) {
        int result=0;
        if(ASC!=null) {
            if (ASC) {
                result = this.weight.compareTo(other.weight);
            } else {
                result = -this.weight.compareTo(other.weight);
            }
        }
        if(result==0){
            result=this.farmId.compareTo(other.farmId);
        }
        if(result==0){
            this.getYYYYMMDate().compareTo(other.getYYYYMMDate());
        }
        return result;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getMonth() {
        return month;
    }
}
