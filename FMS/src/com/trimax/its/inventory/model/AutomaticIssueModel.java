package com.trimax.its.inventory.model;

import java.util.List;

public class AutomaticIssueModel {

	public String denominationValue;
	public String keyNumber;
	public String openingNumber;
	public String closingNumber;
	public String noOfTickets;
	public String noOfBooks;
	public String value;
	public String passType;
	public String noOfPasses;
	public String ticketType;
	public String passDay;
	
	List<AutomaticIssueModel> passengerTypeTickets;
	List<AutomaticIssueModel> passTypeTickets;
	List<AutomaticIssueModel> luggaugeTickets;
	
	public AutomaticIssueModel passengerTickets;
	
	public String getDenominationValue() {
		return denominationValue;
	}
	public void setDenominationValue(String denominationValue) {
		this.denominationValue = denominationValue;
	}
	public String getKeyNumber() {
		return keyNumber;
	}
	public void setKeyNumber(String keyNumber) {
		this.keyNumber = keyNumber;
	}
	public String getOpeningNumber() {
		return openingNumber;
	}
	public void setOpeningNumber(String openingNumber) {
		this.openingNumber = openingNumber;
	}
	public String getClosingNumber() {
		return closingNumber;
	}
	public void setClosingNumber(String closingNumber) {
		this.closingNumber = closingNumber;
	}
	public String getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(String noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public String getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(String noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPassType() {
		return passType;
	}
	public void setPassType(String passType) {
		this.passType = passType;
	}
	public String getNoOfPasses() {
		return noOfPasses;
	}
	public void setNoOfPasses(String noOfPasses) {
		this.noOfPasses = noOfPasses;
	}
	public List<AutomaticIssueModel> getPassengerTypeTickets() {
		return passengerTypeTickets;
	}
	public void setPassengerTypeTickets(
			List<AutomaticIssueModel> passengerTypeTickets) {
		this.passengerTypeTickets = passengerTypeTickets;
	}
	public List<AutomaticIssueModel> getPassTypeTickets() {
		return passTypeTickets;
	}
	public void setPassTypeTickets(List<AutomaticIssueModel> passTypeTickets) {
		this.passTypeTickets = passTypeTickets;
	}
	public List<AutomaticIssueModel> getLuggaugeTickets() {
		return luggaugeTickets;
	}
	public void setLuggaugeTickets(List<AutomaticIssueModel> luggaugeTickets) {
		this.luggaugeTickets = luggaugeTickets;
	}
	public AutomaticIssueModel getPassengerTickets() {
		return passengerTickets;
	}
	public void setPassengerTickets(AutomaticIssueModel passengerTickets) {
		this.passengerTickets = passengerTickets;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getPassDay() {
		return passDay;
	}
	public void setPassDay(String passDay) {
		this.passDay = passDay;
	}
	
	
}
