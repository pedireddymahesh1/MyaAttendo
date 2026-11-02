# Privacy and security baseline

- Collect the minimum data required to show the student's own attendance.
- Do not collect or store university passwords.
- Do not embed client secrets in the Android application.
- Use HTTPS in all non-local environments.
- Validate university tokens server-side, including issuer, audience, signature, expiry and scopes as required by the provider's official documentation.
- Enforce student-to-record ownership on every API request.
- Keep access logs free of passwords, access tokens and unnecessary personal data.
- Define retention, deletion, correction and incident response with the university.
- Use Android Keystore-backed storage for any app session credentials.
- Add rate limiting, dependency scanning, security testing and monitoring before production.
- Obtain university privacy/security approval before processing real student records.
