# MyAttendo release plan

## Phase 1 — Prototype
- Run the Android demo UI.
- Run the local Flask demo API.
- Validate subject cards, percentages, empty/error states and mobile layouts.
- Use synthetic records only.

## Phase 2 — University integration discovery
- Meet university IT / student systems team.
- Obtain written approval and API/SSO specifications.
- Agree on the exact attendance fields and access scope.
- Implement the provider adapter and authentication flow.

## Phase 3 — Controlled pilot
- Test with university-provided test accounts.
- Reconcile MyAttendo figures against the official portal.
- Verify privacy, authorisation, token revocation and failure handling.
- Pilot only with approved participants.

## Phase 4 — Production
- Deploy backend behind HTTPS with managed secrets and monitoring.
- Complete security review and accessibility testing.
- Publish privacy notice and support contact.
- Release signed Android builds through the approved distribution channel.

## Phase 5 — Improvements
- Attendance history calendar and charts.
- Push alerts when attendance changes or drops below a university-approved threshold.
- Offline last-synced snapshot with visible timestamp.
- Timetable and academic notifications if separately approved.
