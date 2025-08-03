import React from 'react';

const CourseDetails = ({ courses }) => {
  return (
    <div>
      <h2>🎓 Course Details</h2>
      <ul>
        {courses.map((course) => (
          <li key={course.id}>
            {course.name} - {course.instructor}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CourseDetails;
