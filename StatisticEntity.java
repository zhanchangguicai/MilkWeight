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
 * StatisticEntity - Basic sets of StatisticEntity
 * 
 */
public class StatisticEntity {

	private String date;
	private String farmId;
	private Integer weight;
	private int month;
	private double percent;

	public StatisticEntity() {

	}

	/**
	 * StatisticEntity - Set the
	 * 
	 * @param initialCapacity - the initialCapacity of HashTable
	 * @param value           - the loadFactor of HashTale
	 * 
	 */
	public StatisticEntity(String date, String farmId, Integer weight) {
		this.date = date;
		this.farmId = farmId;
		this.weight = weight;
	}

	/**
	 * getDate - get the date
	 * 
	 * @return date - the date we get
	 */
	public String getDate() {
		return date;
	}

	/**
	 * setDate - set the date
	 * 
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * getMonth - return a month
	 * 
	 * @return month - the month we get
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * setMonth - set the month
	 * 
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * getPercent - get the precentage
	 * 
	 * @return percent - milk weight/total milk weight
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * setPercent - set the precentage
	 * 
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

}
