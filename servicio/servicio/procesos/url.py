from flask import Blueprint, jsonify, request
from servicio.procesos.aplicacion import Aplicacion
from servicio.procesos.entidades import Proceso

procesos_blueprint = Blueprint("usac", __name__)
aplicacion = Aplicacion()


# Usuarios
@procesos_blueprint.route('/usuarios/cantidad-activos')
def get_usuarios_cant_activos():
    usuarios_cant_activos = aplicacion.get_usuarios_cant_activos()
    respuesta = [{**usuario.to_dict(), "cantidad_activos_usuario": cant_activos}
                 for usuario, cant_activos in usuarios_cant_activos]
    return jsonify(respuesta)


@procesos_blueprint.route('/usuarios/<cedula>/activos')
def get_activos_por_usuario(cedula):
    usuario = aplicacion.get_usuario_por_cedula(cedula)
    activos_usuario = aplicacion.get_activos_por_usuario(usuario)
    respuesta = [{**activo.to_dict(), **activo.get_item().to_dict()} for activo in activos_usuario]
    return jsonify(respuesta)


@procesos_blueprint.route('/usuarios/<cedula>/procesos')
def get_procesos_por_usuario(cedula):
    usuario = aplicacion.get_usuario_por_cedula(cedula)
    procesos = aplicacion.get_procesos_por_usuario(usuario)
    respuesta = [proceso.to_dict() for proceso in procesos]
    return jsonify(respuesta)


# Procesos
@procesos_blueprint.route('/procesos')
def get_procesos():
    procesos = aplicacion.get_procesos()
    respuesta = Proceso.procesos_to_dict(procesos)
    return jsonify(respuesta)


@procesos_blueprint.route('/procesos', methods=['POST'])
def crear_proceso():
    data = request.get_json()
    proceso = data.get("proceso")
    cedulas_usuarios = data.get("usuarios_proceso")
    usuarios = [aplicacion.get_usuario_por_cedula(usuario.get("cedula_usuario")) for usuario in cedulas_usuarios]
    nuevo_proceso = aplicacion.crear_proceso(proceso, usuarios)
    return jsonify({"id_proceso": nuevo_proceso.get_id()})


@procesos_blueprint.route('/procesos/<id_proceso>/usuarios')
def get_usuarios_por_proceso(id_proceso):
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    usuarios = aplicacion.get_usuarios_por_proceso(proceso)
    respuesta = [usuario.to_dict() for usuario in usuarios]
    return jsonify(respuesta)


@procesos_blueprint.route('/procesos/<id_proceso>/activos')
def get_activos_por_proceso(id_proceso):
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    usuarios = aplicacion.get_usuarios_por_proceso(proceso)
    activos = []
    for usuario in usuarios:
        activos = activos + aplicacion.get_activos_por_usuario(usuario)
    respuesta = [{**activo.to_dict(), **activo.get_item().to_dict()} for activo in activos]
    return jsonify(respuesta)


@procesos_blueprint.route('/procesos/<id_proceso>')
def get_detalle_proceso(id_proceso):
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    usuarios = aplicacion.get_usuarios_por_proceso(proceso)
    respuesta = {"proceso": {
        **proceso.to_dict(),
        "cantidad_usuarios_proceso": len(usuarios),
        "cantidad_activos_proceso": aplicacion.get_cantidad_activos_proceso(proceso),
    },
        "usuarios": [],
        "activos": []
    }
    for usuario in usuarios:
        cant_act = len(aplicacion.get_cant_activos_usuario(usuario))
        cant_obs = aplicacion.get_cant_activos_observacion_usuario(usuario, proceso)
        respuesta["usuarios"].append({**usuario.to_dict(),
                                      "cantidad_observaciones_usuario": cant_obs,
                                      "cantidad_activos_usuario": cant_act})
    for activo in aplicacion.get_activos_por_proceso(proceso):
        respuesta["proceso"]["estado_proceso"] = proceso.get_estado()
        usuario = aplicacion.get_usuario_por_activo(activo)
        item = activo.get_item()
        respuesta["activos"].append({**activo.to_dict(),
                                     **activo.get_item().to_dict(),
                                     **usuario.to_dict(),
                                     "revision_activo": activo.get_revision(),
                                     "estado_revision_activo": activo.get_estado(),
                                     "observacion_revision": activo.get_observacion()
                                     })
    return jsonify(respuesta)


@procesos_blueprint.route('/procesos/<id_proceso>/usuarios/<cedula>', methods=['DELETE'])
def eliminar_usuario_de_proceso(id_proceso, cedula):
    usuario = aplicacion.get_usuario_por_cedula(cedula)
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    aplicacion.eliminar_usuario_de_proceso(usuario, proceso)
    return jsonify({"mensaje": "Usuario eliminado correctamente"})


@procesos_blueprint.route('/procesos/<id_proceso>/usuarios/<cedula>', methods=['POST'])
def agregar_usuario_a_proceso(id_proceso, cedula):
    usuario = aplicacion.get_usuario_por_cedula(cedula)
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    aplicacion.agregar_usuario_proceso(usuario, proceso)
    return jsonify({"mensaje": "No implementado"})


@procesos_blueprint.route('/procesos/<id_proceso>/activos/<id_activo>', methods=['PUT'])
def validar_activo(id_proceso, id_activo):
    data = request.get_json()
    proceso = aplicacion.get_proceso_por_id(id_proceso)
    activo = aplicacion.get_activo_por_id(id_activo)
    aplicacion.validar_activo(activo, proceso, data.get("estado_activo"), data.get("observacion_activo"))
    return jsonify({"mensaje": "Activo validado"})


@procesos_blueprint.route('/')
def test():
    return "Si funciona!"
