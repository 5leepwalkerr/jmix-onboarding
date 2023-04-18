package com.company.onboarding.screen.user;

import com.company.onboarding.entity.User;
import io.jmix.ui.component.Button;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;

@UiController("User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
public class UserBrowse extends StandardLookup<User> {
    @Subscribe("generateButton")
    public void onGenerateButtonClick(Button.ClickEvent event) {
        
    }
}