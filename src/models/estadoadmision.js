const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const EstadoAdmision = sequelize.define('EstadoAdmision', {
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
  tableName: 'estados_admision',
  timestamps: false,
});

module.exports = EstadoAdmision;