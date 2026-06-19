package br.com.henriquesousa.diversa21.setup;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.henriquesousa.diversa21.entity.Privilege;
import br.com.henriquesousa.diversa21.entity.Role;
import br.com.henriquesousa.diversa21.repository.PrivilegeRepository;
import br.com.henriquesousa.diversa21.repository.RoleRepository;

@Component
public class SetupDataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. Create Privileges
        Privilege createAnyUserPrivilege = createPrivilegeIfNotFound("CREATE_ANY_USER_PRIVILEGE");
        Privilege createMemberPrivilege = createPrivilegeIfNotFound("CREATE_MEMBER_PRIVILEGE");
        Privilege manageUser = createPrivilegeIfNotFound("MANAGE_USER_PRIVILEGE");
        Privilege changeOwnInstitution = createPrivilegeIfNotFound("CHANGE_OWN_INSTITUTION_PRIVILEGE");
        Privilege createInstitutions = createPrivilegeIfNotFound("CREATE_INSTITUTIONS_PRIVILEGE");
        Privilege manageInstitution = createPrivilegeIfNotFound("MANAGE_INSTITUTION_PRIVILEGE");
        Privilege managePosts = createPrivilegeIfNotFound("MANAGE_POSTS_PRIVILEGE");
        Privilege manageLocations = createPrivilegeIfNotFound("MANAGE_LOCATIONS_PRIVILEGE");

        // 2. Create Roles and assign Privileges
        List<Privilege> superAdminPrivileges = Arrays.asList(
                createAnyUserPrivilege,
                createMemberPrivilege,
                manageUser,
                changeOwnInstitution,
                createInstitutions,
                manageInstitution,
                manageLocations);
        Role superAdminRole = createRoleIfNotFound("ROLE_SUPER_ADMIN", superAdminPrivileges);

        List<Privilege> managerPrivileges = Arrays.asList(
                createMemberPrivilege,
                manageUser,
                manageInstitution
                );
        Role managementRole = createRoleIfNotFound("ROLE_MANAGEMENT", managerPrivileges);

        List<Privilege> reviewPrivileges = Arrays.asList(managePosts);
        Role reviewRole = createRoleIfNotFound("ROLE_REVIEW", reviewPrivileges);

        List<Privilege> memberPrivileges = Arrays.asList();
        Role memberRole = createRoleIfNotFound("ROLE_MEMBER", memberPrivileges);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
        return privilegeRepository.findByName(name).orElseGet(() -> {
            Privilege privilege = new Privilege();
            privilege.setName(name);
            return privilegeRepository.save(privilege);
        });
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            return roleRepository.save(role);
        });
    }
}
