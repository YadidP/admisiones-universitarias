const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const EstadoPago = sequelize.define('EstadoPago', {
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
  tableName: 'estados_pago',
  timestamps: false,
});

module.exports = EstadoPago;