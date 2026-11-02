# Official university integration requirements

## Goal
Read a student's own subject-wise attendance from the university's existing system and display it in MyAttendo.

## Required from the university
1. Written approval naming MyAttendo and its permitted use.
2. Official API documentation and a test/sandbox environment.
3. Supported identity flow: preferably OAuth 2.0 / OpenID Connect (OIDC) or university SSO.
4. Read-only attendance scope limited to the signed-in student.
5. Response schema: student identifier, subject code/name, held classes, attended classes, attendance date/history if permitted.
6. Token lifetime, refresh, logout and revocation specifications.
7. Rate limits, support contact, maintenance windows and versioning policy.

## Integration design
- Android app starts the approved university sign-in flow using the system browser / Custom Tabs.
- University identity provider authenticates the student.
- Backend validates the returned authorization code/token according to the official protocol.
- Backend calls the official attendance API using a least-privilege access token.
- Backend normalises the official response and returns only the student's authorised data to the app.
- Tokens and client secrets stay server-side or in platform-approved secure storage; never hard-code secrets in the APK.

## If there is no API
Ask the university IT team whether it can provide a supported API, SSO integration, scheduled secure export, or an approved deep-link experience. Do not scrape private pages or automate portal login without written approval for that specific method.

## Acceptance tests
- Student A cannot access Student B's records.
- Expired/revoked tokens stop data access.
- API outages show a clear stale/unavailable state, never fabricated current data.
- Totals and percentages reconcile with the official portal.
- Every data request is authorised and auditable.
