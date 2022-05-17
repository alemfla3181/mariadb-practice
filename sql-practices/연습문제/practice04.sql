-- 서브쿼리(혼합) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries 
where to_date = '9999-01-01'
and salary > (select avg(salary) from salaries where to_date='9999-01-01');

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select a.emp_no, concat(a.first_name,' ',a.last_name), c.salary
from employees a, dept_emp b, salaries c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and (b.dept_no, c.salary) in (select a.dept_no, max(b.salary)	
								from dept_emp a, salaries b                            
								where a.emp_no=b.emp_no
								and a.to_date='9999-01-01'
								and b.to_date='9999-01-01'
								group by a.dept_no)
order by c.salary desc;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.emp_no, concat(a.first_name,' ',a.last_name), c.salary
from employees a, dept_emp b, salaries c, (select a.dept_no, avg(b.salary) as avg_salary
								from dept_emp a, salaries b                            
								where a.emp_no=b.emp_no
								and a.to_date='9999-01-01'
								and b.to_date='9999-01-01'
								group by a.dept_no) d
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and b.dept_no = d.dept_no
and c.salary > d.avg_salary
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01';
  
-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no, concat(a.first_name,' ',a.last_name), concat(e.first_name, ' ',e.last_name) , c.dept_name
from employees a, dept_emp b, departments c, dept_manager d, employees e
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and c.dept_no = d.dept_no
and d.emp_no = e.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01';

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select a.emp_no, concat(a.first_name, ' ',a.last_name), d.title, c.salary
from employees a, dept_emp b, salaries c, titles d
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and c.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and b.dept_no =  (Select dept_no from
 (select avg(a.salary) as avg_salary, b.dept_no
								from salaries a, dept_emp b
								where a.emp_no = b.emp_no
								and a.to_date = '9999-01-01'
								and b.to_date = '9999-01-01'
								group by b.dept_no
                                order by avg_salary desc limit 0,1) a)
order by c.salary desc;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
select d.dept_name, ROUND(AVG(b.salary)) AS avg_salary
from    employees a, salaries b, dept_emp c, departments d
where a.emp_no = b.emp_no
and a.emp_no = c.emp_no
and c.dept_no = d.dept_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
group by c.dept_no
having avg_salary = (select max(avg_salary) from
	(select round(avg(b.salary))  as avg_salary
	from dept_emp a, salaries b
	where a.emp_no = b.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
	group by a.dept_no) a);
    
-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select a.title, avg(b.salary)
from titles a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
having avg(b.salary)
order by avg(b.salary) ;

SELECT 
    c.title, ROUND(AVG(b.salary)) AS avg_salary
FROM
    employees a,
    salaries b,
    titles c
WHERE
    a.emp_no = b.emp_no
        AND a.emp_no = c.emp_no
        AND b.to_date = '9999-01-01'
        AND c.to_date = '9999-01-01'
GROUP BY c.title
HAVING avg_salary = (SELECT 
        MAX(avg_salary)
    FROM
        (SELECT 
            ROUND(AVG(b.salary)) AS avg_salary
        FROM
            employees a, salaries b, titles c
        WHERE
            a.emp_no = b.emp_no
                AND a.emp_no = c.emp_no
                AND b.to_date = '9999-01-01'
                AND c.to_date = '9999-01-01'
        GROUP BY c.title) a);

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select f.dept_name , a.first_name , d.salary, g.first_name , e.salary 
from employees a, dept_emp b, dept_manager c, salaries d, salaries e, departments f, employees g
where a.emp_no = b.emp_no
and c.dept_no = b.dept_no
and a.emp_no = d.emp_no
and c.emp_no = e.emp_no
and c.dept_no = f.dept_no
and c.emp_no = g.emp_no
and b.to_date = '9999-01-01'
and c.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and e.to_date = '9999-01-01'
and d.salary > e.salary;
