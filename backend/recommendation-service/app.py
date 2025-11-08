from flask import Flask, jsonify

app = Flask(__name__)


@app.route("/health", methods=["GET"])
def health_check():
    return jsonify({"status": "UP", "message": "Users OK"}), 200


if __name__ == "__main__":
    # 0.0.0.0 permite acceder desde fuera del contenedor Docker
    app.run(host="0.0.0.0", port=5005)
