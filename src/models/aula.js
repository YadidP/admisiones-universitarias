const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Aula = sequelize.define('Aula', {
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
  capacidad: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
}, {
  tableName: 'aulas',
  timestamps: false,
});

module.exports = Aula;