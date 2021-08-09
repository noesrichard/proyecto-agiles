from flask import Blueprint, jsonify
from servicio.app import auth
from .entidades import Administrador
from servicio.login.data import DataAdmin

login_blueprint = Blueprint("login_blueprint", __name__)


@auth.verify_password
def verify_password(username, password) -> Administrador:
    repo_admins: DataAdmin = DataAdmin()
    admin: Administrador = Administrador(cedula_admin=username, password_admin=password)
    if repo_admins.existe(admin):
        return admin


@auth.get_user_roles
def get_user_roles(admin: Administrador):
    repo_admins: DataAdmin = DataAdmin()
    admin.rol = repo_admins.get_rol(admin)
    return admin.rol


@login_blueprint.route('/admin/login')
@auth.login_required
def login():
    respuesta: dict = {"permiso": True}
    return jsonify(respuesta)