import React from 'react';

const BlogDetails = ({ blogs }) => {
  return (
    <div>
      <h2>📝 Blog Details</h2>
      {blogs.length > 0 ? (
        <ul>
          {blogs.map((blog) => (
            <li key={blog.id}>
              {blog.title} by {blog.writer}
            </li>
          ))}
        </ul>
      ) : (
        <p>No Blogs Available.</p>
      )}
    </div>
  );
};

export default BlogDetails;
