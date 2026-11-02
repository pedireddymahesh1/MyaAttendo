import os
from flask import Flask
from flask_cors import CORS
from dotenv import load_dotenv
from .routes import api

load_dotenv()

def create_app(test_config=None):
    app = Flask(__name__)
    app.config["SECRET_KEY"] = os.getenv("SECRET_KEY", "dev-only-change-me")
    app.config["DEMO_MODE"] = os.getenv("DEMO_MODE", "true").lower() == "true"
    if test_config:
        app.config.update(test_config)
    # Development convenience only. Restrict allowed origins before deployment.
    CORS(app, resources={r"/api/*": {"origins": "*"}} if app.config.get("TESTING") else {})
    app.register_blueprint(api, url_prefix="/api/v1")
    return app
