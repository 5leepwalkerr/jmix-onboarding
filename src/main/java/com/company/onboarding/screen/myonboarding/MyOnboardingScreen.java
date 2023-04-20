package com.company.onboarding.screen.myonboarding;

import com.company.onboarding.entity.User;
import com.company.onboarding.entity.UserStep;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.Label;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@UiController("MyOnboardingScreen")
@UiDescriptor("my-onboarding-screen.xml")
public class MyOnboardingScreen extends Screen {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CollectionLoader<UserStep> userStepsD1;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Label<String> totalStepsLabel;
    @Autowired
    private Label<String> completedStepsLabel;
    @Autowired
    private Label<String> overdueStepsLabel;
    @Autowired
    private CollectionContainer<UserStep> userStepDc;
    @Autowired
    private DataContext dataContext;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event){
        User user = (User) currentAuthentication.getUser();
        userStepsD1.setParameter("user",user);
        userStepsD1.load();
        updateLabels();
    }

    @Install(to = "userStepsTable.completedDate",subject = "columnGenerator")
    private Component userStepsTableCompletedColumnGenerator(UserStep userStep){
        CheckBox checkBox = uiComponents.create(CheckBox.class);
        checkBox.setValue(userStep.getCompletedDate()!=null);
        checkBox.addValueChangeListener(e ->{
            if(userStep.getCompletedDate()==null){
                userStep.setCompletedDate(LocalDate.now());
            }
            else userStep.setCompletedDate(null);
        });
        return (Component) checkBox;
    }

    private void updateLabels() {
        totalStepsLabel.setValue("Total steps: " + userStepDc.getItems().size());

        long completedCount = userStepDc.getItems().stream()
                .filter(us -> us.getCompletedDate() != null)
                .count();
        completedStepsLabel.setValue("Completed steps: " + completedCount);

        long overdueCount = userStepDc.getItems().stream()
                .filter(us -> isOverdue(us))
                .count();
        overdueStepsLabel.setValue("Overdue steps: " + overdueCount);
    }

    private boolean isOverdue(UserStep us) {
        return us.getCompletedDate() == null
                && us.getDueDate() != null
                && us.getDueDate().isBefore(LocalDate.now());
    }
    @Subscribe(id = "userStepDc", target = Target.DATA_CONTAINER)
    public void onUserStepDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<UserStep> event) {
        updateLabels();
    }

    @Subscribe("saveButton")
    public void onSaveButton(Button.ClickEvent event){
        dataContext.commit();
        close(StandardOutcome.COMMIT);
    }

    @Subscribe("discardButton")
    public void onDiscardButton(Button.ClickEvent event){
        close(StandardOutcome.DISCARD);
    }

    @Install(to = "UserStepsTable",subject = "styleProvider")
    private String userStepsTableStyleProvider(UserStep entity,String property){
        if("dueDate".equals(property) && isOverdue(entity)) return "overdue-step";
        return null;
    }
}