const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Facultad = sequelize.define('Facultad', {
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
  tableName: 'facultades',
  timestamps: false,
});

module.exports = Facultad;