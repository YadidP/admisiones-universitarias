const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const EstadoSolicitud = sequelize.define('EstadoSolicitud', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  nombre: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
  },
}, {
  tableName: 'estados_solicitud',
  timestamps: false,
});

module.exports = EstadoSolicitud;