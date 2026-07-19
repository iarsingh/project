# NaukriSetu — Frontend

React + TypeScript client for NaukriSetu, the hyperlocal government job bridge platform. Talks to the Spring Boot API in [`../backend`](../backend).

## Tech Stack

- **React 18** with **TypeScript** (bootstrapped with Create React App / `react-scripts`)
- **Material-UI (MUI)** for components and theming
- **Redux Toolkit** (`@reduxjs/toolkit`) for state management
- **React Router v6** for navigation
- **TanStack Query** for server-state/data fetching
- **Axios** for HTTP calls to the backend API
- **Formik + Yup** for form handling and validation

## Folder Structure

```
frontend/
├── public/
├── src/
│   ├── components/
│   │   ├── Layout.tsx          # App shell/layout wrapper
│   │   └── layout/Navbar.tsx   # Top navigation bar
│   ├── hooks/
│   │   └── useAuth.tsx         # Auth context/provider and hook
│   ├── pages/
│   │   ├── Home.tsx
│   │   ├── Login.tsx
│   │   ├── Register.tsx
│   │   ├── Profile.tsx
│   │   ├── Documents.tsx
│   │   ├── Jobs.tsx
│   │   └── NotFound.tsx
│   ├── App.tsx                 # Routes and top-level providers (Theme, Auth, Router)
│   └── index.tsx                # Entry point
└── package.json
```

## Getting Started

### Prerequisites
- Node.js 16+ and npm
- The backend API running (see [`../backend/README.md`](../backend/README.md)) — the app expects it at the URL configured for Axios/CORS (default `http://localhost:8080`, with the client on `http://localhost:3000`)

### Install
```bash
npm install
```

### Run in development
```bash
npm start
```
Opens the app at [http://localhost:3000](http://localhost:3000) with hot reload.

### Run tests
```bash
npm test
```

### Build for production
```bash
npm run build
```
Outputs an optimized build to the `build/` folder.

## Routes

| Path | Page |
|---|---|
| `/` | Home |
| `/login` | Login |
| `/register` | Register |
| `/profile` | Profile |
| `/documents` | Documents |
| `/jobs` | Jobs |
| `*` | NotFound |
