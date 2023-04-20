package com.company.onboarding.security;

import com.company.onboarding.entity.Departament;
import com.company.onboarding.entity.Step;
import com.company.onboarding.entity.User;
import com.company.onboarding.entity.UserStep;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import java.awt.desktop.UserSessionEvent;

@ResourceRole(name = "HR Manager", code = "hr-manager", scope = "UI")
public interface HRManagerRole {
    @MenuPolicy(menuIds = "User.browse")
    @ScreenPolicy(screenIds = {"User.browse", "User.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = Departament.class,
            attributes = "*",
            action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Departament.class,
            actions = EntityPolicyAction.READ)
    void department();

    @EntityAttributePolicy(entityClass = Step.class,
            attributes = "*",
            action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Step.class,
            actions = EntityPolicyAction.READ)
    void step();

    @EntityAttributePolicy(entityClass = User.class,
            attributes = "*",
            action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class,
            actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = UserStep.class,
            attributes = "*",
            action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = UserStep.class,
            actions = EntityPolicyAction.ALL)
    void userStep();
}
