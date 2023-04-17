package com.company.onboarding.screen.departament;

import io.jmix.ui.screen.*;
import com.company.onboarding.entity.Departament;

@UiController("Departament.edit")
@UiDescriptor("departament-edit.xml")
@EditedEntityContainer("departamentDc")
public class DepartamentEdit extends StandardEditor<Departament> {
}