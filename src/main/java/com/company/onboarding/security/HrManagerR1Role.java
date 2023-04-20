package com.company.onboarding.security;

import com.company.onboarding.entity.Departament;
import com.company.onboarding.entity.User;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(
        name = "HR manager's departments and users",
        code = "hr-manager-r1"
)
public interface HrManagerR1Role {

    @JpqlRowLevelPolicy(
            entityClass = Departament.class,
            where = "{E}.hrManager.id = :current_user_id")
    void department();

    @JpqlRowLevelPolicy(
            entityClass = User.class,
            where = "{E}.departament.hrManager.id = :current_user_id")
    void user();
}
