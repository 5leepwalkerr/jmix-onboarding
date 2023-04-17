package com.company.onboarding.screen.departament;

import io.jmix.ui.screen.*;
import com.company.onboarding.entity.Departament;

@UiController("Departament.browse")
@UiDescriptor("departament-browse.xml")
@LookupComponent("departamentsTable")
public class DepartamentBrowse extends StandardLookup<Departament> {
}