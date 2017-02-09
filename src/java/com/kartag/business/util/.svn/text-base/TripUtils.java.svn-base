package com.kartag.business.util;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import com.kartag.common.util.DateUtil;
import com.kartag.common.util.StringUtil;
import com.kartag.server.model.Trip;

public class TripUtils {

	public static Set<Trip> generatescheduledTrips(
			LinkedHashMap<String, String> trips) throws Exception{
		Set<Trip> scheduledTrips = new HashSet<Trip>();
		final String once = "once";
		
		String[] daysArray;
		
		String tripSchedule = trips.get("trip_schedule");
		String time = trips.get("time");
		java.util.Date tripTime = null;
		if(tripSchedule.equals(once))
		{
			daysArray = new String[1];
			daysArray[0] = trips.get("days");
			tripTime = DateUtil.getDateFromTimeAndDateStrings(time, daysArray[0]);
		}
		else
		{
			daysArray =  trips.get("days").split(",");
//			tripTime = DateUtil.getDateFromTimeAndDateStringsForWeeklyTrip(time, daysArray[0]);
		}
		BigInteger uid = new BigInteger(trips.get("uid"));
		String type = trips.get("type");
		int fromId = Integer.parseInt(trips.get("from"));
		int toId = Integer.parseInt(trips.get("to"));
		int availableSeats = Integer.parseInt(trips.get("availableSeats"));
		boolean smokingAllowed = Boolean.valueOf(trips.get("isSmokking"));
		boolean friendsOnly = Boolean.valueOf(trips.get("isFriendsOnly"));
		boolean womenOnly = Boolean.valueOf(trips.get("isWomenOnly"));
		String status = Trip.OPEN;
		String comment = trips.get("comment");
		String communityId = trips.get("communityId");
		Trip trip = new Trip();
		trip.setUid(uid);
		trip.setType(type);
		trip.setToId(toId);
		trip.setFromId(fromId);
		trip.setAvailableSeats(availableSeats);
		trip.setTime(tripTime);
		trip.setSmokingAllowed(smokingAllowed);
		trip.setFriendsOnly(friendsOnly);
		trip.setWomenOnly(womenOnly);
		trip.setStatus(status);
		trip.setComment(comment);
		if(StringUtil.isNotEmpty(communityId)){
			trip.setCommunityId(Integer.parseInt(communityId));
		}
		if(tripSchedule.equals(once))
		{
			scheduledTrips.add(trip);
			return scheduledTrips;
		}
		else
		{
			for(int i = 0 ; i < daysArray.length ; i++ )
			{
				Trip newTrip = new Trip();
				newTrip.setUid(uid);
				newTrip.setType(type);
				newTrip.setToId(toId);
				newTrip.setFromId(fromId);
				newTrip.setAvailableSeats(availableSeats);
				newTrip.setSmokingAllowed(smokingAllowed);
				newTrip.setFriendsOnly(friendsOnly);
				newTrip.setWomenOnly(womenOnly);
				newTrip.setStatus(status);
				newTrip.setComment(comment);
				tripTime = DateUtil.getDateFromTimeAndDateStringsForWeeklyTrip(time, daysArray[i]);
				newTrip.setTime(tripTime);
				scheduledTrips.add(newTrip);
				for(int j = 1 ; j < 4 ; j ++)
				{
					newTrip = new Trip();
					newTrip.setUid(uid);
					newTrip.setType(type);
					newTrip.setToId(toId);
					newTrip.setFromId(fromId);
					newTrip.setAvailableSeats(availableSeats);
					newTrip.setSmokingAllowed(smokingAllowed);
					newTrip.setFriendsOnly(friendsOnly);
					newTrip.setWomenOnly(womenOnly);
					newTrip.setStatus(status);
					newTrip.setComment(comment);
					newTrip.setTime(DateUtil.getDateFromTimeAndDateStringsPlusWeeks(tripTime, j));
				   scheduledTrips.add(newTrip);
				}
			}
			return scheduledTrips;
		}
	}
}
