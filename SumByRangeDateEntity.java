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
public class SumByRangeDateEntity implements
        Comparable<SumByRangeDateEntity>{

   public static Boolean ASC=null;

    private String farmId;
    private String date;
    private Integer weight;
    private Integer allFarmIdWeight;
        
    public SumByRangeDateEntity(String farmId, String date, Integer weight, Integer allFarmIdWeight) {
        this.farmId = farmId;
        this.date = date;
        this.weight = weight;
        this.allFarmIdWeight = allFarmIdWeight;
    }


    /**
    * returns the percentage of weight for the farmIDs total weight produced
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
    public int compareTo(SumByRangeDateEntity other) {
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
            result=this.getDate().compareTo(other.getDate());
        }
        return result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SumByRangeDateEntity{" +
                "farmId='" + farmId + '\'' +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                ", allFarmIdWeight=" + allFarmIdWeight +
                '}';
    }
}
