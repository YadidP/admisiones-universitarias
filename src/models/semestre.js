const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Semestre = sequelize.define('Semestre', {
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
  fecha_inicio: {
    type: DataTypes.DATE,
    allowNull: false,
  },
  fecha_fin: {
    type: DataTypes.DATE,
    allowNull: false,
  },
}, {
  tableName: 'semestres',
  timestamps: false,
});

module.exports = Semestre;