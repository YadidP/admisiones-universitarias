const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const EstadoDocumento = sequelize.define('EstadoDocumento', {
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
  tableName: 'estados_documento',
  timestamps: false,
});

module.exports = EstadoDocumento;