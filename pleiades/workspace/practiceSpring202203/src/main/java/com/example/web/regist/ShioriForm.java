package com.example.web.regist;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ShioriForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String planTitle;
	private Integer planDateYear;
	private Integer planDateMonth;
	private Integer planDateDay;
	private String placeName;
	private String placeArrivalHour;
	private String placeArrivalMinute;
	private String placeDepartureHour;
	private String placeDepartureMinute;

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public Integer getPlanDateYear() {
		return planDateYear;
	}

	public void setPlanDateYear(Integer planDateYear) {
		this.planDateYear = planDateYear;
	}

	public Integer getPlanDateMonth() {
		return planDateMonth;
	}

	public void setPlanDateMonth(Integer planDateMonth) {
		this.planDateMonth = planDateMonth;
	}

	public Integer getPlanDateDay() {
		return planDateDay;
	}

	public void setPlanDateDay(Integer planDateDay) {
		this.planDateDay = planDateDay;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceArrivalHour() {
		return placeArrivalHour;
	}

	public void setPlaceArrivalHour(String placeArrivalHour) {
		this.placeArrivalHour = placeArrivalHour;
	}

	public String getPlaceArrivalMinute() {
		return placeArrivalMinute;
	}

	public void setPlaceArrivalMinute(String placeArrivalMinute) {
		this.placeArrivalMinute = placeArrivalMinute;
	}

	public String getPlaceDepartureHour() {
		return placeDepartureHour;
	}

	public void setPlaceDepartureHour(String placeDepartureHour) {
		this.placeDepartureHour = placeDepartureHour;
	}

	public String getPlaceDepartureMinute() {
		return placeDepartureMinute;
	}

	public void setPlaceDepartureMinute(String placeDepartureMinute) {
		this.placeDepartureMinute = placeDepartureMinute;
	}

	public List<Integer> planDateYearListCreate() {
		List<Integer> planDateYearList = new LinkedList<>();

		int year = LocalDate.now().getYear();
		for (int i = year; i <= year + 5; i++) {
			planDateYearList.add(i);
		}
		return planDateYearList;
	}

	public List<Integer> planDateMonthListCreate() {
		List<Integer> planDateMonthList = new LinkedList<>();

		for (int i = 1; i <= 12; i++) {
			planDateMonthList.add(i);
		}
		return planDateMonthList;
	}

	public List<Integer> planDateDayListCreate() {
		List<Integer> planDateDayList = new LinkedList<>();

		for (int i = 1; i <= 31; i++) {
			planDateDayList.add(i);
		}
		return planDateDayList;
	}

	public List<Integer> placeArrivalHourListCreate() {
		List<Integer> placeArrivalHourList = new LinkedList<>();

		for (int i = 0; i <= 24; i++) {
			placeArrivalHourList.add(i);
		}
		return placeArrivalHourList;
	}

	public List<Integer> placeArrivalMinuteListCreate() {
		List<Integer> placeArrivalMinuteList = new LinkedList<>();

		for (int i = 0; i <= 50; i += 10) {
			placeArrivalMinuteList.add(i);
		}
		return placeArrivalMinuteList;
	}

	public List<Integer> placeDepartureHourListCreate() {
		List<Integer> placeDepartureHourList = new LinkedList<>();

		for (int i = 0; i <= 24; i++) {
			placeDepartureHourList.add(i);
		}
		return placeDepartureHourList;
	}

	public List<Integer> placeDepartureMinuteListCreate() {
		List<Integer> placeDepartureMinuteList = new LinkedList<>();

		for (int i = 0; i <= 50; i += 10) {
			placeDepartureMinuteList.add(i);
		}
		return placeDepartureMinuteList;
	}

}
