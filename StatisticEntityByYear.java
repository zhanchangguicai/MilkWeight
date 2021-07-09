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
 * StatisticEntityByYear - Basic sets of StatisticEntityByYear
 * 
 */
public class StatisticEntityByYear implements Comparable<StatisticEntityByYear> {

	public static Boolean ASC = null;

	private String farmId;
	private Integer year;

	private Integer weight;

	private Integer allFarmIdWeight;

	// maybe show In View

	public String getPercentInAllFarmIdWeight() {
		double percent = ((double) 100) * ((double) weight) / ((double) allFarmIdWeight);
		String trimmedPercent = percent + "";
		trimmedPercent = trimmedPercent.substring(0, 5);
		return trimmedPercent;
	}

	/**
	 * StatisticEntityByYear - Basic sets of StatisticEntityByYear
	 * 
	 * @param farmId - the string input of farmid
	 * @param year   - the year
	 * @param weight - the weight
	 */
	public StatisticEntityByYear(String farmId, Integer year, Integer weight) {
		this.farmId = farmId;
		this.year = year;
		this.weight = weight;
	}

	/**
	 * getFarmId - return a farmid
	 * 
	 * @return farmId - the farmid we get
	 */
	public String getFarmId() {
		return farmId;
	}

	/**
	 * setFarmId - set a farmid
	 * 
	 */
	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	/**
	 * getYear - return the year
	 * 
	 * @return year - the year we get
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * setYear - set the year
	 * 
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * getWeight - get the milk weight
	 * 
	 * @return weight - the milk weight we get
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * setWeight - set the milk weight
	 * 
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * setAllFarmIdWeight - set the total milk weight
	 * 
	 */
	public void setAllFarmIdWeight(Integer allFarmIdWeight) {
		this.allFarmIdWeight = allFarmIdWeight;
	}

	/**
	 * compareTo - compare the farmid and the milk weight
	 * 
	 * @param other - another StatisticEntityByYear object we want to compare with
	 */
	@Override
	public int compareTo(StatisticEntityByYear other) {
		int result = 0;
		if (ASC != null) {
			if (ASC) {
				result = this.weight.compareTo(other.weight);
			} else {
				result = -this.weight.compareTo(other.weight);
			}
		}
		if (result == 0) {
			result = this.farmId.compareTo(other.farmId);
		}
		return result;
	}

	/**
	 * toString - over write the print toString method
	 * 
	 * @return - A string which shows farmid, year, weight and total weight
	 */
	@Override
	public String toString() {
		return "StatisticEntityByYear{" + "farmId='" + farmId + '\'' + ", year=" + year + ", weight=" + weight
				+ ", allFarmIdWeight=" + allFarmIdWeight + '}';
	}
}
