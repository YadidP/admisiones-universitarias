const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Asignatura = sequelize.define('Asignatura', {
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
  tableName: 'asignaturas',
  timestamps: false,
});

module.exports = Asignatura;