# MyAttendo — Android Attendance Companion

**Purpose:** Help Aditya University students view their subject-wise attendance in one simple Android app.

MyAttendo is a companion to the university's existing portal. It does **not** replace the university portal, create attendance records, or claim to be an official university application.

## Current implementation status

- Android app starter: Kotlin + Jetpack Compose.
- Backend starter: Flask REST API.
- Demo mode: sample attendance data for UI development.
- Integration boundary: provider interface for a future university-authorised API/SSO connector.
- No scraping, password collection, or automated login to the university portal is implemented.

**Live attendance is not available until the university authorises an integration and supplies API/SSO documentation and credentials.**

## Architecture

```text
Android app (Kotlin / Compose)
       |
       | HTTPS + app session token
       v
MyAttendo API (Flask)
       |
       | server-side provider adapter
       v
University-approved API / SSO
       |
       v
Official attendance records
```

The university remains the system of record. MyAttendo only reads data that the institution has explicitly authorised it to access.

## Repository structure

```text
MyAttendo-Android/
├── android/                 # Native Android app (Kotlin + Compose)
├── backend/                 # Flask API and integration adapter boundary
├── docs/
│   ├── INTEGRATION_REQUIREMENTS.md
│   ├── PRIVACY_AND_SECURITY.md
│   └── RELEASE_PLAN.md
├── .gitignore
└── README.md
```

## Run the backend locally

Requires Python 3.11+.

```powershell
cd backend
py -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
Copy-Item .env.example .env
flask --app run.py run --debug
```

The backend defaults to `DEMO_MODE=true` and exposes sample data only. This is for development and does not represent real university records.

- Health: `http://127.0.0.1:5000/api/v1/health`
- Attendance: `http://127.0.0.1:5000/api/v1/attendance`

For Android Emulator, use `http://10.0.2.2:5000/` as the development API base URL. For a physical phone, use your computer's LAN IP and ensure both devices are on the same trusted network. Do not expose the debug server to the public internet.

## Open Android app

1. Install Android Studio (current stable).
2. Open the `android/` folder.
3. Allow Gradle sync.
4. Run the `app` configuration on an emulator or Android phone.
5. The starter UI displays demo attendance. Update `BuildConfig.API_BASE_URL` in `android/app/build.gradle.kts` for your local backend.

The Android app currently uses a sample UI state. Wire it to the backend after verifying the local API and selecting the deployment/authentication approach.

## University integration: what is needed

Before live integration, obtain written approval and the university's supported integration specification:
- API base URL, API documentation, test environment and rate limits.
- Supported student authentication method (prefer university SSO/OAuth/OIDC).
- Approved scopes/permissions for read-only attendance access.
- Stable student/course identifiers and attendance response schema.
- Token expiry, refresh, revocation and logout requirements.
- Data retention, privacy notice, incident contact and support owner.

Do not ask students to type their university password into MyAttendo. Do not store university passwords. Do not scrape private portal pages or automate portal login unless the university explicitly authorises that exact mechanism in writing.

## What is not implemented yet

- Live Aditya University authentication or attendance sync.
- University-issued API credentials or SSO.
- Push notifications.
- Offline cache and background sync.
- Attendance history charts and export.
- Play Store release signing and production deployment.

See `docs/` before attempting a real integration.
