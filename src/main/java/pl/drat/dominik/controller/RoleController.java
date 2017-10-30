package pl.drat.dominik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.drat.dominik.dao.entity.Role;
import pl.drat.dominik.service.RoleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;


    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
        Role role = roleService.getRole(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role newRole = roleService.createRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable("id") Long id) {
        Role updatedRole = roleService.updateRole(id, role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
