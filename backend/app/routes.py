from flask import Blueprint, jsonify, current_app
from .provider import get_provider, UniversityApiProvider

api = Blueprint("api", __name__)

@api.get("/health")
def health():
    return jsonify({"status": "ok", "service": "myattendo-api",
                    "mode": "demo" if current_app.config.get("DEMO_MODE") else "university-integration-pending"})

@api.get("/attendance")
def attendance():
    if not current_app.config.get("DEMO_MODE"):
        # Do not return data until real identity verification and an approved provider exist.
        return jsonify({"error": "University integration is not configured.",
                        "required": "Approved SSO/API credentials and server-side connector."}), 503
    data = get_provider().get_attendance()
    data["notice"] = "Synthetic demo data. Not official Aditya University attendance."
    return jsonify(data)

@api.get("/me")
def me():
    return jsonify({"error": "Authentication is not configured.",
                    "required": "Implement the university-approved SSO/OIDC flow before exposing student data."}), 501
