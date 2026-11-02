# MyAttendo — University-wide Student Platform

**One University. One Platform. Every Student.**

A Flask + MySQL-ready campus platform starter designed for a university deployment with role-based workspaces for administrators, faculty and students.

> **Important status:** This is a functional starter application, not an official Aditya University system. It does not connect to Aditya University's student information system or attendance portal. University-wide rollout requires written institutional approval, verified data, an authorised integration, security review, privacy review, infrastructure and operational ownership.

## Included modules

### Student
- Secure account sign-in (accounts provisioned by administrators).
- Subject-wise attendance percentage from sessions recorded in MyAttendo.
- Attendance labels: 75%+ Safe, 65–74.9% Condonation range, below 65% Below 65%. Confirm actual university regulations before using these labels.
- Personal tasks / assignments.
- Enrolled-subject timetable.

### Faculty
- Department-scoped subject list.
- Mark present / absent / late for enrolled students.
- Create dated class sessions with period and topic.
- View recorded sessions.

### University administrator
- Create departments and subjects.
- Create student, faculty and admin accounts.
- Enrol students in subjects.
- Bulk-import student accounts using CSV.
- Export account directory CSV.
- View basic platform counts.

### Platform
- Password hashing.
- Role-based route protection.
- CSRF protection on forms.
- SQLAlchemy ORM and MySQL support.
- SQLite development default.
- Docker Compose for MySQL + Gunicorn.
- Demo data seeder.
- Open-source contribution, security and deployment docs.

## Run locally on Windows

1. Install Python 3.11+.
2. Extract the ZIP and open PowerShell in the project folder.
3. Create and activate a virtual environment:

   ```powershell
   py -m venv .venv
   .\.venv\Scripts\Activate.ps1
   ```
4. Install packages:

   ```powershell
   pip install -r requirements.txt
   ```
5. Copy `.env.example` to `.env`. Set a unique random `SECRET_KEY`. SQLite is used by default for local testing.
6. Create the first administrator:

   ```powershell
   python scripts/create_admin.py
   ```
7. Start the development server:

   ```powershell
   flask --app run.py run --debug
   ```
8. Open http://127.0.0.1:5000/login.

### Optional demo accounts

Instead of creating an admin manually, run:

```powershell
python scripts/seed_demo.py
```

Demo logins:
- admin@example.test / ChangeThisDemoPassword123!
- faculty@example.test / ChangeThisDemoPassword123!
- student@example.test / ChangeThisDemoPassword123!

**Demo accounts are public sample credentials. Never deploy them. Delete them and create unique accounts before exposing any instance to a network.**

## MySQL / Docker deployment

1. Install Docker Desktop.
2. Create a deployment `.env` file from `.env.example` and set strong random secrets. Also set `MYSQL_PASSWORD` and `MYSQL_ROOT_PASSWORD`.
3. Configure the app database URL if running outside Compose, or let Compose construct it from the environment.
4. Start:

   ```powershell
   docker compose up --build
   ```
5. The app will be available at http://localhost:8000.

This Compose file is a deployment starting point, not a complete university production environment. Add HTTPS reverse proxy, backups, monitoring, alerting, log retention, secret management, database migrations, rate limiting, and a tested recovery plan.

## Bulk student import CSV

The admin page accepts a CSV with these headers:

```csv
full_name,email,university_id,temporary_password
Example Student,student1@example.edu,AU-DEMO-001,ChangeMe-Temp-12345
```

Only import records you are authorised to process. Use a secure password distribution and reset process. Do not email spreadsheets containing passwords. For a real university rollout, use the institution's identity provider/SSO or an approved provisioning method instead of bulk temporary passwords.

## Repository structure

```text
MyAttendo-University/
├── app/
│   ├── __init__.py
│   ├── models.py
│   ├── auth.py
│   ├── main.py
│   ├── admin.py
│   ├── faculty.py
│   ├── student.py
│   ├── decorators.py
│   ├── templates/
│   └── static/
├── scripts/
│   ├── create_admin.py
│   └── seed_demo.py
├── docs/
├── tests/
├── database/
├── Dockerfile
├── docker-compose.yml
├── requirements.txt
└── run.py
```

## Current limitations / not yet implemented

- No official Aditya University login, SSO or portal integration.
- No live attendance sync or scraping of university pages.
- No automatic import from the university's private student database.
- No email/SMS/WhatsApp notifications.
- No timetable administration UI yet (entries can be provisioned in database).
- No password reset / MFA / audit dashboard / formal approval workflow.
- No database migration framework yet.
- No production security certification or penetration test.
- No verified student adoption, external contributors or download metrics.

## Required before real university-wide launch

Read [UNIVERSITY_DEPLOYMENT.md](docs/UNIVERSITY_DEPLOYMENT.md) and [SECURITY_CHECKLIST.md](docs/SECURITY_CHECKLIST.md). Obtain written permission from university IT/administration, confirm data-controller responsibilities and privacy obligations, agree the official data source, run a security review, pilot with synthetic data, and only then conduct a limited authorised pilot.

## Open-source

See [CONTRIBUTING.md](CONTRIBUTING.md), [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md), and [SECURITY.md](SECURITY.md). The project is licensed under MIT; review and accept this license choice before publishing if you are not the sole copyright holder.
