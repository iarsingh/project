# NaukriSetu - Hyperlocal Government Job Bridge App

NaukriSetu is a comprehensive platform connecting individuals with local and state-level government job opportunities. The platform features district-wise job listings, AI-powered assistance, and offline capabilities.

## Tech Stack

### Backend
- Java Spring Boot
- PostgreSQL Database
- Spring Security
- Spring Data JPA
- Maven

### Frontend
- React.js
- Material-UI
- Redux for state management
- React Router for navigation
- Axios for API calls

## Features

1. **District-wise Job Board**
   - Location-based job filtering
   - Customizable job alerts
   - Nearby opportunities

2. **AI Career Assistant**
   - Multilingual chatbot support
   - Form filling assistance
   - Mock interview feedback

3. **Real-time Vacancy Tracker**
   - Automated job scraping
   - Instant notifications
   - Status tracking

4. **Document Manager**
   - Secure document storage
   - Application requirement checklist
   - Document verification status

5. **Offline Mode**
   - Local data caching
   - SMS-based applications
   - Sync management

6. **Referral System**
   - Friend referral tracking
   - Reward system
   - Social sharing

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- PostgreSQL 14 or higher
- Maven
- npm or yarn

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/naukrisetu.git
cd naukrisetu
```

2. Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

3. Frontend Setup
```bash
cd frontend
npm install
npm start
```

4. Database Setup
- Create a PostgreSQL database named 'naukrisetu'
- Update application.properties with your database credentials

## Project Structure

```
naukrisetu/
├── backend/                 # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/       # Java source files
│   │   │   └── resources/  # Configuration files
│   │   └── test/           # Test files
│   └── pom.xml             # Maven dependencies
├── frontend/               # React application
│   ├── src/
│   │   ├── components/     # React components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API services
│   │   └── utils/         # Utility functions
│   └── package.json       # npm dependencies
└── docs/                  # Documentation
```

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details. 