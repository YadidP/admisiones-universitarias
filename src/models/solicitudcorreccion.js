const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Postulante = require('./postulante');
const EstadoSolicitud = require('./estadosolicitud');

const SolicitudCorreccion = sequelize.define('SolicitudCorreccion', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  descripcion: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
  fecha_solicitud: {
    type: DataTypes.DATE,
    allowNull: false,
    defaultValue: DataTypes.NOW,
  },
}, {
  tableName: 'solicitudes_correccion',
  timestamps: false,
});

SolicitudCorreccion.belongsTo(Postulante, { foreignKey: 'postulanteId' });
Postulante.hasMany(SolicitudCorreccion, { foreignKey: 'postulanteId' });

SolicitudCorreccion.belongsTo(EstadoSolicitud, { foreignKey: 'estadoSolicitudId' });
EstadoSolicitud.hasMany(SolicitudCorreccion, { foreignKey: 'estadoSolicitudId' });

module.exports = SolicitudCorreccion;