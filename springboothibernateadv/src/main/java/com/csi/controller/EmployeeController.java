package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("signup")

    public ResponseEntity<String> signUp(@RequestBody Employee employee) {

        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("Sign UP Done Succesfully");


    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")

    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {

        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")

    public ResponseEntity<Employee> getDataById(@PathVariable int empId) {

        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")

    public ResponseEntity<List<Employee>> getAllData() {

        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {

        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("Data Updated");
    }

    @DeleteMapping("/deletedatabyid/{empId}")

    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {

        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Done");
    }

    @DeleteMapping("/deletealladata")

    public ResponseEntity<String> deleteAllData() {

        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted");
    }

    @PostMapping("/savebulkofdata")

    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {

        employeeServiceImpl.saveBulkOfData(employees);

        return ResponseEntity.ok("All Data Saved");

    }

    @GetMapping("/getalldatabyusinganyinput/{input}")

    public ResponseEntity<List<Employee>> getDataByUsingAnyInput(@PathVariable String input) throws ParseException {

        return ResponseEntity.ok(employeeServiceImpl.getDataByUsingAnyInput(input));
    }

    @GetMapping("/getdatabyname/{empName}")

    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpName().equals(empName)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")

    public ResponseEntity<List<Employee>> getDataByContactNumber(@PathVariable long empContactNumber) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpContactNumber() == empContactNumber).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")


    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary() > empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyid")

    public ResponseEntity<List<Employee>> sortById() {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyname")

    public ResponseEntity<List<Employee>> sortByName() {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")

    public ResponseEntity<List<Employee>> getDataByEmailId(@PathVariable String empEmailId) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbydob")

    public ResponseEntity<List<Employee>> sortByDOB(){

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
 }

 @GetMapping("/getdatabyaddress/{empAddress}")

    public ResponseEntity<List<Employee>> getDataByAddress(@PathVariable String empAddress){

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpAddress().equals(empAddress)).collect(Collectors.toList()));
 }
}

