package com.duke.sakura.demo.kingdom.sript;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duke.sakura.demo.kingdom.service.CharacterService;
import com.duke.sakura.demo.kingdom.service.UserProfileConditionService;
import com.duke.sakura.demo.kingdom.service.UserProfileService;
import com.duke.sakura.kingdom.exception.ConditionScriptException;

@Service
public class UserProfileConditionScripts implements UserProfileConditionService {
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private CharacterService characterService;

	@Override
	public boolean testCondition(String userProfileId, int conditionId) {
		// System.out.println("test condition for script id = " + conditionId);
		Method method;
		try {
			method = this.getClass().getMethod("script" + conditionId, String.class);
			if(method != null) {
				return (boolean) method.invoke(this, userProfileId);
			}
			return false;
		}catch (NoSuchMethodException nme) {
			throw new ConditionScriptException(String.format("Script %d does not exist", conditionId));
		}catch (IllegalArgumentException iae) {
			throw new ConditionScriptException("Script invoked by invalid user profile id");
		}catch (Exception e) {
			throw new ConditionScriptException(e.toString());
		}
	}
	
	public boolean script76(String userProfileId) {
		return this.characterService.findMainCharacter(userProfileId).getLevel() <= 1;
	}
}
