import React, { Component } from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showBooks: true,
      showBlogs: true,
      showCourses: true,
      books: [
        { id: 1, title: 'React Explained', author: 'Zac Gordon' },
        { id: 2, title: 'Learning JavaScript', author: 'Ethan Brown' },
      ],
      blogs: [
        { id: 1, title: 'React Basics', writer: 'Jane Doe' },
        { id: 2, title: 'Advanced JSX', writer: 'John Smith' },
      ],
      courses: [
        { id: 1, name: 'React Fundamentals', instructor: 'Alex' },
        { id: 2, name: 'React Hooks Deep Dive', instructor: 'Sara' },
      ],
    };
  }

  toggleBooks = () => this.setState({ showBooks: !this.state.showBooks });
  toggleBlogs = () => this.setState({ showBlogs: !this.state.showBlogs });
  toggleCourses = () => this.setState({ showCourses: !this.state.showCourses });

  render() {
    // ✔️ 1. Using element variable
    let bookSection;
    if (this.state.showBooks) {
      bookSection = <BookDetails books={this.state.books} />;
    }

    return (
      <div style={{ padding: '20px' }}>
        <h1>📖 Blogger App</h1>

        <button onClick={this.toggleBooks}>
          {this.state.showBooks ? 'Hide Books' : 'Show Books'}
        </button>
        <button onClick={this.toggleBlogs}>
          {this.state.showBlogs ? 'Hide Blogs' : 'Show Blogs'}
        </button>
        <button onClick={this.toggleCourses}>
          {this.state.showCourses ? 'Hide Courses' : 'Show Courses'}
        </button>

        {/* ✔️ 1. Element Variable */}
        {bookSection}

        {/* ✔️ 2. Ternary Operator */}
        {this.state.showBlogs ? (
          <BlogDetails blogs={this.state.blogs} />
        ) : (
          <p>Blog Section Hidden</p>
        )}

        {/* ✔️ 3. && Short Circuit */}
        {this.state.showCourses && (
          <CourseDetails courses={this.state.courses} />
        )}
      </div>
    );
  }
}

export default App;
