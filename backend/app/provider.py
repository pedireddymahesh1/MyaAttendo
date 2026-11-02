from abc import ABC, abstractmethod
from flask import current_app

class AttendanceProvider(ABC):
    @abstractmethod
    def get_attendance(self, university_subject_id: str) -> dict:
        """Return normalised attendance for the authenticated student."""
        raise NotImplementedError

class DemoAttendanceProvider(AttendanceProvider):
    """Synthetic records for local UI development only."""
    def get_attendance(self, university_subject_id: str = "") -> dict:
        return {
            "source": "DEMO_ONLY",
            "subjects": [
                {"subject_code": "CS301", "subject_name": "Database Management Systems",
                 "classes_held": 32, "classes_attended": 28, "percentage": 87.5},
                {"subject_code": "CS302", "subject_name": "Algorithms",
                 "classes_held": 30, "classes_attended": 23, "percentage": 76.7},
                {"subject_code": "CS303", "subject_name": "Computer Networks",
                 "classes_held": 28, "classes_attended": 18, "percentage": 64.3}
            ]
        }

class UniversityApiProvider(AttendanceProvider):
    """Intentionally unimplemented until the university supplies an approved API spec.

    Implement server-to-server calls here only after written authorisation.
    Never put university client secrets in the Android app.
    """
    def get_attendance(self, university_subject_id: str = "") -> dict:
        raise NotImplementedError(
            "University API connector is not configured. Obtain official API/SSO documentation "
            "and implement this adapter under university authorisation."
        )

def get_provider():
    if current_app.config.get("DEMO_MODE", True):
        return DemoAttendanceProvider()
    return UniversityApiProvider()
