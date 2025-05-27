document.addEventListener('DOMContentLoaded', function() {
    const examList = document.getElementById('exam-list');
    let scrollInterval;
    function formatDate(dateString) {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const date = new Date(dateString);
        return date.toLocaleDateString(undefined, options);
    }

    // Utility function to format time
    function formatTime(timeString) {
        const [hours, minutes] = timeString.split(':');
        const date = new Date();
        date.setHours(hours);
        date.setMinutes(minutes);
        return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    }
    // Function to fetch exams registered by the applicant
function fetchExams() {
    fetch('http://localhost:8080/v1/questify/notice/exams', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')  // Replace with your actual token
        },
        body: JSON.stringify({}) // You can send some request data here if needed
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        populateExamList(data);
        startAutoScroll();
    })
    .catch(error => console.error('Error fetching exams:', error));
}

    // Function to populate the exam list with fetched data
    function populateExamList(exams) {
        examList.innerHTML = ''; // Clear any existing content

        exams.forEach(exam => {
            const listItem = document.createElement('li');
            listItem.textContent = `${exam.name} - Starts on ${formatDate(exam.exam_start_date)} at ${formatTime(exam.exam_start_time)}`;
            examList.appendChild(listItem);
        });
    }

    // Auto-scroll function for the exam list
    function startAutoScroll() {
        const scrollSpeed = 1; // Adjust speed of scrolling
        const scrollStep = 1; // Pixels per step

        scrollInterval = setInterval(function() {
            examList.scrollTop += scrollStep;

            // Reset to top when reaching the bottom of the scroll
            if (examList.scrollTop >= examList.scrollHeight - examList.clientHeight) {
                examList.scrollTop = 0;
            }
        }, scrollSpeed * 100); // Adjust the interval speed
    }

    // Stop scrolling when the mouse hovers over the list
    examList.addEventListener('mouseenter', function() {
        clearInterval(scrollInterval);
    });

    // Resume scrolling when the mouse leaves the list
    examList.addEventListener('mouseleave', function() {
        startAutoScroll();
    });

    // Fetch exams and initialize scrolling
    fetchExams();
});
