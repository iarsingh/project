# project — interview questions and answers

[README](README.md) · [Project architecture](PROJECT_ARCHITECTURE.md)

Answers below use this repository’s files and implementation. They distinguish existing behavior from suggested extensions; source links let you verify each walkthrough.

## 1. What problem does project address, and what can you demonstrate?

A Spring Boot and React platform connecting users with hyperlocal government job opportunities and AI-powered assistance.

I would demonstrate the linked implementation or examples and distinguish that evidence from any planned production features. Start with [`README.md`](README.md).

## 2. How is this repository organized?

- [`frontend/package.json`](frontend/package.json): User interface code/assets.
- [`backend/src/main/java/com/naukrisetu/controller/AuthController.java`](backend/src/main/java/com/naukrisetu/controller/AuthController.java): HTTP request handling.
- [`backend/src/main/java/com/naukrisetu/controller/DistrictController.java`](backend/src/main/java/com/naukrisetu/controller/DistrictController.java): HTTP request handling.
- [`backend/src/main/java/com/naukrisetu/controller/DocumentController.java`](backend/src/main/java/com/naukrisetu/controller/DocumentController.java): HTTP request handling.
- [`backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java`](backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java): HTTP request handling.
- [`backend/src/main/java/com/naukrisetu/controller/JobController.java`](backend/src/main/java/com/naukrisetu/controller/JobController.java): HTTP request handling.
- [`frontend/src/App.tsx`](frontend/src/App.tsx): User interface code/assets.
- [`frontend/src/index.tsx`](frontend/src/index.tsx): User interface code/assets.

[PROJECT_ARCHITECTURE.md](PROJECT_ARCHITECTURE.md) contains the component diagram and the implementation walkthrough.

## 3. How does the JavaScript application start or build?

The commands are defined in [`frontend/package.json`](frontend/package.json):

- `start`: `react-scripts start`.
- `build`: `react-scripts build`.
- `test`: `react-scripts test`.
- `eject`: `react-scripts eject`.

I would run them from that manifest directory and inspect environment configuration before assuming a service is ready.

## 4. What would you verify before extending this repository?

I would identify an executable example or define a concrete acceptance case for the material in [`README.md`](README.md). For code, verify inputs, outputs, and failure handling; for notes or templates, verify that a reader can follow the procedure and distinguish examples from measured results.

## 5. Which files provide verification evidence?

The repository includes [`frontend/src/App.test.tsx`](frontend/src/App.test.tsx). I would explain the scenarios and assertions in these files, then run the matching test command from the appropriate project directory. File presence alone is not a passing test result.

## 6. Which runtime dependencies shape the application?

The dependency manifest [`frontend/package.json`](frontend/package.json) declares `@emotion/react`, `@emotion/styled`, `@mui/icons-material`, `@mui/material`, `@reduxjs/toolkit`, `@tanstack/react-query`, `@testing-library/jest-dom`, `@testing-library/react`, `@testing-library/user-event`, `@types/jest`, `@types/node`, `@types/react`. I would trace where each relevant package is imported before assigning it a role in the architecture.

## 7. How would you investigate data ownership and persistence?

Trace the data/configuration files and the code that reads or writes them in the component table. Identify which files are examples, which records are mutable, and which external store is actually configured. I would document those facts before discussing retention, backup, or tenant isolation.

## 8. How would another engineer reproduce your walkthrough?

Start from the repository root:

```bash
cd frontend
npm install
npm test
npm run start
```

These commands follow repository manifests; environment setup and command results still need to be checked on the target machine.

## 9. How would you add CI without confusing it with deployment?

First automate the repository-specific checks above, including documentation link validation. Add deployment only after defining the target environment, required credentials, approval boundary, smoke test, and rollback procedure. No GitHub Actions workflow is asserted by the inspected inventory.

## 10. How would you present this project in a Forward Deployed Engineer interview?

