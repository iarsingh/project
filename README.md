# NaukriSetu - Hyperlocal Government Job Bridge App

<!-- project-guide:start -->
## Project guide

[Project architecture](PROJECT_ARCHITECTURE.md) · [Interview questions and answers](INTERVIEW_QA.md)

Use the architecture document for the component diagram, implementation boundaries, and verification entry points. The interview guide includes source-backed answers and project walkthroughs.

### Implementation map

| Component | Responsibility |
| --- | --- |
| [`frontend/package.json`](frontend/package.json) | User interface code/assets |
| [`backend/src/main/java/com/naukrisetu/controller/AuthController.java`](backend/src/main/java/com/naukrisetu/controller/AuthController.java) | HTTP request handling |
| [`backend/src/main/java/com/naukrisetu/controller/DistrictController.java`](backend/src/main/java/com/naukrisetu/controller/DistrictController.java) | HTTP request handling |
| [`backend/src/main/java/com/naukrisetu/controller/DocumentController.java`](backend/src/main/java/com/naukrisetu/controller/DocumentController.java) | HTTP request handling |
| [`backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java`](backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java) | HTTP request handling |
| [`backend/src/main/java/com/naukrisetu/controller/JobController.java`](backend/src/main/java/com/naukrisetu/controller/JobController.java) | HTTP request handling |
| [`frontend/src/App.tsx`](frontend/src/App.tsx) | User interface code/assets |
| [`frontend/src/index.tsx`](frontend/src/index.tsx) | User interface code/assets |
| [`frontend/src/react-app-env.d.ts`](frontend/src/react-app-env.d.ts) | User interface code/assets |
| [`frontend/src/reportWebVitals.ts`](frontend/src/reportWebVitals.ts) | User interface code/assets |
| [`frontend/src/setupTests.ts`](frontend/src/setupTests.ts) | User interface code/assets |
| [`frontend/src/components/Layout.tsx`](frontend/src/components/Layout.tsx) | User interface code/assets |
| [`frontend/src/hooks/useAuth.tsx`](frontend/src/hooks/useAuth.tsx) | User interface code/assets |
| [`frontend/src/pages/Documents.tsx`](frontend/src/pages/Documents.tsx) | User interface code/assets |
| [`backend/pom.xml`](backend/pom.xml) | Implementation or supporting configuration |
| [`frontend/src/App.test.tsx`](frontend/src/App.test.tsx) | Executable checks and regression examples |
| [`README.md`](README.md) | Project explanations or operating notes |
| [`backend/README.md`](backend/README.md) | Project explanations or operating notes |
| [`frontend/README.md`](frontend/README.md) | User interface code/assets |

### Local setup and verification

From the repository root (the commands follow the checked-in manifests):

```bash
cd frontend
npm install
npm test
npm run start
```

<!-- project-guide:end -->

<!-- repository-summary -->
A Spring Boot and React platform connecting users with hyperlocal government job opportunities and AI-powered assistance.
<!-- /repository-summary -->

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
git clone https://github.com/iarsingh/project.git naukrisetu
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
├── backend/                          # Spring Boot application (see backend/README.md)
│   ├── src/main/java/com/naukrisetu/
│   │   ├── controller/               # REST endpoints (auth, jobs, districts, documents, referrals, statistics, users)
│   │   ├── model/                    # JPA entities (User, Job, District, Document, Referral, ChatSession, ...)
│   │   ├── repository/                # Spring Data JPA repositories
│   │   ├── security/                  # JWT auth filter/provider, UserDetails implementations
│   │   └── config/                    # Security & app configuration
│   ├── src/main/resources/           # application.properties, static config
│   └── pom.xml                        # Maven dependencies
└── frontend/                          # React + TypeScript application (see frontend/README.md)
    ├── src/
    │   ├── components/                # Shared UI (Layout, Navbar, ...)
    │   ├── hooks/                     # useAuth and other hooks
    │   └── pages/                     # Home, Login, Register, Profile, Documents, Jobs, NotFound
    └── package.json                   # npm dependencies
```

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details. 

## Documentation checks

Project architecture, interview guides, and local source links are checked automatically on pushes and pull requests. Run the same check locally:

```bash
python3 .github/scripts/validate_project_docs.py
```
