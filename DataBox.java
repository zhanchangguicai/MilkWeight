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
 * DataBox - Basic sets of DataBox
 * 
 */
public class DataBox {
	public static class Data {
		String date;
		String farmId;
		Integer weight;

		/**
		 * Data - Set of our Data
		 * 
		 * @param date   - the date
		 * @param farmId - the farmid
		 * @param weight - the milk weight
		 */
		public Data(String date, String farmId, Integer weight) {
			this.date = date;
			this.farmId = farmId;
			this.weight = weight;
		}
	}

	/**
	 * SumDate - Set of SumData
	 * 
	 */
	public static class SumDate {
		String title;
		Integer weight;

		/**
		 * SumDate - Set of Data with wrong format
		 * 
		 * @param title      - the title
		 * @param computeSum - the computeSum
		 */
		public SumDate(String title, Integer computeSum) {
			this.title = title;
			this.weight = computeSum;
		}
	}

	Map<String, Collection<Data>> allDataByFarm = new HashMap<>();
	Map<String, Collection<Data>> allDataByDate = new HashMap<>();
	private Integer totalWeigth = 0;

	/**
	 * addItem - Add item in to the map
	 * 
	 * @param date   - the date
	 * @param farmId - the farmid
	 * @param weight - the milk weight
	 */
	public void addItem(String date, String farmId, Integer weight) {
		totalWeigth += weight;
		Data data = new Data(date, farmId, weight);
		if (allDataByFarm.get(farmId) == null) {
			allDataByFarm.put(farmId, new LinkedList<>());
		}
		if (allDataByDate.get(date) == null) {
			allDataByDate.put(date, new LinkedList<>());
		}
		allDataByFarm.get(farmId).add(data);
		allDataByDate.get(date).add(data);
	}

	/**
	 * sumByFarmId - the map store by farm id
	 * 
	 */
	public Map<String, SumDate> sumByFarmId() {
		Map<String, SumDate> sumResult = new HashMap<>();
		for (String farm : allDataByFarm.keySet()) {
			Collection<Data> datas = allDataByFarm.get(farm);
			sumResult.put(farm, new SumDate(farm, computeSum(datas)));
		}
		return sumResult;
	}

	/**
	 * sumByDate - the map store by Date
	 * 
	 */
	public Map<String, SumDate> sumByDate() {
		Map<String, SumDate> sumResult = new HashMap<>();
		for (String date : allDataByDate.keySet()) {
			Collection<Data> datas = allDataByDate.get(date);
			sumResult.put(date, new SumDate(date, computeSum(datas)));
		}
		return sumResult;
	}

	/**
	 * computeSum - put the milk weight together
	 * 
	 * @param datas - total milk weight
	 */
	private Integer computeSum(Collection<Data> datas) {
		Integer sum = 0;
		for (Data item : datas) {
			sum += item.weight;
		}
		return sum;
	}

	/**
	 * getTotalWeight - get total milk weight
	 * 
	 * @return totalWeigth - total milk weight
	 */
	public Integer getTotalWeight() {
		return totalWeigth;
	}

}