Start with the user and operational problem described in [`README.md`](README.md). Explain one constraint that changes the implementation, show the linked code or example, and walk through a success case and a failure case. Agree on a measurable acceptance criterion before expanding the solution, and leave a handoff with data boundaries and rollback ownership. Any proposed production or business metric should be identified as a target until measured.

## 11. What does `frontend/src/App.tsx` own?

[`frontend/src/App.tsx`](frontend/src/App.tsx) defines the module setup. Its imports include `react`, `react-router-dom`, `@mui/material`, `./hooks/useAuth`, `./components/Layout`, `./pages/Home`, `./pages/Login`, `./pages/Register`, `./pages/Profile`, `./pages/Documents`.

Trace these definitions and imports to explain the module boundary. Relative imports identify project code; package imports should be checked against the nearest manifest.

## 12. What does `frontend/src/index.tsx` own?

[`frontend/src/index.tsx`](frontend/src/index.tsx) defines the module setup. Its imports include `react`, `react-dom/client`, `@mui/material/CssBaseline`, `./App`, `./reportWebVitals`.

Trace these definitions and imports to explain the module boundary. Relative imports identify project code; package imports should be checked against the nearest manifest.

## 13. How are the Spring HTTP controllers organized?

- [`backend/src/main/java/com/naukrisetu/controller/AuthController.java`](backend/src/main/java/com/naukrisetu/controller/AuthController.java): `@RequestMapping("/api/auth")`, `@PostMapping("/login")`, `@PostMapping("/signup")`; fields use `AuthenticationManager`, `UserRepository`, `RoleRepository`, `PasswordEncoder`, `JwtTokenProvider`.
- [`backend/src/main/java/com/naukrisetu/controller/DistrictController.java`](backend/src/main/java/com/naukrisetu/controller/DistrictController.java): `@RequestMapping("/api/districts")`, `@GetMapping("/{id}")`, `@GetMapping("/states")`, `@PutMapping("/{id}")`, `@DeleteMapping("/{id}")`, `@GetMapping("/stats")`; fields use `DistrictRepository`.
- [`backend/src/main/java/com/naukrisetu/controller/DocumentController.java`](backend/src/main/java/com/naukrisetu/controller/DocumentController.java): `@RequestMapping("/api/documents")`, `@PostMapping("/upload")`, `@GetMapping("/user/{userId}")`, `@GetMapping("/{id}")`, `@DeleteMapping("/{id}")`, `@PutMapping("/{id}/verify")`; fields use `DocumentRepository`, `UserRepository`, `Path`.
- [`backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java`](backend/src/main/java/com/naukrisetu/controller/JobApplicationController.java): `@RequestMapping("/api/applications")`, `@PostMapping("/jobs/{jobId}/apply")`, `@GetMapping("/user/{userId}")`, `@GetMapping("/job/{jobId}")`, `@PutMapping("/{id}/status")`, `@PostMapping("/offline-submit")`; fields use `JobApplicationRepository`, `JobRepository`, `UserRepository`.
- [`backend/src/main/java/com/naukrisetu/controller/JobController.java`](backend/src/main/java/com/naukrisetu/controller/JobController.java): `@RequestMapping("/api/jobs")`, `@GetMapping("/search")`, `@GetMapping("/{id}")`, `@PutMapping("/{id}")`, `@DeleteMapping("/{id}")`, `@GetMapping("/nearby")`; fields use `JobRepository`.
- [`backend/src/main/java/com/naukrisetu/controller/ReferralController.java`](backend/src/main/java/com/naukrisetu/controller/ReferralController.java): `@RequestMapping("/api/referrals")`, `@PostMapping("/jobs/{jobId}/refer")`, `@GetMapping("/user/{userId}")`, `@GetMapping("/job/{jobId}")`, `@PutMapping("/{id}/status")`, `@GetMapping("/stats/user/{userId}")`; fields use `ReferralRepository`, `UserRepository`, `JobRepository`.

Class-level request mappings combine with method-level mappings. Follow injected fields to identify the service/repository layer and inspect security configuration separately.
