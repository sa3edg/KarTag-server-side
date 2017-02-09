package com.kartag.server.reporting;

import com.kartag.business.common.Request;
import com.kartag.common.util.DateUtil;
import com.kartag.server.dao.UserDAO;
import com.kartag.server.dao.UserTripsDAO;
import com.kartag.server.model.Report;
import com.kartag.server.model.Trip;
import com.kartag.server.presentation.form.ReportingForm;

public class Reporting {

	public static Report report(ReportingForm actionForm) throws Exception {
		Report report = new Report();
		if (actionForm.getCountryId() == 0) {
			return report;
		}
		int registeredUser = 0;
		int iphoneUsers = 0;
		int androidUsers = 0;
		UserDAO userDao = new UserDAO();
		UserTripsDAO tripsDao = new UserTripsDAO();
		if ("01-01-2013".equals(actionForm.getStartDate())
				&& actionForm.getEndDate().equals(DateUtil.getNowAsString())) {
			registeredUser = userDao.getAllRegisteredUser(actionForm
					.getCountryId());
			iphoneUsers = userDao.getAllDevicesUser(actionForm.getCountryId(),
					Request.SOURCE_DEVICE_IPHONE);
			androidUsers = userDao.getAllDevicesUser(actionForm.getCountryId(),
					Request.SOURCE_DEVICE_ANDROID);
		} else {
			registeredUser = userDao.getFilteredRegisteredUser(
					actionForm.getCountryId(), actionForm.getStartDate(),
					actionForm.getEndDate());
			iphoneUsers = userDao.getFilteredDeviceUser(
					actionForm.getCountryId(), actionForm.getStartDate(),
					actionForm.getEndDate(), Request.SOURCE_DEVICE_IPHONE);
			androidUsers = userDao.getFilteredDeviceUser(
					actionForm.getCountryId(), actionForm.getStartDate(),
					actionForm.getEndDate(), Request.SOURCE_DEVICE_ANDROID);
		}
		report.setTripsCount(tripsDao.getTripsCount(actionForm.getCountryId(),
				actionForm.getPoolId(), actionForm.getStartDate(),
				actionForm.getEndDate()));
		report.setNeedArideRequested(tripsDao.getTripsCountByType(Trip.REQUEST,
				actionForm.getCountryId(), actionForm.getPoolId(),
				actionForm.getStartDate(), actionForm.getEndDate()));
		report.setGiveArideRequested(tripsDao.getTripsCountByType(Trip.OFFER,
				actionForm.getCountryId(), actionForm.getPoolId(),
				actionForm.getStartDate(), actionForm.getEndDate()));
		report.setAcceptedTrips(tripsDao.getTripsCountByStatus(Trip.ACCEPTED,
				actionForm.getCountryId(), actionForm.getPoolId(),
				actionForm.getStartDate(), actionForm.getEndDate()));
		report.setRejectedTrips(tripsDao.getTripsCountByStatus(Trip.REJECTED,
				actionForm.getCountryId(), actionForm.getPoolId(),
				actionForm.getStartDate(), actionForm.getEndDate()));
		if (actionForm.getCommunityId() != 0) {
			report.setTripsCount(tripsDao.getCommunityTripsCount(
					actionForm.getCountryId(), actionForm.getCommunityId(),
					actionForm.getStartDate(), actionForm.getEndDate()));
			report.setNeedArideRequested(tripsDao.getCommunityTripsCountByType(
					Trip.REQUEST, actionForm.getCountryId(),
					actionForm.getCommunityId(), actionForm.getStartDate(),
					actionForm.getEndDate()));
			report.setGiveArideRequested(tripsDao.getCommunityTripsCountByType(
					Trip.OFFER, actionForm.getCountryId(),
					actionForm.getCommunityId(), actionForm.getStartDate(),
					actionForm.getEndDate()));
			report.setAcceptedTrips(tripsDao.getCommunityTripsCountByStatus(
					Trip.ACCEPTED, actionForm.getCountryId(),
					actionForm.getCommunityId(), actionForm.getStartDate(),
					actionForm.getEndDate()));
			report.setRejectedTrips(tripsDao.getCommunityTripsCountByStatus(
					Trip.REJECTED, actionForm.getCountryId(),
					actionForm.getCommunityId(), actionForm.getStartDate(),
					actionForm.getEndDate()));
		}
		report.setRegisteredUsers(registeredUser);
		report.setIphoneUsers(iphoneUsers);
		report.setAndroidUsers(androidUsers);
		return report;
	}
}
