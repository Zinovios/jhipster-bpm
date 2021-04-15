package fr.ippon.jhipster.bpm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fr.ippon.jhipster.bpm");

        noClasses()
            .that()
                .resideInAnyPackage("fr.ippon.jhipster.bpm.service..")
            .or()
                .resideInAnyPackage("fr.ippon.jhipster.bpm.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..fr.ippon.jhipster.bpm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
