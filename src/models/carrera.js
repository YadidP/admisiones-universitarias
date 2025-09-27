const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Facultad = require('./facultad');

const Carrera = sequelize.define('Carrera', {
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
  descripcion: {
    type: DataTypes.TEXT,
    allowNull: true,
  },
}, {
  tableName: 'carreras',
  timestamps: false,
});

Carrera.belongsTo(Facultad, { foreignKey: 'facultadId' });
Facultad.hasMany(Carrera, { foreignKey: 'facultadId' });

module.exports = Carrera;