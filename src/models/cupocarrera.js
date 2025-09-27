const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Carrera = require('./carrera');
const Semestre = require('./semestre');

const CupoCarrera = sequelize.define('CupoCarrera', {
  id: {
    type: DataTypes.BIGINT,
    primaryKey: true,
    autoIncrement: true,
  },
  cupos_disponibles: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
}, {
  tableName: 'cupos_carrera',
  timestamps: false,
});

CupoCarrera.belongsTo(Carrera, { foreignKey: 'carreraId' });
Carrera.hasMany(CupoCarrera, { foreignKey: 'carreraId' });

CupoCarrera.belongsTo(Semestre, { foreignKey: 'semestreId' });
Semestre.hasMany(CupoCarrera, { foreignKey: 'semestreId' });

module.exports = CupoCarrera;