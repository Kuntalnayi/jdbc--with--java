CREATE PROCEDURE getEmpName(IN empId INT, OUT empName VARCHAR(50))
BEGIN
  SELECT empname INTO empName FROM emp_detail WHERE empid = empId;
END;
