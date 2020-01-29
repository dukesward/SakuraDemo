package com.duke.sakura.demo.kingdom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.kingdom.annotation.SakuraDataSource;
import com.duke.sakura.demo.kingdom.data.DataSourceProvider;
import com.duke.sakura.demo.kingdom.model.UserProfile;
import com.duke.sakura.demo.kingdom.repository.UserProfileRepository;
import com.duke.sakura.demo.kingdom.service.DataSourceManager;
import com.duke.sakura.demo.kingdom.service.UserProfileService;
import com.duke.sakura.kingdom.exception.UserProfileException;

import static com.duke.sakura.demo.kingdom.ApplicationConstants.getConstant;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileRepository userProfileRepositoy;
	@Autowired
	DataSourceManager dataSourceManager;
	
	@SakuraDataSource(key="user_profile")
	public void getUserProfile() {
		String defaultUserName = getConstant("USER_PROFILE_DEFAULT");
		UserProfile user = this.userProfileRepositoy.findByName(defaultUserName);
		if(user == null) {
			throw new UserProfileException("User does not exist");
		}
		List<UserProfile> users = new ArrayList<>();
		this.dataSourceManager.collectData("user_profile", users);
	};
	
	@Override
	public UserProfile findUserProfileById(String userProfileId) {
		UserProfile userProfile = this.userProfileRepositoy.findByUuid(userProfileId);
		if(userProfile == null) {
			throw new UserProfileException("User does not exist");
		}
		return userProfile;
	}

	@Override
	public String getDataSourceId() {
		return "kingdom.user.profile";
	}

	@Override
	public void initUserProfile(String userProfileId) {
		String key = this.getDataSourceId() + ".userProfileId";
		DataSourceProvider.setInitData(key, userProfileId);
	};
}
