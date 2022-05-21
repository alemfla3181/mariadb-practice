desc employees;

-- findByFirstNameOrLastName

select a.emp_no, a.first_name, a.last_name, b.salary
 from employees a, salaries b
 where a.emp_no = b.emp_no
 and b.salary < 100000
  and b.salary > 50000
  and b.to_date = '9999-01-01'
 order by b.salary asc
 limit 0, 100;

