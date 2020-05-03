package com.gm.songbook;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.gm.songbook");

        noClasses()
            .that()
            .resideInAnyPackage("com.gm.songbook.service..")
            .or()
            .resideInAnyPackage("com.gm.songbook.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.gm.songbook.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
