
    // Sample data for the dashboard
    const userData = {
      name: "Alex",
      problemsSolved: 156,
      currentStreak: 18,
      accuracy: 78,
      topicsMastered: 24,
      totalTopics: 50,
      progress: {
        dataStructures: 65,
        algorithms: 42,
        problemSolving: 58
      },
      dailyChallenge: {
        title: "Two Sum Problem",
        description: "Given an array of integers, return indices of the two numbers that add up to a specific target.",
        category: "Arrays"
      },
      activities: [
        { type: "solved", title: "Reverse Linked List", time: "2 hours ago", icon: "check" },
        { type: "completed", title: "Binary Trees lesson", time: "Yesterday", icon: "book" },
        { type: "attempted", title: "Merge Intervals", time: "2 days ago", icon: "redo" },
        { type: "started", title: "Graph Algorithms", time: "3 days ago", icon: "play" }
      ],
      quotes: [
        "The best way to learn data structures is to implement them from scratch. The best way to learn algorithms is to solve problems with them.",
        "Bad programmers worry about the code. Good programmers worry about data structures and their relationships.",
        "First solve the problem, then write the code.",
        "The only way to learn a new programming language is by writing programs in it.",
        "Measuring programming progress by lines of code is like measuring aircraft building progress by weight."
      ]
    };

    // DOM elements
    const sidebar = document.getElementById('sidebar');
    const mainContent = document.getElementById('mainContent');
    const sidebarToggle = document.getElementById('sidebarToggle');
    const themeToggle = document.getElementById('themeToggle');
    const quoteBox = document.getElementById('quoteBox');
    const quoteText = document.getElementById('quoteText');
    const recentActivity = document.getElementById('recentActivity');

    // Initialize the dashboard
    document.addEventListener('DOMContentLoaded', function() {
      // Set user data
      document.getElementById('username').textContent = userData.name;
      document.getElementById('problemsSolved').textContent = userData.problemsSolved;
      document.getElementById('currentStreak').textContent = userData.currentStreak + ' Days';
      document.getElementById('accuracyRate').textContent = userData.accuracy + '%';
      document.getElementById('topicsMastered').textContent = userData.topicsMastered + '/' + userData.totalTopics;
      
      // Set progress bars
      document.getElementById('dsProgress').style.width = userData.progress.dataStructures + '%';
      document.getElementById('dsProgressText').textContent = userData.progress.dataStructures + '%';
      document.getElementById('algoProgress').style.width = userData.progress.algorithms + '%';
      document.getElementById('algoProgressText').textContent = userData.progress.algorithms + '%';
      document.getElementById('psProgress').style.width = userData.progress.problemSolving + '%';
      document.getElementById('psProgressText').textContent = userData.progress.problemSolving + '%';
      
      // Set daily challenge
      document.getElementById('dailyChallengeTitle').textContent = userData.dailyChallenge.title;
      document.getElementById('dailyChallengeDesc').textContent = userData.dailyChallenge.description;
      
      // Set initial quote
      quoteText.textContent = `"${userData.quotes[0]}"`;
      
      // Populate recent activity
      renderActivities();
      
      // Animate stats cards
      animateStatsCards();
      
      // Set current date in welcome message
      setDailyQuote();
    });

    // Toggle sidebar
    sidebarToggle.addEventListener('click', function() {
      sidebar.classList.toggle('sidebar-collapsed');
      mainContent.classList.toggle('main-content-expanded');
      sidebarToggle.innerHTML = sidebar.classList.contains('sidebar-collapsed') ? 
        '<i class="fas fa-bars"></i>' : '<i class="fas fa-times"></i>';
    });

    // Toggle dark mode
    themeToggle.addEventListener('click', function() {
      document.body.classList.toggle('dark-mode');
      themeToggle.innerHTML = document.body.classList.contains('dark-mode') ? 
        '<i class="fas fa-sun"></i>' : '<i class="fas fa-moon"></i>';
    });

    // Change quote on click
    function changeQuote() {
      const randomIndex = Math.floor(Math.random() * userData.quotes.length);
      quoteText.textContent = `"${userData.quotes[randomIndex]}"`;
      quoteBox.style.background = `linear-gradient(135deg, ${getRandomColor()}, ${getRandomColor()})`;
    }

    // Set daily motivational quote
    function setDailyQuote() {
      const quotes = [
        "The expert in anything was once a beginner.",
        "Consistency is the key to mastery.",
        "Every problem is an opportunity to learn.",
        "Today's effort is tomorrow's success.",
        "Small progress is still progress."
      ];
      const today = new Date().getDay();
      document.getElementById('dailyQuote').textContent = 
        `"${quotes[today % quotes.length]}" - Let's tackle some DSA today!`;
    }

    // Start daily challenge
    function startDailyChallenge() {
      alert(`Starting challenge: ${userData.dailyChallenge.title}\n\n${userData.dailyChallenge.description}`);
      // In a real app, this would redirect to the challenge page
    }

    // Open topic
    function openTopic(topicName) {
      alert(`Opening topic: ${topicName}`);
      // In a real app, this would redirect to the topic page
    }

    // Render activities
    function renderActivities() {
      recentActivity.innerHTML = '';
      userData.activities.forEach(activity => {
        const activityItem = document.createElement('div');
        activityItem.className = 'activity-item';
        
        let iconClass, bgColor;
        switch(activity.type) {
          case 'solved':
            iconClass = 'check';
            bgColor = 'success';
            break;
          case 'completed':
            iconClass = 'book';
            bgColor = 'info';
            break;
          case 'attempted':
            iconClass = 'redo';
            bgColor = 'warning';
            break;
          case 'started':
            iconClass = 'play';
            bgColor = 'primary';
            break;
        }
        
        activityItem.innerHTML = `
          <div class="d-flex align-items-center">
            <div class="avatar bg-${bgColor} bg-opacity-10 text-${bgColor} me-3">
              <i class="fas fa-${iconClass}"></i>
            </div>
            <div>
              <h6 class="mb-0 fw-bold">${activity.type === 'solved' ? 'Solved' : activity.type === 'attempted' ? 'Attempted' : activity.type === 'completed' ? 'Completed' : 'Started'} "${activity.title}"</h6>
              <small class="text-muted">${activity.time}</small>
            </div>
          </div>
        `;
        recentActivity.appendChild(activityItem);
      });
    }
 
    // Animate stats cards
    function animateStatsCards() {
      const statsCards = [
        document.getElementById('statsCard1'),
        document.getElementById('statsCard2'),
        document.getElementById('statsCard3'),
        document.getElementById('statsCard4')
      ];
      
      statsCards.forEach((card, index) => {
        setTimeout(() => {
          card.classList.add('animated');
        }, index * 150);
      });
    }

    // Helper function to generate random color
    function getRandomColor() {
      const colors = [
        '#5f72be', '#9b23ea', '#28a745', '#ffc107', '#dc3545', 
        '#17a2b8', '#6610f2', '#6f42c1', '#e83e8c', '#fd7e14'
      ];
      return colors[Math.floor(Math.random() * colors.length)];
    }
  