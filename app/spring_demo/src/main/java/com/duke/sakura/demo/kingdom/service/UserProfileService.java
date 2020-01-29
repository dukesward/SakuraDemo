package com.duke.sakura.demo.kingdom.service;

import com.duke.sakura.demo.kingdom.model.UserProfile;
import com.duke.sakura.demo.service.SakuraDataService;

public interface UserProfileService extends SakuraDataService {
	
	public void getUserProfile();
	
	public void initUserProfile(String userProfileId);
	
	public UserProfile findUserProfileById(String userProfileId);
}
