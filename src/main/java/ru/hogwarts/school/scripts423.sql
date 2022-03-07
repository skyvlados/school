SELECT student.name,student.age,faculty.name
FROM student
FULL JOIN faculty ON student.faculty_id=faculty.id;
SELECT student.name
FROM student
RIGHT OUTER JOIN avatar ON student.avatar_id=avatar.id;